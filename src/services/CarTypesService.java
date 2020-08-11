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
     *
     * @param em
     * @param carTypesEntity
     */
    public void addCategory(EntityManager em, CarTypesEntity carTypesEntity) {
        em.persist(carTypesEntity);
    }

    /**
     * Méthode pour lister toutes les annonces
     *
     * @return carTypesEntities
     */
    public List<CarTypesEntity> displayCategory() {
        List<CarTypesEntity> carTypesEntities =
                em.createQuery(
                        "select c from CarTypesEntity c").getResultList();
        return carTypesEntities;

    }

    /**
     * Suppression de l'entité carTypes
     *
     * @param em
     * @param carTypesEntity
     */
    public void deleteCarTypes(EntityManager em, CarTypesEntity carTypesEntity) {
        em.remove(em.merge(carTypesEntity));
    }

    /**
     * Recherche la catégorie en fonction de l'id passé
     * @param em
     * @param id
     * @return
     */
    public CarTypesEntity findCarTypesById(EntityManager em, int id) {
        return em.createNamedQuery("car_types.findCarTypesById", CarTypesEntity.class).setParameter("id", id).getSingleResult();
    }

    /**
     * Modification de la table catégorie en fonction de l'id passé
     *
     * @param carTypesEntity
     * @param em
     * @return
     */
    public void updateCarTypes(EntityManager em, CarTypesEntity carTypesEntity) {
        em.merge(carTypesEntity);
    }


}
