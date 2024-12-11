package dao;

import model.Alocacao;
import util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

public class AlocacaoDAOImpl implements AlocacaoDAO {

    @Override
    public void create(Alocacao alocacao) throws AlocacaoException {
        if (hasOverlappingAllocation(alocacao.getEquipamento().getId(), alocacao.getDataInicio(), alocacao.getDataFim(), null)) {
            throw new AlocacaoException("Equipamento já está alocado em outro projeto nesse período.");
        }

        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(alocacao);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public Alocacao read(Long id) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            return em.find(Alocacao.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Alocacao alocacao) throws AlocacaoException {
        if (hasOverlappingAllocation(alocacao.getEquipamento().getId(), alocacao.getDataInicio(), alocacao.getDataFim(), alocacao.getId())) {
            throw new AlocacaoException("Equipamento já está alocado em outro projeto nesse período.");
        }

        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(alocacao);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(Alocacao alocacao) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            Alocacao managedAlocacao = em.find(Alocacao.class, alocacao.getId());
            if (managedAlocacao != null) {
                em.remove(managedAlocacao);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Alocacao> findAll() {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            TypedQuery<Alocacao> query = em.createQuery("SELECT a FROM Alocacao a", Alocacao.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Alocacao> findByEquipamentoId(Long equipamentoId) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            TypedQuery<Alocacao> query = em.createQuery(
                "SELECT a FROM Alocacao a WHERE a.equipamento.id = :equipamentoId", Alocacao.class);
            query.setParameter("equipamentoId", equipamentoId);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Alocacao> findByProjetoId(Long projetoId) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            TypedQuery<Alocacao> query = em.createQuery(
                "SELECT a FROM Alocacao a WHERE a.projeto.id = :projetoId", Alocacao.class);
            query.setParameter("projetoId", projetoId);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public boolean hasOverlappingAllocation(Long equipamentoId, LocalDate dataInicio, LocalDate dataFim, Long alocacaoId) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        String jpql = "SELECT COUNT(a) FROM Alocacao a WHERE a.equipamento.id = :equipamentoId " +
                      "AND (:dataInicio BETWEEN a.dataInicio AND a.dataFim OR :dataFim BETWEEN a.dataInicio AND a.dataFim)";
        
        if (alocacaoId != null) {
            jpql += " AND a.id <> :alocacaoId";
        }

        TypedQuery<Long> query = em.createQuery(jpql, Long.class);
        query.setParameter("equipamentoId", equipamentoId);
        query.setParameter("dataInicio", dataInicio);
        query.setParameter("dataFim", dataFim);
        
        if (alocacaoId != null) {
            query.setParameter("alocacaoId", alocacaoId);
        }

        Long count = query.getSingleResult();
        em.close();
        return count > 0;
    }
}