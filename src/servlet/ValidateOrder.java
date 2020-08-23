package servlet;

import forms.OrdersForm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/validateOrder")

public class ValidateOrder extends HttpServlet {
    public static final String URL_REDIRECTION = "/WEB-INF/validateOrder.jsp";         // Cas d une redirection
    public static final String FIELD_ID_USER = "idUser";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(URL_REDIRECTION);
       
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idUser = Integer.parseInt(request.getParameter(FIELD_ID_USER));

        OrdersForm ordersForm = new OrdersForm();

        ordersForm.validateOrder(idUser);

        request.setAttribute("ordersForm", ordersForm);
//        response.sendRedirect(URL_REDIRECTION);                    // Cas d une redirection

        this.getServletContext().getRequestDispatcher(URL_REDIRECTION).forward(request, response);

    }
}
