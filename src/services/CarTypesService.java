package services;

import entities.CarTypesEntity;
import servlet.CarTypes;
import util.JPAutil;

import javax.persistence.EntityManager;

public class CarTypesService {

    EntityManager em = JPAutil.createEntityManager("projet_bac_info2");

    public void addCategory(EntityManager em, CarTypesEntity carTypesEntity){
        em.persist(carTypesEntity);
    }

}
