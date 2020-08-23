package forms;

import entities.AdsEntity;
import entities.ContractsEntity;
import enumeration.EnumOrderStatut;
import enumeration.EnumTypesAds;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class BasketCrudForm {

    public AdsEntity ads;
    public AdsForm adsForm = null;
    public BasketForm basketForm;

    /**
     * Méthode d'ajout au panier
     *
     * @param request
     * @return AdsForm
     */

    public AdsForm addBasket(HttpServletRequest request, Date dateStart, Date dateEnd) throws ParseException {
        EnumOrderStatut enumOrderStatut = EnumOrderStatut.values()[0];

        int idContractType = 0;
        // Recherche de l'ads
        adsForm = new AdsForm();
        ads = adsForm.checkAds(request);


        // Si aucune voiture n'est trouvé on renvoit une erreur.
        if (adsForm.getErrors().isEmpty()) {

            if (this.basketForm == null) {
                basketForm = new BasketForm();
            }

            // On check si l'annonce est une vente ou une location
            if (ads.getTypesAds() == EnumTypesAds.VENTE) {
                idContractType = 1;
            } else if (ads.getTypesAds() == EnumTypesAds.LOCATIONCD) {
                idContractType = 2;
            }
            adsForm = null;
            basketForm.add(ads, request, enumOrderStatut, idContractType, dateStart, dateEnd);
        }
        return adsForm;
    }

    /**
     * Méthode pour supprimer un produit du panier
     *
     * @param request
     * @return AdsForm
     */
    public AdsForm delBasket(HttpServletRequest request) {
        int idAds = (int) request.getAttribute("idAds");
        int idUser = Integer.parseInt(request.getParameter("idUser"));
        // Recherche de l'ads
        adsForm = new AdsForm();
        ads = adsForm.checkAds(idAds);
        if (adsForm.getErrors().isEmpty()) {

            if (this.basketForm == null) {
                basketForm = new BasketForm();
            }

            adsForm = null;
            basketForm.remove(idAds, idUser);
        }
        return adsForm;
    }

    /**
     * Méthode pour avoir la list des contracts
     *
     * @param idUser
     * @return
     */
    public List<ContractsEntity> listContracts(int idUser) {
        if (this.basketForm == null) {
            basketForm = new BasketForm();
        }
        return basketForm.listContracts(idUser);
    }

}
