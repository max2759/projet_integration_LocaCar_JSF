package services;

import entities.AdsEntity;
import entities.CarTypesEntity;
import util.JPAutil;

import javax.persistence.EntityManager;
import java.util.List;

public class CarTypesService {

    EntityManager em = JPAutil.createEntityManager("projet_bac_info2");

    /**
     * Méthode pour ajouter une catégorie
     * @param em
     * @param carTypesEntity
     *
     */
    public void addCategory(EntityManager em, CarTypesEntity carTypesEntity){
        em.persist(carTypesEntity);
    }

    /**
     * Méthode pour lister toutes les annonces
     *
     * @return carTypesEntities
     */
    public List<CarTypesEntity> displayCategory () {
        List<CarTypesEntity> carTypesEntities =
                em.createQuery(
                        "select c from CarTypesEntity c").getResultList();
        return carTypesEntities;

    }

    /**
     * Supprime une catégorie dans la DB en fonction de l'id
     * @param id
     * @return
     */
    public CarTypesEntity deleteCarTypes(int id){
        CarTypesEntity carTypesEntity = em.find(CarTypesEntity.class, id);
        em.remove(carTypesEntity);
        return carTypesEntity;
    }

    public CarTypesEntity updateCarTypes(String id, String label){
        CarTypesEntity carTypesEntity = em.find(CarTypesEntity.class, id);
        /*carTypesEntity*/
        return carTypesEntity;
    }


}
