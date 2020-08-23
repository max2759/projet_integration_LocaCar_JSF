package forms;

import entities.BrandsEntity;
import entities.CarTypesEntity;
import entities.ModelsEntity;
import services.BrandsService;
import services.CarTypesService;
import services.ModelsService;
import util.JPAutil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ModelsForm {

    // Appel de l'em
    EntityManager em = JPAutil.createEntityManager("projet_bac_info2");

    // Les entités liés
    BrandsEntity brandsEntity = new BrandsEntity();
    BrandsService brandsService = new BrandsService();
    ModelsEntity modelsEntity = new ModelsEntity();
    ModelsService modelsService = new ModelsService();

    // Les champs
    private static final String BRANDS_MODELS_FIELD = "brandsModels";
    private static final String MODELS_FIELD = "modelsAdd";
    private static final String MODELS_FIELD_TO_UPDATE = "modelLabelToUpdate";
    private static final String MODELS_FIELD_ID_TO_UPDATE = "idModelsToUpdate";



    private String resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();

    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    /**
     * Ajout d'un modèle de véhicule dans l'entité modèle
     * @param request
     * @return
     */
    public ModelsEntity addModels(HttpServletRequest request){
        String models = getValeurChamp(request, MODELS_FIELD);
        int brandsId = Integer.parseInt(getValeurChamp(request, BRANDS_MODELS_FIELD));

        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();

            // On récupère l'id du constructeur
            brandsEntity = brandsService.consultBrands(em, brandsId);

            // on ajoute les champs dans modèles
            modelsEntity.setBrandsByIdBrands(brandsEntity);
            modelsEntity.setLabel(models);

            // on insère les données dans modèle
            modelsService.addModels(em, modelsEntity);
            tx.commit();
        } catch (Exception ex) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
        } finally {
            em.close();
        }

        return modelsEntity;
    }

    /**
     * Modification du modèle de véhicule
     * @param request
     */
    public void updateModel(HttpServletRequest request){
        String label = getValeurChamp(request, MODELS_FIELD_TO_UPDATE);
        int idUpdateModel = Integer.parseInt(getValeurChamp(request, MODELS_FIELD_ID_TO_UPDATE ));

        ModelsEntity modelsEntity;
        ModelsService modelsService = new ModelsService();

        EntityTransaction tx = null;
        try{
            tx = em.getTransaction();
            tx.begin();
            modelsEntity = modelsService.consultModel(em, idUpdateModel);
            modelsEntity.setLabel(label);
            modelsService.updateModels(em, modelsEntity);
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
