package exceptions;

import entities.AdsEntity;
import services.AdsService;
import util.JPAutil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class AdsException {
    //public AdsEntity ads;

    public void validationID(AdsEntity ads) throws Exception {
        if (ads == null) {
            throw new Exception("L'annonce est incorrecte");

        }
    }

}
