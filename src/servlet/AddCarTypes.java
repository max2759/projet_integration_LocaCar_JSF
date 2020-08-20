package servlet;

import entities.CarTypesEntity;
import entities.UsersEntity;
import forms.CarTypesForm;
import services.CarTypesService;
import util.JPAutil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet("/ajouter-categorie")
public class AddCarTypes extends HttpServlet {

    /*public static final String ATT_CARTYPESENTITY = "carTypesEntity";
    public static final String ATT_FORM = "carTypesForm";*/
    public static final String VUE = "/WEB-INF/addCarTypes.jsp";
    public static final String URL_REDIRECT = "connexion";
    public static final String URL_REDIRECT_TO_CATEGORY = "/WEB-INF/carTypes.jsp";
    public Map<String, String> erreur;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        CarTypesForm carTypesForm = new CarTypesForm();

        carTypesForm.addCategory(request);

        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        UsersEntity usersEntity = (UsersEntity) session.getAttribute("UserEntity");
        if (usersEntity != null) {
            if (!usersEntity.getRolesByIdRoles().getLabel().equals("Admin")) {
                response.sendRedirect(URL_REDIRECT);
            } else {
                this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
            }
        }else{
            response.sendRedirect(URL_REDIRECT);
        }
    }
}
