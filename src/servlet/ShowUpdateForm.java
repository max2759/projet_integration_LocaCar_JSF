package servlet;

import entities.*;
import enumeration.EnumFuel;
import enumeration.EnumTypesAds;
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
import java.util.List;

@WebServlet("/modifier-annonce")
public class ShowUpdateForm extends HttpServlet {

    public static final String VUE = "/WEB-INF/UpdateAds.jsp";
    public static final String URL_REDIRECT = "connexion";


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // récupération de la session
        HttpSession session = request.getSession();

        UsersEntity usersEntity = (UsersEntity) session.getAttribute("UserEntity");
        EntityManager em = JPAutil.createEntityManager("projet_bac_info2");


        String idAds = request.getParameter("idAds");
        int idAd = Integer.parseInt(idAds);


        if (usersEntity != null) {

            // liste d'objet
            List<CarTypesEntity> carTypesEntities;
            List<ModelsEntity> modelsEntities;
            List<BrandsEntity> brandsEntities;

            AdsService adsService = new AdsService();
            AdsEntity adsEntity = new AdsEntity();
            CarsEntity carsEntity = new CarsEntity();
            CarsService carsService = new CarsService();

            adsEntity = adsService.consulter(em, idAd);

            // Les services
            CarTypesService carTypesService = new CarTypesService();
            ModelsService modelsService = new ModelsService();
            BrandsService brandsService = new BrandsService();
            EnumFuel enumFuel = null;

            //Appel des méthodes qui retourne une liste d'objet
            carTypesEntities = carTypesService.displayCategory();
            modelsEntities = modelsService.displayModels();
            brandsEntities = brandsService.displayBrands();



            request.setAttribute("category", carTypesEntities);
            request.setAttribute("models", modelsEntities);
            request.setAttribute("brands", brandsEntities);
            request.setAttribute("ads", adsEntity);
            request.setAttribute("enumFuel", EnumFuel.values() );




            this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
        } else {
            response.sendRedirect(URL_REDIRECT);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    }
}
