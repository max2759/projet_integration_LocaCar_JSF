package servlet;

import entities.AdsEntity;
import entities.BrandsEntity;
import entities.CarTypesEntity;
import entities.ModelsEntity;
import enumeration.EnumFuel;
import enumeration.EnumTypesAds;
import forms.AdsForm;
import services.BrandsService;
import services.CarTypesService;
import services.ModelsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/details")
public class ShowOneAd extends HttpServlet {

    public static final String VUE = "/WEB-INF/adDetails.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idAds = request.getParameter("idAdsToShow");
        int idAd = Integer.parseInt(idAds);

        // Form pour les annonces
        AdsForm adsForm = new AdsForm();

        //Appel des méthodes qui retourne l'objet annonce par ID
        AdsEntity adsEntity = adsForm.showAd(idAd);

        request.setAttribute("ads", adsEntity);


        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
