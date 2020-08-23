package exceptions;

import entities.OrdersEntity;

import java.util.List;

public class OrdersException {
    //public AdsEntity ads;


    /**
     * Vérification de l'existence de l'entité
     *
     * @param orders
     * @throws Exception
     */
    public void validationEntity(OrdersEntity orders) throws Exception {
        if (orders == null) {
            throw new Exception("La commande est incorrecte");

        }
    }

    /**
     * Vérification si l'entité est vide
     *
     * @param orders
     * @throws Exception
     */
    public void validationEntities(List<OrdersEntity> orders) throws Exception {
        if (orders == null || orders.isEmpty()) {
            throw new Exception("Aucune commande");

        }
    }

}
