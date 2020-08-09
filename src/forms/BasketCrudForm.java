package forms;

import entities.AdsEntity;
import enumeration.EnumOrderStatut;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Date;
//import javax.servlet.http.HttpSession;
//import java.util.Map;

public class BasketCrudForm {

    public AdsEntity ads;
    public AdsForm adsForm = null;
    public BasketForm basketForm;

    /**
     * Méthode d'ajout au panier
     * @param request
     * @return AdsForm
     */

    public AdsForm addBasket(HttpServletRequest request, Date dateStart, Date dateEnd) throws ParseException {
        EnumOrderStatut enumOrderStatut = EnumOrderStatut.values()[0];
        String idAds = request.getParameter("idAds");
        // Recherche de l'ads
        adsForm = new AdsForm();
        ads = adsForm.checkAds(request);


        // Si aucune voiture n'est trouvé on renvoit une erreur.
        if (adsForm.getErrors().isEmpty()) {
//            HttpSession session = request.getSession();
            if (this.basketForm == null) {
                basketForm = new BasketForm(request);
            }

            adsForm = null;
            basketForm.add(idAds, ads, request, enumOrderStatut, 1, dateStart, dateEnd);
        }
        return adsForm;
    }

    /**
     * Méthode pour supprimer un produit du panier
     * @param request
     * @return AdsForm
     */
    public AdsForm delBasket(HttpServletRequest request){
        String idAds = request.getParameter("idAds");
        int idUser = Integer.parseInt(request.getParameter("idUser"));
        // Recherche de l'ads
        adsForm = new AdsForm();
        ads = adsForm.checkAds(request);
        if (adsForm.getErrors().isEmpty()) {

            if (this.basketForm == null) {
                basketForm = new BasketForm(request);
            }

            adsForm = null;
            basketForm.remove(idAds, idUser);
        }
        return adsForm;
    }
}
