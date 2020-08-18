package servlet;

import forms.CarTypesForm;
import util.JPAutil;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/supprimer-categorie")
public class DeleteCarTypes extends HttpServlet {

    public static final String VUE = "categories";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = JPAutil.createEntityManager("projet_bac_info2");
        CarTypesForm carTypesForm = new CarTypesForm();

        carTypesForm.deleteCategory(request);

        response.sendRedirect(VUE);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
