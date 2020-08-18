package exceptions;

import entities.CarsEntity;

public class CarsException {

    /**
     * Vérification si l'entité est vide
     * @param cars
     * @throws Exception
     */
    public void validationEntity(CarsEntity cars) throws Exception {

        if (cars == null) {
            throw new Exception("La car est incorrecte");

        }
    }

    /**
     * Vérication si cars (entité) est active
     * @param cars
     * @throws Exception
     */
    public void carNotActive(CarsEntity cars) throws Exception {
        if (!cars.isActive()) {
            throw new Exception("La voiture n'est plus disponible");
        }
    }

}
