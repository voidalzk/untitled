package servlet;

import model.Empregado;
import model.Projeto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/associate")
public class AssociateServlet extends HttpServlet {
    private EntityManagerFactory emf;

    @Override
    public void init() {
        emf = Persistence.createEntityManagerFactory("exemploPU");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();
        List<Empregado> empregados = em.createQuery("SELECT e FROM Empregado e", Empregado.class).getResultList();
        List<Projeto> projetos = em.createQuery("SELECT p FROM Projeto p", Projeto.class).getResultList();
        em.close();
        request.setAttribute("empregados", empregados);
        request.setAttribute("projetos", projetos);
        request.getRequestDispatcher("associate.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long empregadoId = Long.parseLong(request.getParameter("empregadoId"));
        String[] projetoIds = request.getParameterValues("projetoIds");

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Empregado empregado = em.find(Empregado.class, empregadoId);
        if (projetoIds != null) {
            for (String pid : projetoIds) {
                Projeto projeto = em.find(Projeto.class, Long.parseLong(pid));
                empregado.getProjetos().add(projeto);
                projeto.getEmpregados().add(empregado);
            }
        }
        em.getTransaction().commit();
        em.close();
        response.sendRedirect("index.jsp");
    }

    @Override
    public void destroy() {
        emf.close();
    }
}