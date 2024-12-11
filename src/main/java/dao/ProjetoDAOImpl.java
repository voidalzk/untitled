package dao;

import model.Projeto;
import util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ProjetoDAOImpl implements ProjetoDAO {

    @Override
    public void create(Projeto projeto) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.persist(projeto);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Projeto read(Long id) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        Projeto projeto = em.find(Projeto.class, id);
        em.close();
        return projeto;
    }

    @Override
    public void update(Projeto projeto) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.merge(projeto);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void delete(Projeto projeto) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        Projeto managedProjeto = em.find(Projeto.class, projeto.getId());
        if (managedProjeto != null) {
            em.remove(managedProjeto);
        }
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Projeto> findAll() {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        TypedQuery<Projeto> query = em.createQuery("SELECT p FROM Projeto p", Projeto.class);
        List<Projeto> projetos = query.getResultList();
        em.close();
        return projetos;
    }
}