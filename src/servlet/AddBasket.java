package servlet;

import entities.AdsEntity;
import entities.CarsEntity;
import forms.AdsForm;
import forms.CarsForm;
import services.AdsService;
import services.CarsService;
import util.JPAutil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/addBasket")

public class AddBasket extends HttpServlet {
    public static final String VUE = "/WEB-INF/addBasket.jsp";

    public AdsEntity ads;
    public CarsEntity cars;
    public AdsForm adsForm = new AdsForm();
    public CarsForm carsForm = new CarsForm();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        carsForm.checkCars(request);
        adsForm.checkAds(request);
        if ((!(adsForm.getErrors().isEmpty())) || (!(carsForm.getErrors().isEmpty()))) {
            request.setAttribute("adsForm", adsForm);
            request.setAttribute("carsForm", carsForm);
        }
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("idAds").isEmpty()) {
            doGet(request, response);
            return;

        }
        /*
        int idCars = Integer.parseInt(request.getParameter("idCars"));
        int idAds = Integer.parseInt(request.getParameter("idAds"));
*/
        // Recherche de la voiture

        cars = carsForm.checkCars(request);

        // Recherche de l'ads
        ads = adsForm.checkAds(request);

        // Si on a des erreurs, par exemple si il ne trouve la car ou l'ads
        if ((!(adsForm.getErrors().isEmpty())) || (!(carsForm.getErrors().isEmpty()))) {
            request.setAttribute("adsForm", adsForm);
            request.setAttribute("carsForm", carsForm);
        } else {
            request.setAttribute("cars", cars);
            request.setAttribute("ads", ads);
        }
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

    }
}
