package exceptions;

import entities.ContractsEntity;

import java.util.List;

public class ContractsException {

    /**
     * Vérification de l'existence de l'entité
     *
     * @param contract
     * @throws Exception
     */
    public void validationEntity(ContractsEntity contract) throws Exception {
        if (contract == null) {
            throw new Exception("Le contract est incorrecte");

        }
    }

    /**
     * Vérification si l'entité est vide
     *
     * @param contract
     * @throws Exception
     */
    public void validationEntities(List<ContractsEntity> contract) throws Exception {
        if (contract == null || contract.isEmpty()) {
            throw new Exception("Aucun contrat");

        }
    }
}
