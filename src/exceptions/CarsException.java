package exceptions;

import entities.CarsEntity;

public class CarsException {

    public void validationID(CarsEntity cars) throws Exception {

        if (cars == null) {
            throw new Exception("La car est incorrecte");

        }
    }

}
