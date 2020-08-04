package servlet;

import entities.AdsEntity;
import entities.CarsEntity;
import forms.AdsForm;
import forms.BasketCrudForm;
import forms.CarsForm;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/addBasket")

public class AddBasket extends HttpServlet {
    public static final String VUE = "/WEB-INF/addBasket.jsp";

    public AdsEntity ads;
    public CarsEntity cars;
    public AdsForm adsForm = null;
    public CarsForm carsForm = null;
    public BasketCrudForm basketCrudForm;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        adsForm = new AdsForm();
        carsForm = new CarsForm();

        carsForm.checkCars(request);
        adsForm.checkAds(request);
        if ((!(adsForm.getErrors().isEmpty())) || (!(carsForm.getErrors().isEmpty()))) {
            request.setAttribute("adsForm", adsForm);
            request.setAttribute("carsForm", carsForm);
        }
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (this.basketCrudForm == null)
        {
            basketCrudForm = new BasketCrudForm();
        }

        adsForm = basketCrudForm.addBasket(request);

        request.setAttribute("adsForm", adsForm);
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

    }
}
