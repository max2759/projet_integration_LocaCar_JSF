package forms;

import entities.AdsEntity;

import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import java.util.Map;

public class BasketCrudForm {

    public AdsEntity ads;
    public AdsForm adsForm = null;
    public BasketForm basketForm;

    /**
     * Méthode d'ajout au panier
     * @param request
     * @return
     */

    public AdsForm addBasket(HttpServletRequest request) {

        String idAds = request.getParameter("idAds");
        // Recherche de l'ads
        adsForm = new AdsForm();
        ads = adsForm.checkAds(request);

        // Si aucune voiture n'est trouvé on renvoit une erreur.
        if (adsForm.getErrors().isEmpty()) {
//            HttpSession session = request.getSession();
            if (this.basketForm == null) {
                basketForm = new BasketForm(request);
                adsForm = null;
            }
            basketForm.add(idAds, ads);
        }
        return adsForm;
    }

    /**
     * Méthode pour supprimer un produit du panier
     * @param request
     * @return
     */
    public AdsForm delBasket(HttpServletRequest request){
        String idAds = request.getParameter("idAds");
        // Recherche de l'ads
        adsForm = new AdsForm();
        ads = adsForm.checkAds(request);
        if (adsForm.getErrors().isEmpty()) {

            if (this.basketForm == null) {
                basketForm = new BasketForm(request);
                adsForm = null;
            }
            basketForm.remove(idAds);
        }
        return adsForm;
    }
}
