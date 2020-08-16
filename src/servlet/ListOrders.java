package servlet;

import entities.OrdersEntity;
import entities.UsersEntity;
import forms.ListOrdersForm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/listOrders")
public class ListOrders extends HttpServlet {
    public static final String VUE = "/WEB-INF/listOrders.jsp";
    public static final String FIELD_ID_SEARCH = "idSearch";
    public static final String URL_REDIRECT = "connexion";


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // récupération de la session
        HttpSession session = request.getSession();

        UsersEntity usersEntity = (UsersEntity) session.getAttribute("UserEntity");

        if (usersEntity != null) {
            if (!usersEntity.getRolesByIdRoles().getLabel().equals("Admin")) {
                response.sendRedirect(URL_REDIRECT);
            } else {
                ListOrdersForm listOrdersForm = new ListOrdersForm();
                List<OrdersEntity> ordersEntities = listOrdersForm.searchOrders(request);

                request.setAttribute("listOrdersForm", listOrdersForm);
                request.setAttribute("ordersEntities", ordersEntities);

                this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
            }
        } else {
            response.sendRedirect(URL_REDIRECT);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

        // récupération de la session
        HttpSession session = request.getSession();

        UsersEntity usersEntity = (UsersEntity) session.getAttribute("UserEntity");

        if (usersEntity != null) {
            if (!usersEntity.getRolesByIdRoles().getLabel().equals("Admin")) {
                response.sendRedirect(URL_REDIRECT);
            } else {
                this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
            }
        } else {
            response.sendRedirect(URL_REDIRECT);
        }
    }
}
