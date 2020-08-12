package forms;

import entities.CarTypesEntity;
import exceptions.CarTypesException;
import services.CarTypesService;
import util.JPAutil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CarTypesForm {

    private static final String CATEGORY_FIELD = "category";
    private static final String UPDATECATEGORY_FIELD = "updateCat";
    private static final String IDUPDATECATEGORY_FIELD = "idCategory";
    private static final String DELETE_FIELD = "categoryDelete";

    CarTypesEntity carTypes = new CarTypesEntity();

    private String resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();

    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    /*public void deleteCarTypes(HttpServletRequest request){

        int idDelCat = Integer.parseInt(request.getParameter(DELETE_FIELD));
        EntityManager em = JPAutil.createEntityManager("projet_bac_info2");

        CarTypesService cts = null;

        //recherche de la catégorie

        carTypes = cts.findCarTypesById(em, idDelCat);

        cts.deleteCarTypes(em, carTypes);

    }*/

    public CarTypesEntity addCategory(HttpServletRequest request) {

        String category = getValeurChamp(request, CATEGORY_FIELD);

        CarTypesException ctE = new CarTypesException();

        try {
            ctE.categoryNoNumber(category);
        } catch (Exception e) {
            setErreur(CATEGORY_FIELD, e.getMessage());
        }
        carTypes.setLabel(category);

        if (erreurs.isEmpty()) {
            resultat = "Catégorie ajoutée";
        } else {
            resultat = "Echec d'ajout dans catégorie";
        }
        return carTypes;
    }

    /**
     * Modification de la catégorie
     * @param request
     * @return
     */
    public void updateCategory(HttpServletRequest request) {

        String updateCat = getValeurChamp(request, UPDATECATEGORY_FIELD);
        int idUpdateCat = Integer.parseInt(request.getParameter(IDUPDATECATEGORY_FIELD));

        EntityManager em = JPAutil.createEntityManager("projet_bac_info2");

        CarTypesEntity carTypesEntity;
        CarTypesService carTypesService = new CarTypesService();

        // Recherche la catégorie en fonction de l'id et ajout de celle ci
        EntityTransaction tx = null;
        try{
            tx = em.getTransaction();
            tx.begin();
            carTypesEntity = carTypesService.findCarTypesById(em, idUpdateCat);
            carTypesEntity.setLabel(updateCat);
            carTypesService.updateCarTypes(em, carTypesEntity);
            tx.commit();
        } catch (Exception ex) {
            if (tx != null && tx.isActive()) tx.rollback();
        } finally {
            em.close();
        }

    }




    /**
     * Ajout message d'erreur
     *
     * @param field
     * @param message
     */
    private void setErreur(String field, String message) {
        erreurs.put(field, message);
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
