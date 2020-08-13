package servlet;

import com.sun.deploy.net.HttpRequest;
import entities.AdsEntity;
import entities.CarsEntity;
import entities.ContractsEntity;
import forms.AdsForm;
import forms.BasketCrudForm;
import forms.BasketForm;
import forms.CarsForm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@WebServlet("/basket")

public class Basket extends HttpServlet {
    public static final String VUE = "/WEB-INF/basket.jsp";

    public AdsEntity ads;

    private double totalPrice = 0;
    public AdsForm adsForm = null;
    public CarsForm carsForm = null;
    public BasketCrudForm basketCrudForm;
    public List<ContractsEntity> contractsEntity;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Integer idUser;
        if (session.getAttribute("User") != null) {
            idUser = Integer.parseInt(session.getAttribute("User").toString());
        } else {
            idUser = 0;
        }


        adsForm = new AdsForm();
        carsForm = new CarsForm();

        carsForm.checkCars(request);
        adsForm.checkAds(request);

        if (this.basketCrudForm == null) {
            basketCrudForm = new BasketCrudForm();
        }
        contractsEntity = basketCrudForm.listContracts(request, idUser);

        // Calcul du prix total du panier
        generateTotalPrice();


        request.setAttribute("totalPrice", totalPrice);
        request.setAttribute("basket", contractsEntity);
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String startDate = request.getParameter("dateStart");
        String endDate = request.getParameter("dateEnd");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


        HttpSession session = request.getSession();
        Integer idUser;
        if (session.getAttribute("User") != null) {
            idUser = Integer.parseInt(session.getAttribute("User").toString());
        } else {
            idUser = 0;
        }

        if (this.basketCrudForm == null) {
            basketCrudForm = new BasketCrudForm();
        }

        // On initialise les dates de début et fin pour le contract
        try {
            Date dateStart = new Date();
            Date dateEnd;
            if (startDate != null) {
                dateStart = sdf.parse(startDate);
            }
            if (endDate == null) {
                dateEnd = null;
            } else {
                dateEnd = sdf.parse(endDate);
            }
            // On l'ajoute au panier
            adsForm = basketCrudForm.addBasket(request, dateStart, dateEnd);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        contractsEntity = basketCrudForm.listContracts(request, idUser);

        // Calcul du prix total du panier
        generateTotalPrice();

        request.setAttribute("adsForm", adsForm);

        request.setAttribute("basket", contractsEntity);
        request.setAttribute("totalPrice", getTotalPrice());
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);


    }

    /**
     * Calcul du prix total
     *
     * @return
     */
    public double generateTotalPrice() {
        this.totalPrice = 0;
        for (ContractsEntity value : contractsEntity
        ) {
            setTotalPrice(value.getFinalPrice());

        }
        return getTotalPrice();
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice += totalPrice;
    }

}

