package forms;

import entities.AdsEntity;
import entities.CarsEntity;
import entities.ContractsEntity;
import entities.OrdersEntity;
import enumeration.EnumOrderStatut;
import services.AdsService;
import services.CarsService;
import services.ContractsService;
import services.OrdersService;
import util.JPAutil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class pour créer la session du panier
 */
public class BasketForm {
    public static Map<String, AdsEntity> basket;
//    public HttpSession session;
    private static final String FIELD_ID_USERS = "idUsers";

    /**
     * Constructeur qui crée la session si elle n'existe pas.
     *
     * @param request
     */
    public BasketForm(HttpServletRequest request) {

        /* Récupération de la session depuis la requête*/
//        session = request.getSession();
    }

    public List<ContractsEntity> listContracts(int idUser) {
        ContractsService contractsService = new ContractsService();
        List<ContractsEntity> contractsEntities = null;
        //création de l'em
        EntityManager em = JPAutil.createEntityManager("projet_bac_info2");


        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();

           contractsEntities = contractsService.findAllContractsByIdUser(em, idUser);

            tx.commit();
        } catch (
                Exception ex) {
            if (tx != null && tx.isActive()) tx.rollback();
        } finally {
            em.close();
        }

        return contractsEntities;
    }

    /**
     * Recherche de contract sur base de order et de car
     *
     * @param ordersEntity
     * @param carsEntity
     * @return
     */
    public ContractsEntity searchContract(OrdersEntity ordersEntity, CarsEntity carsEntity) {

        ContractsService contractsService = new ContractsService();
        ContractsEntity contractsEntity = new ContractsEntity();
        int idOrder = ordersEntity.getId();
        int idCar = carsEntity.getId();
        //création de l'em
        EntityManager em = JPAutil.createEntityManager("projet_bac_info2");

        // Recherche de l'order :
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();

            contractsEntity = contractsService.findContractByIdOrderAndByIdCar(em, idOrder, idCar);

            tx.commit();
        } catch (Exception ex) {
            if (tx != null && tx.isActive()) tx.rollback();
        } finally {
            em.close();
        }
        return contractsEntity;
    }

    /**
     * Méthode d'ajout au panier
     *
     * @param idAds
     * @param adsEntity
     */
    public void add(String idAds, AdsEntity adsEntity, HttpServletRequest request, EnumOrderStatut enumOrderStatut, int idContractType, Date dateStart, Date dateEnd) throws ParseException {
        int idUsers = Integer.parseInt(request.getParameter(FIELD_ID_USERS));

        OrdersEntity ordersEntity;
        ContractsEntity contractsEntity;

        OrdersEntity oldOrder;
        ContractsEntity oldContract = null;

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
        if (oldOrder != null) {
            oldContract = searchContract(oldOrder, adsEntity.getCarsByIdCars());
        }
//        contractsEntity = contractsForm.saveContract(ordersEntity.getId(), adsEntity.getCarsByIdCars().getId(), idContractType, dateStart, dateEnd, adsEntity.getPrice());
/*

        setBasket(idAds, adsEntity);
        session.setAttribute("basket", this.basket);
*/

        // sauvegarde du panier en DB
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            if (oldOrder == null) {
                ordersService.add(em, ordersEntity);
                oldOrder = ordersService.findOrderByIdUser(em, idUsers);
                contractsEntity = contractsForm.saveContract(oldOrder.getId(), adsEntity.getCarsByIdCars().getId(), idContractType, dateStart, dateEnd, adsEntity.getPrice());

                contractsService.add(em, contractsEntity);
            } else {
                // MAJ de l'order
                oldOrder.setOrderDate(ordersEntity.getOrderDate());
                oldOrder.setOrderStatut(ordersEntity.getOrderStatut());

                // update de l'order et du contract
                ordersService.mergeOrder(em, oldOrder);
                oldOrder = ordersService.findOrderByIdUser(em, idUsers);

                contractsEntity = contractsForm.saveContract(oldOrder.getId(), adsEntity.getCarsByIdCars().getId(), idContractType, dateStart, dateEnd, adsEntity.getPrice());
                oldContract = searchContract(oldOrder, adsEntity.getCarsByIdCars());
                if (oldContract == null) {
                    contractsService.add(em, contractsEntity);
                } else {

                    // MAJ du contract
                    oldContract.setDateStart(contractsEntity.getDateStart());
                    oldContract.setDateEnd(contractsEntity.getDateEnd());
                    oldContract.setFinalPrice(contractsEntity.getFinalPrice());
                    contractsService.mergeContract(em, oldContract);
                }
                /*
                if (oldContract.getId() != contractsEntity.getId()) {
                    contractsService.add(em, contractsEntity);
                } else {

                    // MAJ du contract
                    oldContract.setDateStart(contractsEntity.getDateStart());
                    oldContract.setDateEnd(contractsEntity.getDateEnd());
                    oldContract.setFinalPrice(contractsEntity.getFinalPrice());
                    contractsService.mergeContract(em, oldContract);
                }*/
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
    public void remove(int idAds, int idUsers) {

        OrdersEntity oldOrder = null;
        AdsEntity adsEntity = null;
        CarsEntity carsEntity = null;
        int idCar = 0;
        //création de l'em
        EntityManager em = JPAutil.createEntityManager("projet_bac_info2");

        // Recherche de l'order :
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();

            OrdersService ordersService = new OrdersService();
            oldOrder = ordersService.findOrderByIdUser(em, idUsers);

            tx.commit();
        } catch (Exception ex) {
            if (tx != null && tx.isActive()) tx.rollback();
        } finally {
            em.close();
        }

        // Recherche de la car :
        em = JPAutil.createEntityManager("projet_bac_info2");

        tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();


            // On recherche l'annonce pour trouver la voiture
            AdsService adsService = new AdsService();
            adsEntity = adsService.consulter(em, idAds);

            // On a l'annonce, maintenant on cherche l'id de la voiture
            carsEntity = adsEntity.getCarsByIdCars();
            idCar = carsEntity.getId();

            tx.commit();
        } catch (Exception ex) {
            if (tx != null && tx.isActive()) tx.rollback();
        } finally {
            em.close();
        }


        ContractsService contractsService = new ContractsService();

        ContractsEntity contractsEntity = searchContract(oldOrder, carsEntity);
        // On efface le contract trouvé !!
        em = JPAutil.createEntityManager("projet_bac_info2");
        tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            contractsService.removeContract(em, contractsEntity);
            tx.commit();
        } catch (Exception ex) {
            if (tx != null && tx.isActive()) tx.rollback();
        } finally {
            em.close();
        }

        // On réinitialise les sessions
/*
        unsetBasket(Integer.toString(idAds));
        session.setAttribute("basket", this.basket);
*/

    }
/*

    */
/**
     * Méthode pour setter la map de panier. Elle est appellé par add
     *
     * @param idAds
     * @param adsEntity
     *//*

    public void setBasket(String idAds, AdsEntity adsEntity) {
        if (this.basket == null) {
            this.basket = new HashMap<>();

*/
/*
            ContractsService contractsService = new ContractsService();
            List<ContractsEntity> contractsEntities;
            //création de l'em
            EntityManager em = JPAutil.createEntityManager("projet_bac_info2");
            EntityTransaction tx = null;
            try {
                tx = em.getTransaction();
                tx.begin();

                contractsEntities = contractsService.listAll(em, idUser);

                tx.commit();
            } catch (Exception ex) {
                if (tx != null && tx.isActive()) tx.rollback();
            } finally {
                em.close();
            }
*//*


        }
        this.basket.put(idAds, adsEntity);
    }

    */
/**
     * Méthode pour unsetter la map de panier. Elle est appellé par remove
     *
     * @param idAds
     *//*

    public void unsetBasket(String idAds) {
        */
/*if (this.basket == null) {
            this.basket = new HashMap<>();
        }*//*

        this.basket.remove(idAds);
    }
*/

/*


    public Map getBasket() {
        return basket;
    }
*/
}