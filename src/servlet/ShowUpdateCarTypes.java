package servlet;

import entities.*;
import enumeration.EnumFuel;
import enumeration.EnumTypesAds;
import forms.AdsForm;
import forms.CarTypesForm;
import services.BrandsService;
import services.CarTypesService;
import services.ModelsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/modification-categorie")
public class ShowUpdateCarTypes extends HttpServlet {

    public static final String VUE = "/WEB-INF/UpdateCarTypes.jsp";
    public static final String URL_REDIRECT = "categories";


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // récupération de la session
        HttpSession session = request.getSession();

        UsersEntity usersEntity = (UsersEntity) session.getAttribute("UserEntity");

        // On récupère l'id de la catégorie qu'on parse en INT
        String idCarTypes = request.getParameter("idCategory");
        int idCategory = Integer.parseInt(idCarTypes);

        // On regarde si l'utilisateur est connecté
        if (usersEntity != null) {

            // appel de carTypesForm
            CarTypesForm carTypesForm = new CarTypesForm();

            CarTypesEntity carTypesEntity;

            // Appel méthode showCarTypesById qui retourne une catégorie en fonction de son id
            carTypesEntity = carTypesForm.showCarTypesById(idCategory);

            request.setAttribute("category", carTypesEntity);

            this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
        } else {
            response.sendRedirect(URL_REDIRECT);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
