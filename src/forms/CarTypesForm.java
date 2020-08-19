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

    public void deleteCategory(HttpServletRequest request){

        int idDeleteCat = Integer.parseInt(getValeurChamp(request, DELETE_FIELD));

        EntityManager em = JPAutil.createEntityManager("projet_bac_info2");

        CarTypesEntity carTypesEntity;
        CarTypesService carTypesService = new CarTypesService();

        EntityTransaction tx = null;
        try{
            tx = em.getTransaction();
            tx.begin();
            carTypesEntity = carTypesService.consult(em, idDeleteCat);
            carTypesService.deleteCarTypes(em, carTypesEntity);
            tx.commit();
        } catch (Exception ex) {
            if (tx != null && tx.isActive()) tx.rollback();
        } finally {
            em.close();
        }


    }

    public CarTypesEntity addCategory(HttpServletRequest request) {

        String category = getValeurChamp(request, CATEGORY_FIELD);
        EntityManager em = JPAutil.createEntityManager("projet_bac_info2");


        CarTypesException ctE = new CarTypesException();
        CarTypesService carTypesService = new CarTypesService();

        try {
            ctE.categoryNoNumber(category);
            carTypesService.checkCarTypesEntity(em, category);
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
    public CarTypesEntity updateCategory(HttpServletRequest request) {

        String updateCat = getValeurChamp(request, UPDATECATEGORY_FIELD);
        int idUpdateCat = Integer.parseInt(request.getParameter(IDUPDATECATEGORY_FIELD));

        EntityManager em = JPAutil.createEntityManager("projet_bac_info2");

        CarTypesEntity carTypesEntity = new CarTypesEntity();
        CarTypesService carTypesService = new CarTypesService();

        EntityTransaction tx = null;
        try{
            tx = em.getTransaction();
            tx.begin();
            carTypesEntity = carTypesService.consult(em, idUpdateCat);
            carTypesEntity.setLabel(updateCat);
            carTypesService.updateCarTypes(em, carTypesEntity);
            tx.commit();
        } catch (Exception ex) {
            if (tx != null && tx.isActive()) tx.rollback();
        } finally {
            em.close();
        }

        return carTypes;

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
