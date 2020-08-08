package forms;

import entities.CarTypesEntity;
import exceptions.CarTypesException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CarTypesForm {

    private static final String CATEGORY_FIELD = "category";
    private String resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();

    public String getResultat(){
        return resultat;
    }

    public Map<String, String> getErreurs(){
        return erreurs;
    }

    public CarTypesEntity addCategory(HttpServletRequest request){

        String category = getValeurChamp(request, CATEGORY_FIELD);

        CarTypesEntity carTypes = new CarTypesEntity();
        CarTypesException ctE = new CarTypesException();

        try{
            ctE.categoryNoNumber(category);
        } catch (Exception e){
            setErreur( CATEGORY_FIELD, e.getMessage());
        }
        carTypes.setLabel( category );

        if (erreurs.isEmpty()){
            resultat = "Catégorie ajoutée";
        }else{
            resultat = "Echec d'ajout dans catégorie";
        }
        return carTypes;
    }


    /*
    * Ajout d'un message d'erreur
    * */
    private void setErreur(String field, String message){
        erreurs.put(field, message);
    }

    private static String getValeurChamp(HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur.trim();
        }
    }
}
