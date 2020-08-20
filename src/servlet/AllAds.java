package servlet;

import entities.AdsEntity;
import services.AdsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/accueil")
public class AllAds extends HttpServlet {

    public static final String VUE = "/WEB-INF/index.jsp";
    public List<AdsEntity> adsEntities;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AdsService adsService = new AdsService();

        adsEntities = adsService.listerTous();

        request.setAttribute("adsEntities", adsEntities);

        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

    }
}
