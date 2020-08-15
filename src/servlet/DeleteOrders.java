package servlet;

import entities.AdsEntity;
import entities.OrdersEntity;
import enumeration.EnumOrderStatut;
import forms.AdsForm;
import forms.BasketCrudForm;
import forms.CarsForm;
import forms.OrdersForm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/deleteOrders")

public class DeleteOrders extends HttpServlet {

    public static final String VUE = "/WEB-INF/deleteOrders.jsp";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        OrdersForm ordersForm = new OrdersForm();
        ordersForm.deleteOrder(request);

        request.setAttribute("ordersForm", ordersForm);
//        response.sendRedirect(URL_REDIRECTION);                    // Cas d une redirection

        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

    }
}
