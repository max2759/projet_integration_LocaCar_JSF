package services;

import entities.CarTypesEntity;
import servlet.CarTypes;
import util.JPAutil;

import javax.persistence.EntityManager;

public class CarTypesService {

    public void addCategory(EntityManager em, CarTypesEntity carTypesEntity){
        em.persist(carTypesEntity);
    }

}
