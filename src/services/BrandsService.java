package services;

import entities.BrandsEntity;
import entities.ModelsEntity;
import util.JPAutil;

import javax.persistence.EntityManager;

public class BrandsService {

    EntityManager em = JPAutil.createEntityManager("projet_bac_info2");

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
}
