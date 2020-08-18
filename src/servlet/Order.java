package servlet;

import entities.ContractsEntity;
import entities.OrdersEntity;
import entities.UsersEntity;
import forms.ContractsForm;
import forms.OrdersForm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/order")
public class Order extends HttpServlet {
    public static final String VUE = "/WEB-INF/order.jsp";
    public static final String FIELD_ID_ORDER = "idOrder";
    public static final String URL_REDIRECT = "listOrders";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // récupération de la session
        HttpSession session = request.getSession();

        UsersEntity usersEntity = (UsersEntity) session.getAttribute("UserEntity");

        if (usersEntity != null) {
            if (!usersEntity.getRolesByIdRoles().getLabel().equals("Admin")) {
                response.sendRedirect(URL_REDIRECT);
            } else {
                int idOrder = Integer.parseInt(request.getParameter(FIELD_ID_ORDER));

                OrdersForm ordersForm = new OrdersForm();
                ContractsForm contractsForm = new ContractsForm();

                OrdersEntity ordersEntity = ordersForm.findOrderValidatedById(idOrder);
                List<ContractsEntity> contractsEntityList = contractsForm.findAllContractByIdOrder(idOrder);


                request.setAttribute("ordersForm", ordersForm);
                request.setAttribute("ordersEntity", ordersEntity);
                request.setAttribute("contractsEntityList", contractsEntityList);

                this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

            }
        } else {
            response.sendRedirect(URL_REDIRECT);
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(URL_REDIRECT);
//        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }
}
