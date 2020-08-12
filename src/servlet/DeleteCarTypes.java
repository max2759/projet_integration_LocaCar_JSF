package servlet;

import entities.CarTypesEntity;
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
import java.io.IOException;

@WebServlet("/supprimer-categorie")
public class DeleteCarTypes extends HttpServlet {

    public static final String URL_REDIRECTION = "categories";
    CarTypesEntity carTypesEntity = null;
    CarTypesForm ctf = null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = JPAutil.createEntityManager("projet_bac_info2");

        ctf.deleteCarTypes(request);

        request.setAttribute( "ctf", ctf);
        response.sendRedirect( URL_REDIRECTION);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
