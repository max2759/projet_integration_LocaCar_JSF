package servlet;

import entities.AdsEntity;
import entities.CarsEntity;
import entities.ContractsEntity;
import forms.AdsForm;
import forms.BasketCrudForm;
import forms.BasketForm;
import forms.CarsForm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;


@WebServlet("/basket")

public class Basket extends HttpServlet {
    public static final String VUE = "/WEB-INF/basket.jsp";

    public AdsEntity ads;
    public CarsEntity cars;
    public AdsForm adsForm = null;
    public CarsForm carsForm = null;
    public BasketCrudForm basketCrudForm;
    public List<ContractsEntity> contractsEntity;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Integer idUser;
        if (session.getAttribute("User") != null) {
            idUser = Integer.parseInt(session.getAttribute("User").toString());
        }
        else { idUser = 0; }


        adsForm = new AdsForm();
        carsForm = new CarsForm();

        carsForm.checkCars(request);
        adsForm.checkAds(request);

        if (this.basketCrudForm == null) {
            basketCrudForm = new BasketCrudForm();
        }
        contractsEntity = basketCrudForm.listContracts(request, idUser);


        if ((!(adsForm.getErrors().isEmpty())) || (!(carsForm.getErrors().isEmpty()))) {
            request.setAttribute("adsForm", adsForm);
            request.setAttribute("carsForm", carsForm);
        }

        request.setAttribute("basket", contractsEntity);
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer idUser;
        if (session.getAttribute("User") != null) {
            idUser = Integer.parseInt(session.getAttribute("User").toString());
        }
        else { idUser = 0; }

        if (this.basketCrudForm == null) {
            basketCrudForm = new BasketCrudForm();
        }
        try {
            Date dateStart = new Date();
            Date dateEnd = new Date();
            adsForm = basketCrudForm.addBasket(request, dateStart, dateEnd);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        contractsEntity= basketCrudForm.listContracts(request,idUser);




        request.setAttribute("adsForm", adsForm);

        request.setAttribute("basket", contractsEntity);
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);


    }

}

