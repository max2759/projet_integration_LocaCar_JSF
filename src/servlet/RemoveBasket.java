package servlet;


import entities.AdsEntity;
import entities.CarsEntity;
import forms.AdsForm;
import forms.BasketForm;
import forms.CarsForm;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/removeBasket")
public class RemoveBasket extends HttpServlet {

    public static final String VUE = "/WEB-INF/basket.jsp";

    public AdsEntity ads;
    public CarsEntity cars;
    public AdsForm adsForm = null;
    public CarsForm carsForm = null;
    public Map<String, Object> basket;
    public BasketForm basketForm;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idAds = request.getParameter("idAds");

        // Recherche de l'ads
        adsForm = new AdsForm();
        ads = adsForm.checkAds(request);

        // Si aucune voiture n'est trouvé on renvoit une erreur.
        if (adsForm.getErrors().isEmpty()) {
            HttpSession session = request.getSession();
            if (this.basketForm == null) {
                basketForm = new BasketForm(request);

            }
            basketForm.remove(idAds);
//            basket = basketForm.getBasket();

//            request.setAttribute("basket", basket);
        } else {
            request.setAttribute("adsForm", adsForm);
        }
/*
        // Si on a des erreurs, par exemple si il ne trouve la car ou l'ads
        if ((!(adsForm.getErrors().isEmpty())) || (!(carsForm.getErrors().isEmpty()))) {
            request.setAttribute("adsForm", adsForm);
            request.setAttribute("carsForm", carsForm);
        } else {
            request.setAttribute("cars", cars);
            request.setAttribute("ads", ads);
        }

 */
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
