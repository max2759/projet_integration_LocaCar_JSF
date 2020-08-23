package services;

import entities.ContractTypesEntity;

import javax.persistence.EntityManager;

public class ContractTypesService {

    /**
     * Retourne une catégorie en fonction de l'id passé
     * @param em
     * @param id
     * @return
     */
    public ContractTypesEntity consult(EntityManager em, int id) {
        return em.find(ContractTypesEntity.class, id);
    }

}
