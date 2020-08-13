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
     * Methode pour rechercher une annonce à partir de l'id
     *
     * @param idAds
     * @return
     */
    public AdsEntity checkAds(int idAds) {
        AdsEntity ads = null;

        // On vérifie que c'est bien un nbr
        try {

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
                adsException.validationEntity(ads);
            } catch (Exception e) {
                setError(FIELD_ID, e.getMessage());
            }
        } catch (NumberFormatException e) {
            setError(FIELD_ID, "Cette valeur n'est pas un chiffre, essaie encore !");

        }

        if (errors.isEmpty()) {
            result = "Succès";
        } else {
            result = "Échec";
        }
        return ads;
    }

    /**
     * Méthode de vérification de l'existence de l'ads
     *
     * @param request
     * @return
     */

    public AdsEntity checkAds(HttpServletRequest request) {

        AdsEntity ads = null;

        if (request.getParameter(FIELD_ID) != null) {
            // On vérifie que c'est bien un nbr
            try {

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
                    adsException.validationEntity(ads);
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
        return ads;
    }

    /*
     * Ajoute un message correspondant au champ spécifié à la map des errors.
     */
    private void setError(String field, String message) {
        errors.put(field, message);
    }

}