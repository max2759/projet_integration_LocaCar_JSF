package exceptions;

public class CarTypesException {

        public void categoryNoNumber(String category) throws Exception {
            if ( category != null && category.trim().length() != 0 ){
                if (category.matches(".*\\d.*")){
                    throw new Exception("Uniquement des lettres ! ");
                }
            }else{
                throw new Exception("Merci de remplir le formulaire !");
            }
        }
}
