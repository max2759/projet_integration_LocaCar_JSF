package servlet;

import entities.UsersAdsEntity;
import entities.UsersEntity;
import forms.AdsForm;
import services.UsersAdsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/annonces-inactive")
public class DeActivateAds extends HttpServlet {
    public static final String VUE = "/WEB-INF/deActivateAd.jsp";
    public static final String REDIRECT_URL = "annonces";
    public static final String URL_REDIRECT = "connexion";
    public List<UsersAdsEntity> usersAdsEntities;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AdsForm adsForm = new AdsForm();

        adsForm.reActivateAds(request);

        response.sendRedirect(REDIRECT_URL);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // récupération de la session
        HttpSession session = request.getSession();

        UsersEntity usersEntity = (UsersEntity) session.getAttribute("UserEntity");

        if(usersEntity != null) {
            // Appel de la classe usersAdsService
            UsersAdsService usersAdsService = new UsersAdsService();

            // On stocke dans une liste les annonces en fonction de l'utilisateur
            usersAdsEntities= usersAdsService.listAllUserAds();

            request.setAttribute("usersAdsEntities", usersAdsEntities);

            this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
        }else{
            response.sendRedirect(URL_REDIRECT);
        }

    }
}
