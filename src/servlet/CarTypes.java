package servlet;

import entities.CarTypesEntity;
import entities.UsersEntity;
import services.CarTypesService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/categories")
public class CarTypes extends HttpServlet {

    public static final String VUE = "/WEB-INF/carTypes.jsp";
    public static final String URL_REDIRECT = "connexion";


    CarTypesService carTypesService = new CarTypesService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // récupération de la session
        HttpSession session = request.getSession();

        UsersEntity usersEntity = (UsersEntity) session.getAttribute("UserEntity");

        if ( usersEntity != null){
            if (!usersEntity.getRolesByIdRoles().getLabel().equals("Admin")){
                response.sendRedirect(URL_REDIRECT);
            }else {

                List<CarTypesEntity> carTypesEntities = null;

                carTypesEntities = carTypesService.displayCategory();

                request.setAttribute("category", carTypesEntities);


                this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
            }
        }else{
            response.sendRedirect(URL_REDIRECT);
        }


    }
}
