package services;

import entities.CarTypesEntity;
import entities.ModelsEntity;
import util.JPAutil;

import javax.persistence.EntityManager;

public class ModelsService {
    EntityManager em = JPAutil.createEntityManager("projet_bac_info2");

    /**
     * méthode Consulter d'une entité à  partir de la bd
     *
     * @param em
     * @param id
     * @return
     */
    public ModelsEntity consultModel(EntityManager em, int id) {
        return em.find(ModelsEntity.class, id);
    }
}
