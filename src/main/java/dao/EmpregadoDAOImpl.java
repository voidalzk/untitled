package dao;

import model.Empregado;
import util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class EmpregadoDAOImpl implements EmpregadoDAO {

    @Override
    public void create(Empregado empregado) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.persist(empregado);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Empregado read(Long id) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Empregado empregado = em.find(Empregado.class, id);
        em.close();
        return empregado;
    }

    @Override
    public void update(Empregado empregado) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.merge(empregado);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void delete(Empregado empregado) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        Empregado managedEmpregado = em.find(Empregado.class, empregado.getId());
        if (managedEmpregado != null) {
            em.remove(managedEmpregado);
        }
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Empregado> findAll() {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        TypedQuery<Empregado> query = em.createQuery("SELECT e FROM Empregado e", Empregado.class);
        List<Empregado> empregados = query.getResultList();
        em.close();
        return empregados;
    }
}