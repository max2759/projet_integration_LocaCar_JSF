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


@WebServlet("/removeBasket")

public class RemoveBasket extends HttpServlet {
//    public static final String VUE = "/WEB-INF/basket.jsp";
public static final String URL_REDIRECTION = "basket";         // Cas d'une redirection

    public AdsEntity ads;
//    public CarsEntity cars;
    public AdsForm adsForm = null;
//    public CarsForm carsForm = null;
    public BasketCrudForm basketCrudForm;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (this.basketCrudForm == null) {
            basketCrudForm = new BasketCrudForm();
        }
        adsForm = basketCrudForm.delBasket(request);


        request.setAttribute("adsForm", adsForm);
        response.sendRedirect( URL_REDIRECTION );                    // Cas d'une redirection

//        this.getServletContext().getRequestDispatcher(URL_REDIRECTION).forward(request, response);

    }
}
