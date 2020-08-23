package services;

import entities.ContractTypesEntity;

import javax.persistence.EntityManager;

public class ContractTypesService {

    /**
     * Méthode de recherche de l'entité
     * @param em
     * @param id
     * @return
     */
    public ContractTypesEntity consult(EntityManager em, int id) {
        return em.find(ContractTypesEntity.class, id);
    }

}
