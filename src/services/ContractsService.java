package services;

import entities.ContractsEntity;

import javax.persistence.EntityManager;

/**
 * Méthode pour lister toutes les annonces
 */
public class ContractsService {

    /**
     * méthode ajouter d'une entité à  la bd
     *
     * @param em
     * @param contract
     */
    public void add(EntityManager em, ContractsEntity contract) {

        em.persist(contract);

    }

    /**
     *
     * Update de l'entité
     * @param em
     * @param contract
     */

    public void mergeContract(EntityManager em, ContractsEntity contract) {
        em.merge(contract);

    }

    /**
     * méthode Consulter d'une entité à  partir de la bd
     *
     * @param em
     * @param id
     * @return
     */
    public ContractsEntity consult(EntityManager em, int id) {
        return em.find(ContractsEntity.class, id);
    }

    public ContractsEntity findContractByIdOrder(EntityManager em, int id) {
        try {

            return em.createNamedQuery("Contracts.findContractByIdOrder",
                    ContractsEntity.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception ex) {
            return null;
        }

    }
}

