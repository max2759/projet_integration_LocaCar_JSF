package servlet;

import entities.UsersEntity;
import forms.OrdersForm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/deleteOrders")

public class DeleteOrders extends HttpServlet {

    public static final String VUE = "/WEB-INF/deleteOrders.jsp";
    public static final String URL_REDIRECT = "listOrders";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(URL_REDIRECT);
//        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // récupération de la session
        HttpSession session = request.getSession();

        UsersEntity usersEntity = (UsersEntity) session.getAttribute("UserEntity");

        if (usersEntity != null) {
            if (!usersEntity.getRolesByIdRoles().getLabel().equals("Admin")) {
                response.sendRedirect(URL_REDIRECT);
            } else {
                OrdersForm ordersForm = new OrdersForm();
                ordersForm.deleteOrder(request);

                request.setAttribute("ordersForm", ordersForm);

                this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

            }
        } else {
            response.sendRedirect(URL_REDIRECT);
        }
    }
}
