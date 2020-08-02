package services;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import entities.CarsEntity;
import util.JPAutil;

/**
 * Méthode pour lister toutes les annonces
 */
public class CarsService {

    EntityManager em = JPAutil.createEntityManager("projet_bac_info2");

    //méthode Consulter d'une entité à  partir de la bd
    public  CarsEntity consulter(CarsEntity cars,Object id)
    {
        return em.find(CarsEntity.class, id);
    }

}