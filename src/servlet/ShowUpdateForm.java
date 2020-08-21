package servlet;

import entities.*;
import enumeration.EnumFuel;
import enumeration.EnumTypesAds;
import forms.AdsForm;
import services.*;
import util.JPAutil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/modifier-annonce")
public class ShowUpdateForm extends HttpServlet {

    public static final String VUE = "/WEB-INF/UpdateAds.jsp";
    public static final String URL_REDIRECT = "connexion";


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // récupération de la session
        HttpSession session = request.getSession();

        UsersEntity usersEntity = (UsersEntity) session.getAttribute("UserEntity");

        // On récupère l'id de l'annonce
        String idAds = request.getParameter("idAds");
        int idAd = Integer.parseInt(idAds);

        // On vérifie que l'utilisateur est bien connecté
        if (usersEntity != null) {

            // liste d'objet
            List<CarTypesEntity> carTypesEntities;
            List<ModelsEntity> modelsEntities;
            List<BrandsEntity> brandsEntities;

            // Form pour les annonces
            AdsForm adsForm = new AdsForm();

            // Les services
            CarTypesService carTypesService = new CarTypesService();
            ModelsService modelsService = new ModelsService();
            BrandsService brandsService = new BrandsService();

            //Appel des méthodes qui retourne une liste d'objet
            carTypesEntities = carTypesService.displayCategory();
            modelsEntities = modelsService.displayModels();
            brandsEntities = brandsService.displayBrands();
            AdsEntity adsEntity = adsForm.showAd(idAd);

            request.setAttribute("category", carTypesEntities);
            request.setAttribute("models", modelsEntities);
            request.setAttribute("brands", brandsEntities);
            request.setAttribute("ads", adsEntity);
            request.setAttribute("enumFuel", EnumFuel.values());
            request.setAttribute("enumTypesAds", EnumTypesAds.values());

            this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
        } else {
            response.sendRedirect(URL_REDIRECT);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
