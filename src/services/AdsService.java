package services;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import entities.AdsEntity;
import util.JPAutil;

/**
 * Méthode pour lister toutes les annonces
 */
public class AdsService {

    EntityManager em = JPAutil.createEntityManager("projet_bac_info2");

    //méthode pour lister tous les objets à  partir de la bd
    public List<AdsEntity> listerTous() {
        List<AdsEntity> ads =
                em.createQuery(
                        "select a from AdsEntity a").getResultList();
        return ads;

    }

}