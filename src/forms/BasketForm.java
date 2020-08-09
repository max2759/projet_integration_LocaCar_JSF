package forms;

import entities.AdsEntity;
import entities.ContractsEntity;
import entities.OrdersEntity;
import enumeration.EnumOrderStatut;
import services.AdsService;
import services.ContractsService;
import services.OrdersService;
import util.JPAutil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Class pour créer la session du panier
 */
public class BasketForm {
    public static Map<String, AdsEntity> basket;
    public HttpSession session;
    private static final String FIELD_ID_USERS = "idUsers";
    private static final String FIELD_DATE_START = "dateStart";
    private static final String FIELD_DATE_END = "dateEnd";

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
    public void add(String idAds, AdsEntity adsEntity, HttpServletRequest request, EnumOrderStatut enumOrderStatut, int idContractType) throws ParseException {
        int idUsers = Integer.parseInt(request.getParameter(FIELD_ID_USERS));

        //Création d'un format date
        String dateStartStr = request.getParameter(FIELD_DATE_START);
        String dateEndStr = request.getParameter(FIELD_DATE_END);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //On crée les variables de date
        Date dateStart = sdf.parse(dateStartStr);
        Date dateEnd = sdf.parse(dateEndStr);


        OrdersEntity ordersEntity;
        ContractsEntity contractsEntity;

        OrdersEntity oldOrder;
        ContractsEntity oldContract;

        OrdersForm ordersForm = new OrdersForm();
        ContractsForm contractsForm = new ContractsForm();

        OrdersService ordersService = new OrdersService();
        ContractsService contractsService = new ContractsService();

        //création de l'em
        EntityManager em = JPAutil.createEntityManager("projet_bac_info2");


        // Recherche d'une ancienne sauvegarde
        oldOrder = ordersService.findOrderByIdUser(em, idUsers);
        ordersEntity = ordersForm.saveOrder(request, enumOrderStatut);


        // Recherche d'un ancien contract sauvegardé
        if (oldOrder == null) {
            oldContract = contractsService.findContractByIdOrder(em, ordersEntity.getId());
        }
        else{
            oldContract = contractsService.findContractByIdOrder(em, oldOrder.getId());
        }
        contractsEntity = contractsForm.saveContract(ordersEntity.getId(), adsEntity.getCarsByIdCars().getId(), idContractType, dateStart, dateEnd, adsEntity.getPrice());

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
                // MAJ de l'order
                oldOrder.setOrderDate(ordersEntity.getOrderDate());
                oldOrder.setOrderStatut(ordersEntity.getOrderStatut());

                ordersService.mergeOrder(em, oldOrder);
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