package services;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

import entities.AdsEntity;
import entities.CarsEntity;
import util.JPAutil;

/**
 * Méthode pour lister toutes les annonces
 */
public class AdsService {

    EntityManager em = JPAutil.createEntityManager("projet_bac_info2");

    /**
     * Méthode pour lister toutes les annonces
     *
     * @return
     */
    public List<AdsEntity> listerTous() {
        List<AdsEntity> ads =
                em.createQuery(
                        "select a from AdsEntity a").getResultList();
        return ads;

    }


    /**
     * méthode Consulter d'une entité à  partir de la bd
     *
     * @param em
     * @param id
     * @return
     */
    public AdsEntity consulter(EntityManager em, int id) {
        return em.find(AdsEntity.class, id);
    }


}