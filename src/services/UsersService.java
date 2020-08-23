package services;

import entities.UsersEntity;

import javax.persistence.EntityManager;

/**
 * Méthode pour lister toutes les annonces
 */
public class UsersService {
    /**
     * méthode Consulter d'une entité à  partir de la bd
     *
     * @param em
     * @param id
     * @return
     */
    public UsersEntity consult(EntityManager em, int id) {
        return em.find(UsersEntity.class, id);
    }


}