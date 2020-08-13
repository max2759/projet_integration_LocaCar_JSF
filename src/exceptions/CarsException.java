package exceptions;

import entities.CarsEntity;

public class CarsException {

    public void validationEntity(CarsEntity cars) throws Exception {

        if (cars == null) {
            throw new Exception("La car est incorrecte");

        }
    }

    public void carNotActive(CarsEntity cars) throws Exception {
        if (cars.isActive() == false) {
            throw new Exception("La voiture n'est plus disponible");
        }
    }

}
