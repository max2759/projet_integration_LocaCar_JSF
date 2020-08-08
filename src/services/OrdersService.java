package services;

import javax.persistence.EntityManager;
import java.util.List;

import entities.AdsEntity;
import entities.OrdersEntity;
import util.JPAutil;

/**
 * Méthode pour lister toutes les annonces
 */
public class OrdersService {

    /**
     * méthode ajouter d'une entité à  la bd
     *
     * @param em
     * @param orders
     */
    public void add(EntityManager em, OrdersEntity orders) {

        em.persist(orders);

    }

    /**
     * méthode Consulter d'une entité à  partir de la bd
     *
     * @param em
     * @param id
     * @return
     */
    public OrdersEntity consult(EntityManager em, int id) {
        return em.find(OrdersEntity.class, id);
    }

    public OrdersEntity findOrderByIdUser(EntityManager em, int id) {
        return em.createNamedQuery("Orders.findOrderByIdUser",
                OrdersEntity.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}