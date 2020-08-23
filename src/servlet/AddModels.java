package servlet;

import entities.BrandsEntity;
import entities.UsersEntity;
import forms.ModelsForm;
import services.BrandsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/ajouter-modeles")
public class AddModels extends HttpServlet {

    public static final String VUE = "/WEB-INF/addModels.jsp";
    public static final String URL_REDIRECT = "connexion";
    public static final String VUE2 = "ajouter-modeles";




    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ModelsForm modelsForm = new ModelsForm();

        modelsForm.addModels(request);

        response.sendRedirect(VUE2);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // récupération de la session
        HttpSession session = request.getSession();

        UsersEntity usersEntity = (UsersEntity) session.getAttribute("UserEntity");

        if (usersEntity != null) {
            if (!usersEntity.getRolesByIdRoles().getLabel().equals("Admin")) {
                response.sendRedirect(URL_REDIRECT);
            } else {

                // Liste d'objet
                List<BrandsEntity> brandsEntities;
                // Services
                BrandsService brandsService = new BrandsService();

                //Appel des méthodes qui retourne une liste d'objet
                brandsEntities = brandsService.displayBrands();

                request.setAttribute("brands", brandsEntities);

                this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
            }
        }else{
            response.sendRedirect(URL_REDIRECT);
        }

    }
}
