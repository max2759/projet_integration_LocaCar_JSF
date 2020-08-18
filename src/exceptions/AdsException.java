package exceptions;

import entities.AdsEntity;

public class AdsException {
    //public AdsEntity ads;


    /**
     * Vérification de l'existence de l'entité
     * @param ads Entité ads
     * @throws Exception incorrecte
     */
    public void validationEntity(AdsEntity ads) throws Exception {
        if (ads == null) {
            throw new Exception("L'annonce est incorrecte");

        }
    }

}
