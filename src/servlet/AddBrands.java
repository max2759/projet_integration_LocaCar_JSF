package servlet;

import entities.UsersEntity;
import forms.BrandsForm;
import forms.CarTypesForm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/ajouter-constructeur")
public class AddBrands extends HttpServlet {
    public static final String VUE = "/WEB-INF/addBrands.jsp";
    public static final String URL_REDIRECT = "connexion";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Appel du form qui traite les champs du formulaire
        BrandsForm brandsForm = new BrandsForm();

        //Ajout dans brands les champs du formulaire
        brandsForm.addBrands(request);

        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // récupération de la session
        HttpSession session = request.getSession();

        UsersEntity usersEntity = (UsersEntity) session.getAttribute("UserEntity");

        // affiche la page si l'utilisateur est connecté et est bien admin
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
