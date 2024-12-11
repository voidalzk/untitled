package dao;

import model.Alocacao;
import util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class AlocacaoDAOImpl implements AlocacaoDAO {

    @Override
    public void create(Alocacao alocacao) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.persist(alocacao);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Alocacao read(Long id) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Alocacao alocacao = em.find(Alocacao.class, id);
        em.close();
        return alocacao;
    }

    @Override
    public void update(Alocacao alocacao) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.merge(alocacao);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void delete(Alocacao alocacao) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        Alocacao managedAlocacao = em.find(Alocacao.class, alocacao.getId());
        if (managedAlocacao != null) {
            em.remove(managedAlocacao);
        }
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Alocacao> findAll() {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        TypedQuery<Alocacao> query = em.createQuery("SELECT a FROM Alocacao a", Alocacao.class);
        List<Alocacao> alocacoes = query.getResultList();
        em.close();
        return alocacoes;
    }

    @Override
    public List<Alocacao> findByEquipamentoId(Long equipamentoId) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        TypedQuery<Alocacao> query = em.createQuery(
            "SELECT a FROM Alocacao a WHERE a.equipamento.id = :equipamentoId", Alocacao.class);
        query.setParameter("equipamentoId", equipamentoId);
        List<Alocacao> alocacoes = query.getResultList();
        em.close();
        return alocacoes;
    }

    @Override
    public List<Alocacao> findByProjetoId(Long projetoId) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        TypedQuery<Alocacao> query = em.createQuery(
            "SELECT a FROM Alocacao a WHERE a.projeto.id = :projetoId", Alocacao.class);
        query.setParameter("projetoId", projetoId);
        List<Alocacao> alocacoes = query.getResultList();
        em.close();
        return alocacoes;
    }
}