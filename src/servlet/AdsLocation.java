package servlet;

import entities.AdsEntity;
import entities.CarsEntity;
import services.CarsService;
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


@WebServlet("/adsLocation")

public class AdsLocation extends HttpServlet {
    public static final String VUE = "/WEB-INF/adsLocation.jsp";
    public CarsEntity cars;
    public AdsEntity ads;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Recherche de la voiture

        EntityManager em = JPAutil.createEntityManager("projet_bac_info2");

        CarsService carsService = new CarsService();

        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            cars = carsService.consulter(em, 5);
            tx.commit();
        } catch (Exception ex) {
            if (tx != null && tx.isActive()) tx.rollback();
        } finally {
            em.close();
        }


// Recherche de l'ads
        em = JPAutil.createEntityManager("projet_bac_info2");
        CarsService adsService = new CarsService();
        tx = null;

        try {
            tx = em.getTransaction();
            tx.begin();
            ads = carsService.searchAds(em, 5);
            tx.commit();
        } catch (Exception ex) {
            if (tx != null && tx.isActive()) tx.rollback();
        } finally {
            em.close();
        }

        request.setAttribute("cars", cars);
        request.setAttribute("ads", ads);


        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }
}
