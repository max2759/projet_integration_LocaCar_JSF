package exceptions;

import entities.AdsEntity;

public class AdsException {
    //public AdsEntity ads;


    /**
     * Vérification de l'existence de l'entité
     * @param ads
     * @throws Exception
     */
    public void validationEntity(AdsEntity ads) throws Exception {
        if (ads == null) {
            throw new Exception("L'annonce est incorrecte");

        }
    }

}
