package forms;

import entities.AdsEntity;
import exceptions.AdsException;
import services.AdsService;
import util.JPAutil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class AdsForm {
    private static final String FIELD_ID = "idAds";

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

    public AdsEntity checkAds(HttpServletRequest request) {

        AdsEntity ads = new AdsEntity();

        if (request.getParameter(FIELD_ID) != null) {
            int idAds = Integer.parseInt(request.getParameter(FIELD_ID));

            EntityManager em = JPAutil.createEntityManager("projet_bac_info2");

            // Recherche de l'ads

            AdsService adsService = new AdsService();

            EntityTransaction tx = null;
            try {
                tx = em.getTransaction();
                tx.begin();
                ads = adsService.consulter(em, idAds);
                tx.commit();
            } catch (Exception ex) {
                if (tx != null && tx.isActive()) tx.rollback();
            } finally {
                em.close();
            }

            AdsException adsException = new AdsException();

            try {
                adsException.validationID(ads);
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

        return ads;
    }

    /*
     * Ajoute un message correspondant au champ spécifié à la map des errors.
     */
    private void setError(String field, String message) {
        errors.put(field, message);
    }

}