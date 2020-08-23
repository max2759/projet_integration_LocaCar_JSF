package services;

import entities.UsersAdsEntity;
import util.JPAutil;

import javax.persistence.EntityManager;
import java.util.List;

public class UsersAdsService {

    EntityManager em = JPAutil.createEntityManager("projet_bac_info2");

    /**
     * Méthode pour lister toutes les annonces
     *
     * @return
     */
    public List<UsersAdsEntity> listAllUserAds() {
        List<UsersAdsEntity> usersAdsEntities =
                em.createQuery(
                        "select uad from UsersAdsEntity uad").getResultList();
        return usersAdsEntities;
    }

    /**
     * méthode Consulter d'une entité à  partir de la bd
     *
     * @param em
     * @param id
     * @return
     */
    public UsersAdsEntity consulterUserAds(EntityManager em, int id) {
        return em.find(UsersAdsEntity.class, id);
    }

    /**
     * Ajouter dans UserAds
     * @param em
     * @param usersAdsEntity
     */
    public void addUserAds(EntityManager em, UsersAdsEntity usersAdsEntity){
        em.persist(usersAdsEntity);
    }
}
