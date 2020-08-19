package services;

import javax.persistence.EntityManager;
import java.util.List;

import entities.AdsEntity;
import servlet.Ads;
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

    public AdsEntity findAdsById(EntityManager em, int id) {
        try {
            return em.createNamedQuery("findAdsByIdAds", AdsEntity.class).setParameter("idAds", id)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
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

    /**
     * Ajouter une annonce dans la DB
     *
     * @param em
     * @param adsEntity
     */
    public void addAds(EntityManager em, AdsEntity adsEntity) {
        em.persist(adsEntity);
    }

    /**
     * Mets à jour l'entité dans la db
     *
     * @param em
     * @param adsEntity
     */
    public void updateAds(EntityManager em, AdsEntity adsEntity) {
        em.merge(adsEntity);
    }

}