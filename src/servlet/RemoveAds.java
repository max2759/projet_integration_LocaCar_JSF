package servlet;

import forms.AdsForm;
import util.JPAutil;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

@WebServlet("/supprimer-annonce")
public class RemoveAds extends HttpServlet {

    public static final String REDIRECT_URL = "annonces";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AdsForm adsForm = new AdsForm();

        adsForm.removeAds(request);

        response.sendRedirect(REDIRECT_URL);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
