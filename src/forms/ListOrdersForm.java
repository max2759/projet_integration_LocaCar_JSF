package forms;

import entities.OrdersEntity;
import exceptions.OrdersException;
import services.OrdersService;
import util.JPAutil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListOrdersForm {
    public static final String FIELD_ID_SEARCH = "idSearch";

    private String result;
    private Map<String, String> errors = new HashMap<String, String>();

    public String getResult() {

        return result;
    }

    public Map<String, String> getErrors() {

        return errors;
    }

    private void setError(String field, String message) {
        errors.put(field, message);
    }

    /**
     * Vérifie si l'entrée correspond à une ou plusieurs commandes
     *
     * @param request
     * @return
     */
    public List<OrdersEntity> searchOrders(HttpServletRequest request) {

        String username = request.getParameter(FIELD_ID_SEARCH);
        int idSearch = 0;

        // On vérifie que l'entrée n'est pas vide
        if (username.length() == 0) {
            setError(FIELD_ID_SEARCH, "Votre recherche est vide");
        }

        // On vérifie que l'entrée est un entier pour chercher aussi dans les id
        else if (username.matches("\\d*")) {
            idSearch = Integer.parseInt(request.getParameter(FIELD_ID_SEARCH));
        }

        // Si aucune erreur alors on cherche
        if (errors.isEmpty()) {
            OrdersService ordersService = new OrdersService();

            List<OrdersEntity> ordersEntity = null;
            EntityManager em = JPAutil.createEntityManager("projet_bac_info2");

            EntityTransaction tx = null;
            try {
                tx = em.getTransaction();
                tx.begin();
                ordersEntity = ordersService.findOrdersValidateByIdOrderAndByIdUserAndByUsername(em, idSearch, username);
                tx.commit();
            } catch (Exception ex) {
                if (tx != null && tx.isActive()) tx.rollback();
            } finally {
                em.close();
            }

            OrdersException ordersException = new OrdersException();

            try {
                ordersException.validationEntities(ordersEntity);
            } catch (Exception e) {
                setError(FIELD_ID_SEARCH, e.getMessage());
            }


            if (errors.isEmpty()) {
                result = "Succès";
            } else {
                result = "Échec";
            }

            return ordersEntity;
        }

        // S'il y a une erreur, on retourne un null
        else {
            return null;
        }
    }
}
