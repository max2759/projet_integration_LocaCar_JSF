package forms;

import entities.AdsEntity;
import entities.OrdersEntity;
import enumeration.EnumOrderStatut;
import services.AdsService;
import services.OrdersService;
import util.JPAutil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Class pour créer la session du panier
 */
public class BasketForm {
    public static Map<String, AdsEntity> basket;
    public HttpSession session;
    private static final String FIELD_ID_USERS = "idUsers";

    /**
     * Constructeur qui crée la session si elle n'existe pas.
     *
     * @param request
     */
    public BasketForm(HttpServletRequest request) {

        /* Récupération de la session depuis la requête*/
        session = request.getSession();
    }

    /**
     * Méthode d'ajout au panier
     *
     * @param idAds
     * @param adsEntity
     */
    public void add(String idAds, AdsEntity adsEntity, HttpServletRequest request, EnumOrderStatut enumOrderStatut) {
        int idUsers = Integer.parseInt(request.getParameter(FIELD_ID_USERS));

        OrdersEntity ordersEntity;
        OrdersEntity oldOrder;
        OrdersForm ordersForm = new OrdersForm();
        OrdersService ordersService = new OrdersService();

        EntityManager em = JPAutil.createEntityManager("projet_bac_info2");


        oldOrder = ordersService.findOrderByIdUser(em, idUsers);
        ordersEntity = ordersForm.saveOrder(request, enumOrderStatut);

        setBasket(idAds, adsEntity);
        session.setAttribute("basket", this.basket);

        // sauvegarde du panier en DB
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            if (oldOrder == null) {
                ordersService.add(em, ordersEntity);
            }
            else{
                ordersService.merge(em.)
            }
            tx.commit();
        } catch (Exception ex) {
            if (tx != null && tx.isActive()) tx.rollback();
        } finally {
            em.close();
        }


    }

    /**
     * Méthode pour supprimer un produit du panier
     *
     * @param idAds
     */
    public void remove(String idAds) {
        unsetBasket(idAds);
        session.setAttribute("basket", this.basket);

    }

    /**
     * Méthode pour setter la map de panier. Elle est appellé par add
     *
     * @param idAds
     * @param adsEntity
     */
    public void setBasket(String idAds, AdsEntity adsEntity) {
        if (this.basket == null) {
            this.basket = new HashMap<>();
        }
        this.basket.put(idAds, adsEntity);
    }

    /**
     * Méthode pour unsetter la map de panier. Elle est appellé par remove
     *
     * @param idAds
     */
    public void unsetBasket(String idAds) {
        /*if (this.basket == null) {
            this.basket = new HashMap<>();
        }*/
        this.basket.remove(idAds);
    }

/*


    public Map getBasket() {
        return basket;
    }
*/
}