package services;

import entities.*;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

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
     * Update de l'entité
     *
     * @param em
     * @param contract
     */
    public void mergeContract(EntityManager em, ContractsEntity contract) {
        em.merge(contract);

    }

    /**
     * Delete de l'entité
     *
     * @param em
     * @param contractsEntity
     */
    public void removeContract(EntityManager em, ContractsEntity contractsEntity) {

        em.remove(em.merge(contractsEntity));

//        em.remove(contractsEntity);
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

    /**
     * recherche de contract par id order
     *
     * @param em
     * @param id
     * @return
     */
    public List<ContractsEntity> findAllContractByIdOrder(EntityManager em, int id) {
        try {

            return em.createNamedQuery("Contracts.findAllContractsByIdOrder",
                    ContractsEntity.class)
                    .setParameter("id", id)
                    .getResultList();
        } catch (Exception ex) {
            return null;
        }

    }

    /**
     * recherche de contrat par id commande et par id voiture
     * @param em
     * @param idOrder
     * @param idCar
     * @return
     */
    public ContractsEntity findContractByIdOrderAndByIdCar(EntityManager em, int idOrder, int idCar) {
        try {

            return em.createNamedQuery("Contracts.findContractByIdOrderAndByIdCar",
                    ContractsEntity.class)
                    .setParameter("idOrder", idOrder)
                    .setParameter("idCar", idCar)
                    .getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * Lister tous les contrats d'un utilisateurs
     * @param em
     * @param idUser
     * @return
     */
    public List<ContractsEntity> findAllContractsByIdUser(EntityManager em, int idUser) {

        UsersService usersService = new UsersService();
        UsersEntity usersEntity = usersService.consult(em, idUser);
        List<ContractsEntity> contractsEntities = em.createNamedQuery("Contracts.findAllContractsByIdUser",
                ContractsEntity.class)
                .setParameter("user", usersEntity)
                .getResultList();

        return contractsEntities;

    }

    /**
     * Vérification de réservation de date pour un véhicule
     * @param em
     * @param idCar
     * @param dateStart
     * @param dateEnd
     * @return
     */
    public List<ContractsEntity> findContractsByIdCarAndReservationDate(EntityManager em, int idCar, Date dateStart, Date dateEnd) {

        List<ContractsEntity> contractsEntities = em.createNamedQuery("Contracts.findContractsByIdCarAndReservationDate",
                ContractsEntity.class)
                .setParameter("idCar", idCar)
                .setParameter("dateStart", dateStart)
                .setParameter("dateEnd", dateEnd)
                .getResultList();

        return contractsEntities;

    }



}


