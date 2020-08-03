package forms;

import entities.CarsEntity;
import exceptions.CarsException;
import services.CarsService;
import util.JPAutil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CarsForm {
    private static final String FIELD_ID = "idCars";

    private String result;
    private Map<String, String> errors = new HashMap<String, String>();

    public String getResult() {

        return result;
    }

    public Map<String, String> getErrors() {

        return errors;
    }

    /**
     * Méthode de vérification de l'existence de l'ads
     *
     * @param request
     * @return
     */

    public CarsEntity checkCars(HttpServletRequest request) {

        CarsEntity cars = new CarsEntity();

        if (request.getParameter(FIELD_ID) != null) {
            int idCars = Integer.parseInt(request.getParameter(FIELD_ID));

            EntityManager em = JPAutil.createEntityManager("projet_bac_info2");

            // Recherche de l'Cars

            CarsService carsService = new CarsService();

            EntityTransaction tx = null;
            try {
                tx = em.getTransaction();
                tx.begin();
                cars = carsService.consulter(em, idCars);
                tx.commit();
            } catch (Exception ex) {
                if (tx != null && tx.isActive()) tx.rollback();
            } finally {
                em.close();
            }

            CarsException carsException = new CarsException();

            try {
                carsException.validationID(cars);
            } catch (Exception e) {
                setError(FIELD_ID, e.getMessage());
            }
        }
        else{
            setError(FIELD_ID, "Vide");
        }

        if (errors.isEmpty()) {
            result = "Succès";
        } else {
            result = "Échec";
        }

        return cars;
    }

    /*
     * Ajoute un message correspondant au champ spécifié à la map des errors.
     */
    private void setError(String field, String message) {
        errors.put(field, message);
    }

}