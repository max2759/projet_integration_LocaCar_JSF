package services;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

import entities.AdsEntity;
import entities.BrandsEntity;
import entities.CarsEntity;
import servlet.Ads;
import util.JPAutil;

/**
 * Méthode pour lister toutes les annonces
 */
public class CarsService {

    EntityManager em = JPAutil.createEntityManager("projet_bac_info2");

    /**
     * méthode Consulter d'une entité à  partir de la bd
     *
     * @param em
     * @param id
     * @return
     */
    public CarsEntity consulter(EntityManager em, int id) {
        return em.find(CarsEntity.class, id);
    }


    /**
     * Méthode pour connaître l'annonce de la voiture à partir de l'id Car
     *
     * @param em
     * @param ID
     * @return
     * @throws Exception
     */

    public AdsEntity searchAds(EntityManager em, int ID) throws Exception {
        try {

            return em.createNamedQuery("findAds",
                    AdsEntity.class)
                    .setParameter("idCar", ID)
                    .getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * Ajouter voiture à la db
     * @param em
     * @param carsEntity
     */
    public void addCars(EntityManager em, CarsEntity carsEntity){
        em.persist(carsEntity);
    }

    /**
     * Mets à jour l'entité dans la db
     * @param em
     * @param carsEntity
     */
    public void updateCar(EntityManager em, CarsEntity carsEntity){
        em.merge(carsEntity);
    }


    /**
     * Lister toutes les voitures
     * @return carsEntities
     */
    public List<CarsEntity> displayCars() {
        List<CarsEntity> carsEntities =
                em.createQuery(
                        "select c from CarsEntity c").getResultList();

        return carsEntities;
    }
}