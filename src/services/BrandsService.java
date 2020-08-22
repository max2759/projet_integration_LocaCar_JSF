package services;

import entities.BrandsEntity;
import entities.CarTypesEntity;
import util.JPAutil;

import javax.persistence.EntityManager;
import java.util.List;

public class BrandsService {

    EntityManager em = JPAutil.createEntityManager("projet_bac_info2");

    /**
     * Méthode pour lister toutes les marques
     *
     * @return brandsEntities
     */
    public List<BrandsEntity> displayBrands() {
        List<BrandsEntity> brandsEntities =
                em.createQuery(
                        "select b from BrandsEntity b").getResultList();

        return brandsEntities;
    }

    /**
     * méthode Consulter d'une entité à  partir de la bd
     *
     * @param em
     * @param id
     * @return
     */
    public BrandsEntity consultBrands(EntityManager em, int id) {
        return em.find(BrandsEntity.class, id);
    }

    /**
     * Ajouter un constructeur dans la base de donnée
     * @param em
     * @param brandsEntity
     */
    public void addBrands(EntityManager em, BrandsEntity brandsEntity){
        em.persist(brandsEntity);
    }
}
