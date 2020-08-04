package servlet;

import entities.AdsEntity;
import services.AdsService;
import util.JPAutil;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/annonces")

public class Ads extends HttpServlet {
    public static final String VUE = "/WEB-INF/ads.jsp";
    public List<AdsEntity> ads;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = JPAutil.createEntityManager("projet_bac_info2");

        AdsService adsService = new AdsService();
        ads = adsService.listerTous();

        request.setAttribute("ads", ads);


        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }
}
