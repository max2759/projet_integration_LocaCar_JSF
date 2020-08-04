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
    private Map<String, AdsEntity> basket;
    HttpSession session;

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

    public void remove(String idAds){
        unsetBasket(idAds);

        session.setAttribute("basket", this.basket);

    }

    public void setBasket(String idAds, AdsEntity adsEntity) {
        if (this.basket == null) {
            this.basket = new HashMap<>();
        }
        this.basket.put(idAds, adsEntity);
    }
    public void unsetBasket(String idAds){
        this.basket.put(idAds, null);
    }


    public Map getBasket() {
        return basket;
    }
}