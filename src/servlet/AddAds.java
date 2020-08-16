package servlet;

import entities.*;
import forms.AdsForm;
import services.*;
import util.JPAutil;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@WebServlet("/ajouter-annonce")
public class AddAds extends HttpServlet {

    public static final String VUE = "/WEB-INF/addAds.jsp";
    public static final String REDIRECT_URL = "annonces";
    public static final String URL_REDIRECT = "connexion";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = JPAutil.createEntityManager("projet_bac_info2");

        AdsForm adsForm = new AdsForm();

        try {
            adsForm.addAds(request);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        response.sendRedirect(REDIRECT_URL);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // récupération de la session
        HttpSession session = request.getSession();

        UsersEntity usersEntity = (UsersEntity) session.getAttribute("UserEntity");

        if (usersEntity != null) {
            // liste d'objet
            List<CarTypesEntity> carTypesEntities;
            List<ModelsEntity> modelsEntities;
            List<BrandsEntity> brandsEntities;

            // Les services
            CarTypesService carTypesService = new CarTypesService();
            ModelsService modelsService = new ModelsService();
            BrandsService brandsService = new BrandsService();

            //Appel des méthodes qui retourne une liste d'objet
            carTypesEntities = carTypesService.displayCategory();
            modelsEntities = modelsService.displayModels();
            brandsEntities = brandsService.displayBrands();


            request.setAttribute("category", carTypesEntities);
            request.setAttribute("models", modelsEntities);
            request.setAttribute("brands", brandsEntities);


            this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
        }else{
            response.sendRedirect(URL_REDIRECT);
        }
    }
}
