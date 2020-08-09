package exceptions;

import entities.ContractsEntity;

public class ContractsException {

    /**
     * Vérification de l'existence de l'entité
     * @param contract
     * @throws Exception
     */
    public void validationEntity(ContractsEntity contract) throws Exception {
        if (contract == null) {
            throw new Exception("Le contract est incorrecte");

        }
    }

}
