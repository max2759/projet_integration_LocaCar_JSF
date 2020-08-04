package forms;

import entities.AdsEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Class pour créer la session du panier
 */
public class BasketForm {
    public static Map<String, AdsEntity> basket;
    public HttpSession session;

    /**
     * Constructeur qui crée la session si elle n'existe pas.
     * @param request
     */
    public BasketForm(HttpServletRequest request) {

        /* Récupération de la session depuis la requête*/
        session = request.getSession();
    }

    /**
     * Méthode d'ajout au panier
     * @param idAds
     * @param adsEntity
     */
    public void add(String idAds, AdsEntity adsEntity) {
        setBasket(idAds, adsEntity);
        session.setAttribute("basket", this.basket);

    }

    /**
     * Méthode pour supprimer un produit du panier
     * @param idAds
     */
    public void remove(String idAds){
        unsetBasket(idAds);
        session.setAttribute("basket", this.basket);

    }

    /**
     * Méthode pour setter la map de panier. Elle est appellé par add
     * @param idAds
     * @param adsEntity
     */
    public void setBasket(String idAds, AdsEntity adsEntity) {
        if (this.basket == null) {
            this.basket = new HashMap<>();
        }
        this.basket.put(idAds, adsEntity);
    }

    /**
     * Méthode pour unsetter la map de panier. Elle est appellé par remove
     * @param idAds
     */
    public void unsetBasket(String idAds){
        /*if (this.basket == null) {
            this.basket = new HashMap<>();
        }*/
        this.basket.remove(idAds);
    }

/*


    public Map getBasket() {
        return basket;
    }
*/
}