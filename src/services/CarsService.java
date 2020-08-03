package services;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

import entities.AdsEntity;
import entities.CarsEntity;
import servlet.Ads;
import util.JPAutil;

/**
 * Méthode pour lister toutes les annonces
 */
public class CarsService {

    /**
     * méthode Consulter d'une entité à  partir de la bd
     *
     * @param em
     * @param id
     * @return
     */
    public CarsEntity consulter(EntityManager em, int id) {
        return em.find(CarsEntity.class, id);
    }


    /**
     * Méthode pour connaître l'annonce de la voiture
     *
     * @param em
     * @param ID
     * @return
     * @throws Exception
     */

    public AdsEntity searchAds(EntityManager em, int ID) throws Exception {
        try {

            return em.createNamedQuery("findAds",
                    AdsEntity.class)
                    .setParameter("id", ID)
                    .getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }
}