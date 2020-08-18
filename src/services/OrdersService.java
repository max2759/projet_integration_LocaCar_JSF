package services;

import entities.OrdersEntity;

import javax.persistence.EntityManager;
import java.util.List;

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
     * Update de l'entité
     *
     * @param em
     * @param orders
     */
    public void mergeOrder(EntityManager em, OrdersEntity orders) {
        em.merge(orders);

    }

    /**
     * méthode Consulter d'une entité à  partir de la bd
     *
     * @param em
     * @param id
     * @return
     */
    public OrdersEntity consult(EntityManager em, int id) {
//        return em.find(OrdersEntity.class, id);
        try {

            return em.createNamedQuery("Orders.findOrderById",
                    OrdersEntity.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }
    /**
     * méthode Consulter une commande validé : par id
     *
     * @param em
     * @param id
     * @return
     */
    public OrdersEntity findOrderValidatedById(EntityManager em, int id) {
        try {

            return em.createNamedQuery("Orders.findOrderValidatedById",
                    OrdersEntity.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * Recherche de commande en attente par idUser
     *
     * @param em
     * @param id
     * @return
     */
    public OrdersEntity findOrderByIdUser(EntityManager em, int id) {
        try {

            return em.createNamedQuery("Orders.findOrderByIdUser",
                    OrdersEntity.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception ex) {
            return null;
        }

    }

    /**
     * Recherche de commande valide ou annulé par Id order ou Id User ou un username
     * @param em
     * @param id
     * @param username
     * @return
     */
    public List<OrdersEntity> findOrdersValidateByIdOrderAndByIdUserAndByUsername(EntityManager em, int id, String username){
        try {

            return em.createNamedQuery("Orders.findOrdersValidateByIdOrderAndByIdUserAndByUsername",
                    OrdersEntity.class)
                    .setParameter("id", id)
                    .setParameter("username", username)
                    .getResultList();
        } catch (Exception ex) {
            return null;
        }
    }
}