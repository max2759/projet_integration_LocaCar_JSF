package forms;

import entities.AdsEntity;
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
    private static final String FIELD_COLOR_CAR = "color";
    private static final String FIELD_RELEASEYEAR_CAR = "ReleaseYear";
    private static final String FIELD_KM_CAR = "kilometer";
    private static final String FIELD_HP_CAR = "horsePower";
    private static final String FIELD_CARTYPES_CAR = "carTypes";
    private static final String FIELD_BRANDS_CAR = "brands";
    private static final String FIELD_MODELS_CAR = "models";
    private static final String FIELD_FUEL_CAR = "fuel";

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

        CarsEntity cars = null;

        if (request.getParameter(FIELD_ID) != null) {
            // On vérifie que c'est bien un nbr
            try {
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
                    carsException.validationEntity(cars);
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

        return cars;
    }

    /*
     * Ajoute un message correspondant au champ spécifié à la map des errors.
     */
    private void setError(String field, String message) {
        errors.put(field, message);
    }

    public AdsEntity searchAdsByIdCar(int idCar) {

        AdsEntity ads = new AdsEntity();

        // Recherche de la voiture
        EntityManager em = JPAutil.createEntityManager("projet_bac_info2");

        CarsService carsService = new CarsService();

        EntityTransaction tx = null;


// Recherche de l'ads
        try {
            tx = em.getTransaction();
            tx.begin();
            ads = carsService.searchAds(em, idCar);
            tx.commit();
        } catch (Exception ex) {
            if (tx != null && tx.isActive()) tx.rollback();
        } finally {
            em.close();
        }

        return ads;
    }




}