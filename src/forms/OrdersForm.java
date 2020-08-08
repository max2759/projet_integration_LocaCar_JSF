package forms;

import entities.AdsEntity;
import entities.OrdersEntity;
import entities.UsersEntity;
import enumeration.EnumOrderStatut;
import exceptions.OrdersException;
import services.OrdersService;
import services.UsersService;
import util.JPAutil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
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

    private static String getValeurChamp(HttpServletRequest request, String nomChamp) {
        String valeur = request.getParameter(nomChamp);
        if (valeur == null || valeur.trim().length() == 0) {
            return null;
        } else {
            return valeur.trim();
        }
    }
}

