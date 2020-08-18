package servlet;

import entities.UsersEntity;
import services.UsersService;
import util.JPAutil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/connexion")
public class connexion extends HttpServlet {

    public static final String VUE = "/WEB-INF/connexion.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String user = request.getParameter("User");

        HttpSession session = request.getSession();

        EntityManager em = JPAutil.createEntityManager("projet_bac_info2");
        UsersService usersService = new UsersService();
        UsersEntity usersEntity = new UsersEntity();
        // On recherche la commande par Id user
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            usersEntity = usersService.consult(em, Integer.parseInt(user));
            tx.commit();
        } catch (
                Exception ex) {
            if (tx != null && tx.isActive()) tx.rollback();
        } finally {
            em.close();
        }

        session.setAttribute("UserEntity", usersEntity);
        session.setAttribute("User", user);


        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }
}
