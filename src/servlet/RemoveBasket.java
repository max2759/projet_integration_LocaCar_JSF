package servlet;

import entities.AdsEntity;
import forms.AdsForm;
import forms.BasketCrudForm;
import forms.CarsForm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/removeBasket")

public class RemoveBasket extends HttpServlet {
    public static final String URL_REDIRECTION = "basket";         // Cas d une redirection
    public static final String FIELD_ID_CARS = "idCar";

    public AdsEntity ads;
    public AdsForm adsForm = null;
    public BasketCrudForm basketCrudForm;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher(URL_REDIRECTION).forward(request, response);

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idCar = Integer.parseInt(request.getParameter(FIELD_ID_CARS));


        if (this.basketCrudForm == null) {
            basketCrudForm = new BasketCrudForm();
        }
        CarsForm carsForm = new CarsForm();
        AdsEntity adsEntity = carsForm.searchAdsByIdCar(idCar);

        request.setAttribute("idAds", adsEntity.getId());
        adsForm = basketCrudForm.delBasket(request);


        request.setAttribute("adsForm", adsForm);
        response.sendRedirect(URL_REDIRECTION);                    // Cas d une redirection

//        this.getServletContext().getRequestDispatcher(URL_REDIRECTION).forward(request, response);

    }
}
