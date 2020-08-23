package forms;

import entities.AdsEntity;
import entities.CarsEntity;
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
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class BasketForm {

    private static final String FIELD_ID_USERS = "idUsers";
    private static final String FIELD_LOCATION_DAYS = "locationDays";

    /**
     * Méthode pour lister les contracts par id User
     *
     * @param idUser
     * @return
     */
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
     * @param ordersEntity OrdersEntity
     * @param carsEntity   carsEntity
     * @return void
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
     * @param adsEntity
     * @param request
     * @param enumOrderStatut
     * @param idContractType
     * @param dateStart
     * @param dateEnd
     * @throws ParseException
     */
    public void add(AdsEntity adsEntity, HttpServletRequest request, EnumOrderStatut enumOrderStatut, int idContractType, Date dateStart, Date dateEnd) throws ParseException {
        int idUsers = Integer.parseInt(request.getParameter(FIELD_ID_USERS));

        // On récupère le prix
        int locationDays = Integer.parseInt(request.getParameter(FIELD_LOCATION_DAYS));
        Double finalPrice = adsEntity.getPrice() * locationDays;


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

        // sauvegarde du panier en DB
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            if (oldOrder == null) {
                ordersService.add(em, ordersEntity);
                oldOrder = ordersService.findOrderByIdUser(em, idUsers);
                contractsEntity = contractsForm.saveContract(oldOrder.getId(), adsEntity.getCarsByIdCars().getId(), idContractType, dateStart, dateEnd, finalPrice);

                contractsService.add(em, contractsEntity);
            } else {
                // MAJ de l'order
                oldOrder.setOrderDate(ordersEntity.getOrderDate());
                oldOrder.setOrderStatut(ordersEntity.getOrderStatut());

                // update de l'order et du contract
                ordersService.mergeOrder(em, oldOrder);
                oldOrder = ordersService.findOrderByIdUser(em, idUsers);

                contractsEntity = contractsForm.saveContract(oldOrder.getId(), adsEntity.getCarsByIdCars().getId(), idContractType, dateStart, dateEnd, finalPrice);
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
        AdsEntity adsEntity;
        CarsEntity carsEntity = null;

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

    }
}