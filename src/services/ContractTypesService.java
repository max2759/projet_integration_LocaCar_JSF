package services;

import entities.ContractTypesEntity;

import javax.persistence.EntityManager;

public class ContractTypesService {

    public ContractTypesEntity consult(EntityManager em, int id) {
        return em.find(ContractTypesEntity.class, id);
    }

}
