package servlet;

import entities.BrandsEntity;
import entities.CarTypesEntity;
import entities.UsersEntity;
import forms.BrandsForm;
import services.BrandsService;
import services.CarTypesService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/constructeurs")
public class ShowBrands extends HttpServlet {

    public static final String VUE = "/WEB-INF/showBrands.jsp";
    public static final String URL_REDIRECT = "connexion";
    public static final String URL_REDIRECT_AFTER_UPDATE = "constructeurs";


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        BrandsForm brandsForm = new BrandsForm();

        brandsForm.updateBrands(request);

        response.sendRedirect(URL_REDIRECT_AFTER_UPDATE);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // récupération de la session
        HttpSession session = request.getSession();

        UsersEntity usersEntity = (UsersEntity) session.getAttribute("UserEntity");

        // On regarde si l'utilisateur est bien connecté et qu'il est admin pour afficher
        if ( usersEntity != null){
            if (!usersEntity.getRolesByIdRoles().getLabel().equals("Admin")){
                response.sendRedirect(URL_REDIRECT);
            }else {

                // Appel de la classe service de brands
                BrandsService brandsService = new BrandsService();


                // On mets les constructeurs dans une collection de type list
                List<BrandsEntity> brandsEntities;

                brandsEntities = brandsService.displayBrands();

                request.setAttribute("brands", brandsEntities);


                this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
            }
        }else{
            response.sendRedirect(URL_REDIRECT);
        }
    }
}
