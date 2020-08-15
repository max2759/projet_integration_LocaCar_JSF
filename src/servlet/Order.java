package servlet;

import com.sun.org.apache.xpath.internal.operations.Or;
import entities.ContractsEntity;
import entities.OrdersEntity;
import forms.ContractsForm;
import forms.OrdersForm;
import services.OrdersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/order")
public class Order extends HttpServlet {
    public static final String VUE = "/WEB-INF/order.jsp";
    public static final String FIELD_ID_ORDER = "idOrder";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }
}
