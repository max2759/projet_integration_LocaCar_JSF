package exceptions;

import services.CarTypesService;
import services.CarsService;

import javax.persistence.EntityManager;

public class CarTypesException {

        public void categoryNoNumber(String category) throws Exception {
            if ( category != null && category.trim().length() != 0 ){
                if (!category.matches("[a-zA-Z]+[a-zA-Z-]*")){
                    throw new Exception("Uniquement des lettres ! ");
                }
            }else{
                throw new Exception("Merci de remplir le champ !");
            }
        }


}
