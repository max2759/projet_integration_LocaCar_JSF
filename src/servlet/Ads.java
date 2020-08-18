package servlet;

import entities.AdsEntity;
import entities.UsersAdsEntity;
import entities.UsersEntity;
import services.AdsService;
import services.UsersAdsService;
import util.JPAutil;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@WebServlet("/annonces")

public class Ads extends HttpServlet {
    public static final String VUE = "/WEB-INF/listAds.jsp";
    public static final String URL_REDIRECT = "connexion";
    public List<UsersAdsEntity> usersAdsEntities;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        UsersEntity usersEntity = (UsersEntity) session.getAttribute("UserEntity");

        if (usersEntity != null){
            UsersAdsService usersAdsService = new UsersAdsService();

            usersAdsEntities= usersAdsService.listAllUserAds();

            request.setAttribute("usersAdsEntities", usersAdsEntities);

            this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

        }else{
            response.sendRedirect(URL_REDIRECT);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }
}
