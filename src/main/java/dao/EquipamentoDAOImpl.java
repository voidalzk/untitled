package dao;

import model.Equipamento;
import util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class EquipamentoDAOImpl implements EquipamentoDAO {

    @Override
    public void create(Equipamento equipamento) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.persist(equipamento);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Equipamento read(Long id) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Equipamento equipamento = em.find(Equipamento.class, id);
        em.close();
        return equipamento;
    }

    @Override
    public void update(Equipamento equipamento) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.merge(equipamento);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void delete(Equipamento equipamento) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        Equipamento managedEquipamento = em.find(Equipamento.class, equipamento.getId());
        if (managedEquipamento != null) {
            em.remove(managedEquipamento);
        }
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Equipamento> findAll() {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        TypedQuery<Equipamento> query = em.createQuery("SELECT e FROM Equipamento e", Equipamento.class);
        List<Equipamento> equipamentos = query.getResultList();
        em.close();
        return equipamentos;
    }
}