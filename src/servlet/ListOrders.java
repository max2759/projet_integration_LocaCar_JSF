package servlet;

import entities.OrdersEntity;
import forms.ListOrdersForm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/listOrders")
public class ListOrders extends HttpServlet {
    public static final String VUE = "/WEB-INF/listOrders.jsp";
    public static final String FIELD_ID_SEARCH = "idSearch";


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        ListOrdersForm listOrdersForm = new ListOrdersForm();
        List<OrdersEntity> ordersEntities = listOrdersForm.searchOrders(request);

        request.setAttribute("listOrdersForm", listOrdersForm);
        request.setAttribute("ordersEntities", ordersEntities);

        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }
}
