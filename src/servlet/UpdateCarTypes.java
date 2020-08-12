package servlet;

import forms.CarTypesForm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/modification-categorie")
public class UpdateCarTypes extends HttpServlet {
    public static final String VUE = "categories";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CarTypesForm carTypesForm = new CarTypesForm();

        carTypesForm.updateCategory(request);

        response.sendRedirect(VUE);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
