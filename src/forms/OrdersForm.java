package forms;

import entities.*;
import enumeration.EnumOrderStatut;
import enumeration.EnumTypesAds;
import exceptions.CarsException;
import exceptions.OrdersException;
import services.CarsService;
import services.ContractsService;
import services.OrdersService;
import services.UsersService;
import util.JPAutil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrdersForm {

    private static final String FIELD_ID = "idOrders";
    private static final String FIELD_ID_USERS = "idUsers";


    private String result;
    private Map<String, String> errors = new HashMap<String, String>();

    public String getResult() {

        return result;
    }

    public Map<String, String> getErrors() {

        return errors;
    }


    /**
     * Vérification de la commande
     *
     * @param request
     * @return
     */
    public OrdersEntity checkOrders(HttpServletRequest request) {

        OrdersEntity orders = null;

        if (request.getParameter(FIELD_ID) != null) {
            // On vérifie que c'est bien un nbr
            try {

                int idOrders = Integer.parseInt(request.getParameter(FIELD_ID));

                EntityManager em = JPAutil.createEntityManager("projet_bac_info2");

                // Recherche de l'ads

                OrdersService ordersService = new OrdersService();

                EntityTransaction tx = null;
                try {
                    tx = em.getTransaction();
                    tx.begin();
                    orders = ordersService.consult(em, idOrders);
                    tx.commit();
                } catch (Exception ex) {
                    if (tx != null && tx.isActive()) tx.rollback();
                } finally {
                    em.close();
                }

                OrdersException ordersException = new OrdersException();

                try {
                    ordersException.validationEntity(orders);
                } catch (Exception e) {
                    setError(FIELD_ID, e.getMessage());
                }
            } catch (NumberFormatException e) {
                setError(FIELD_ID, "Cette valeur n'est pas un chiffre, essaie encore !");

            }
        } else {
            setError(FIELD_ID, "Vide");
        }

        if (errors.isEmpty()) {
            result = "Succès";
        } else {
            result = "Échec";
        }
        return orders;
    }

    /**
     * Méthode pour sauvegarder l'entité
     *
     * @param request
     * @return
     */
    public OrdersEntity saveOrder(HttpServletRequest request, EnumOrderStatut orderStatut) {
        int idUsers = Integer.parseInt(request.getParameter(FIELD_ID_USERS));
        Date orderDate = new Date();

        EntityManager em = JPAutil.createEntityManager("projet_bac_info2");

        OrdersEntity order = new OrdersEntity();
        UsersEntity users = new UsersEntity();
        UsersService usersService = new UsersService();


        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            users = usersService.consult(em, idUsers);
            tx.commit();
        } catch (Exception ex) {
            if (tx != null && tx.isActive()) tx.rollback();
        } finally {
            em.close();
        }

        order.setUsersByIdUsers(users);
        order.setOrderDate(orderDate);
        order = changeStatut(order, orderStatut);
        return order;
    }

    /*
     * Ajoute un message correspondant au champ spécifié à la map des errors.
     */
    private void setError(String field, String message) {
        errors.put(field, message);
    }

    /**
     * Changement de statut de la commande
     *
     * @param ordersEntity
     * @param orderStatut
     */
    public OrdersEntity changeStatut(OrdersEntity ordersEntity, EnumOrderStatut orderStatut) {

        ordersEntity.setOrderStatut(orderStatut);
        return ordersEntity;
    }

    /**
     * Méthode de validation d'une commande
     *
     * @param idUser
     */
    public void validateOrder(int idUser) {
        OrdersService ordersService = new OrdersService();
        OrdersEntity ordersEntity = null;

        EntityManager em = JPAutil.createEntityManager("projet_bac_info2");

        // On recherche la commande par Id user
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            ordersEntity = ordersService.findOrderByIdUser(em, idUser);
            tx.commit();
        } catch (
                Exception ex) {
            if (tx != null && tx.isActive()) tx.rollback();
        } finally {
            em.close();
        }

        // On gère les exceptions
        OrdersException ordersException = new OrdersException();

        // On vérifie que la commande est valide.
        try {
            ordersException.validationEntity(ordersEntity);
        } catch (Exception e) {
            setError(FIELD_ID, e.getMessage());
        }

        if (errors.isEmpty()) {
            EnumOrderStatut enumOrderStatut = EnumOrderStatut.values()[1];
            ordersEntity = changeStatut(ordersEntity, enumOrderStatut);


            em = JPAutil.createEntityManager("projet_bac_info2");

            // On recherche la commande par Id user
            tx = null;
            try {
                tx = em.getTransaction();
                tx.begin();

                changeStatutCarAfterValidationOrder(ordersEntity.getId());

                // On check si y a pas d'erreur jusqu'à maintenant
                if (errors.isEmpty()) {

                    ordersService.mergeOrder(em, ordersEntity);

                    result = "Succès";
                } else {
                    result = "Échec";
                }
                tx.commit();
            } catch (
                    Exception ex) {
                if (tx != null && tx.isActive()) tx.rollback();
                result = "Échec";
            } finally {
                em.close();
            }


        } else {
            result = "Échec";
        }
    }

    /**
     * Méthode qui change le statut du véhicule lors de la validation d'une commande vente
     *
     * @param idOrder
     */
    public void changeStatutCarAfterValidationOrder(int idOrder) {
        ContractsService contractsService = new ContractsService();
        List<ContractsEntity> contractsEntities = null;
        CarsService carsService = new CarsService();
        EntityManager em = JPAutil.createEntityManager("projet_bac_info2");

        // On recherche la commande par Id user
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            contractsEntities = contractsService.findAllContractByIdOrder(em, idOrder);

            tx.commit();
        } catch (
                Exception ex) {
            if (tx != null && tx.isActive()) tx.rollback();
        } finally {
            em.close();
        }

        // On parcourt les contracts
        for (ContractsEntity value : contractsEntities
        ) {
            CarsEntity carsEntity = value.getCarsByIdCars();

            // On vérifie que la voiture est disponible sinon, on retourne un message d'erreur
            CarsException carsException = new CarsException();
            try {
                carsException.carNotActive(carsEntity);
            } catch (Exception e) {
                setError("car", e.getMessage());
                result = "Échec";
                return;
            }
            carsEntity.setActive(false);

            // On fait un merge de la voiture
            em = JPAutil.createEntityManager("projet_bac_info2");

            tx = null;
            try {
                tx = em.getTransaction();
                tx.begin();


                // Le cas d'une vente, on change le isActive du véhicule en 0 car le véhicule n'est plus disponible

                EnumTypesAds enumTypesAds = EnumTypesAds.values()[0];

                AdsEntity adsEntity = carsService.searchAds(em, carsEntity.getId());
                if (adsEntity.getTypesAds() == enumTypesAds) {
                    carsService.mergeCar(em, carsEntity);
                }
                tx.commit();
            } catch (
                    Exception ex) {
                if (tx != null && tx.isActive()) tx.rollback();
            } finally {
                em.close();
            }
        }
    }

    public OrdersEntity findOrderValidatedById(int idOrder) {
        OrdersEntity ordersEntity = null;
        OrdersService ordersService = new OrdersService();

        EntityManager em = JPAutil.createEntityManager("projet_bac_info2");

        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();

            //Vérification le id Order
            ordersEntity = ordersService.findOrderValidatedById(em, idOrder);
            OrdersException ordersException = new OrdersException();

            // Si aucune entité n'est trouvé on renvoit une exception
            try {
                ordersException.validationEntity(ordersEntity);
            } catch (Exception e) {
                setError(FIELD_ID, e.getMessage());
            }
            tx.commit();
        } catch (Exception ex) {
            if (tx != null && tx.isActive()) tx.rollback();
        } finally {
            em.close();
        }

        if (errors.isEmpty()) {
            result = "Succès";
        } else {
            result = "Echec";
        }
        return ordersEntity;

    }

    /**
     * Méthode pour annuler une commande
     *
     * @param request
     */
    public void deleteOrder(HttpServletRequest request) {

        int idOrder = Integer.parseInt(request.getParameter(FIELD_ID));
        OrdersEntity ordersEntity;
        OrdersService ordersService = new OrdersService();

        EntityManager em = JPAutil.createEntityManager("projet_bac_info2");

        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();

            //Vérification le id Order
            ordersEntity = findOrderValidatedById(idOrder);

            // Si aucune erreur, on change le statut en : canceled et on merge
            if (errors.isEmpty()) {

                EnumOrderStatut enumOrderStatut = EnumOrderStatut.CANCELED;
                ordersEntity.setOrderStatut(enumOrderStatut);
                ordersService.mergeOrder(em, ordersEntity);
                result = "Succès";
            } else {
                result = "Echec";
            }
            tx.commit();
        } catch (Exception ex) {
            if (tx != null && tx.isActive()) tx.rollback();
        } finally {
            em.close();
        }

    }

}

