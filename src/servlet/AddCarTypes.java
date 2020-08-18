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

    public static final String ATT_CARTYPESENTITY = "carTypesEntity";
    public static final String ATT_FORM = "carTypesForm";
    public static final String VUE = "/WEB-INF/addCarTypes.jsp";
    public static final String URL_REDIRECT = "connexion";
    public Map<String, String> erreur;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* Objet formulaire */
        CarTypesForm carTypesForm = new CarTypesForm();

        /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
        CarTypesEntity carTypesEntity = carTypesForm.addCategory(request);
        erreur = carTypesForm.getErreurs();

        EntityManager em = JPAutil.createEntityManager("projet_bac_info2");

        if (erreur.isEmpty()) {
            CarTypesService carTypesService = new CarTypesService();
            EntityTransaction tx = null;

            try {
                tx = em.getTransaction();
                tx.begin();
                carTypesService.addCategory(em, carTypesEntity);
                tx.commit();
            } catch (Exception ex) {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            } finally {
                em.close();
            }
        }

        /* Stockage du form et de la bean dans request */
        request.setAttribute(ATT_FORM, carTypesForm);
        request.setAttribute(ATT_CARTYPESENTITY, carTypesEntity);

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
