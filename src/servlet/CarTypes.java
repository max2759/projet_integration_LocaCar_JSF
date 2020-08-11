package servlet;

import entities.CarTypesEntity;
import forms.CarTypesForm;
import services.CarTypesService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/categories")
public class CarTypes extends HttpServlet {

    public static final String VUE = "/WEB-INF/carTypes.jsp";
    public static final String ATT_CARTYPESENTITY = "carTypesEntity";
    public List<CarTypesEntity> carTypesEntities;
    public CarTypesEntity carTypesEntity;

    CarTypesService carTypesService = new CarTypesService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* Objet formulaire*/
        CarTypesForm carTypesForm = new CarTypesForm();

        carTypesForm.deleteCategory(request);


        /*EntityManager em = JPAutil.createEntityManager("projet_bac_info2");


        EntityTransaction tx = null;

        try{
            tx = em.getTransaction();
            tx.begin();
            carTypesService.updateCarTypes(IdfromForm, updateCat);
            tx.commit();
        }catch(Exception e){
            if (tx != null && tx.isActive()){
                tx.rollback();
            }
        }finally {
            em.close();
        }*/


        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        carTypesEntities = carTypesService.displayCategory();

        request.setAttribute("category", carTypesEntities);

        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

    }
}
