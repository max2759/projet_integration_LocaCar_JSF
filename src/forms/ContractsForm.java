package forms;

import entities.*;
import enumeration.EnumOrderStatut;
import exceptions.ContractsException;
import exceptions.OrdersException;
import services.*;
import util.JPAutil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContractsForm {

    private static final String FIELD_ID_CONTRACT = "idContract";
    private static final String FIELD_ID_ORDERS = "idOrders";
    private static final String FIELD_ID_USERS = "idUsers";

    private String result;
    private Map<String, String> errors = new HashMap<String, String>();

    public String getResult() {

        return result;
    }

    public Map<String, String> getErrors() {

        return errors;
    }

    /**
     * Vérification de la commande
     *
     * @param request
     * @return
     */
    public ContractsEntity checkContract(HttpServletRequest request) {

        ContractsEntity contract = null;

        if (request.getParameter(FIELD_ID_CONTRACT) != null) {
            // On vérifie que c'est bien un nbr
            try {

                int idContract = Integer.parseInt(request.getParameter(FIELD_ID_CONTRACT));

                EntityManager em = JPAutil.createEntityManager("projet_bac_info2");

                ContractsService contractsService = new ContractsService();

                EntityTransaction tx = null;
                try {
                    tx = em.getTransaction();
                    tx.begin();
                    contract = contractsService.consult(em, idContract);
                    tx.commit();
                } catch (Exception ex) {
                    if (tx != null && tx.isActive()) tx.rollback();
                } finally {
                    em.close();
                }

                ContractsException contractsException = new ContractsException();

                try {
                    contractsException.validationEntity(contract);
                } catch (Exception e) {
                    setError(FIELD_ID_CONTRACT, e.getMessage());
                }
            } catch (NumberFormatException e) {
                setError(FIELD_ID_CONTRACT, "Cette valeur n'est pas un chiffre, essaie encore !");

            }
        } else {
            setError(FIELD_ID_CONTRACT, "Vide");
        }

        if (errors.isEmpty()) {
            result = "Succès";
        } else {
            result = "Échec";
        }
        return contract;
    }

    /**
     * Méthode pour sauvegarder l'entité
     * @param idOrder
     * @param idCar
     * @param idContractType
     * @param dateStart
     * @param dateEnd
     * @param finalPrice
     * @return
     */
    public ContractsEntity saveContract(int idOrder, int idCar, int idContractType, Date dateStart, Date dateEnd, double finalPrice) {

        EntityManager em = JPAutil.createEntityManager("projet_bac_info2");

        // Création des entité :

        ContractsEntity contractsEntity = new ContractsEntity();
        OrdersEntity ordersEntity = new OrdersEntity();
        CarsEntity carsEntity = new CarsEntity();
        ContractTypesEntity contractTypesEntity = new ContractTypesEntity();


        // Création des services

        OrdersService ordersService = new OrdersService();

        CarsService carsService = new CarsService();


        ContractTypesService contractTypesService = new ContractTypesService();

        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            ordersEntity = ordersService.consult(em, idOrder);
            carsEntity = carsService.consulter(em, idCar);
            contractTypesEntity = contractTypesService.consult(em, idContractType);
            tx.commit();
        } catch (Exception ex) {
            if (tx != null && tx.isActive()) tx.rollback();
        } finally {
            em.close();
        }

        contractsEntity.setOrdersByIdOrders(ordersEntity);
        contractsEntity.setCarsByIdCars(carsEntity);
        contractsEntity.setContractTypesByIdContractTypes(contractTypesEntity);
        contractsEntity.setDateStart(dateStart);
        contractsEntity.setDateEnd(dateEnd);
        contractsEntity.setFinalPrice(finalPrice);
        return contractsEntity;
    }

    /*
     * Ajoute un message correspondant au champ spécifié à la map des errors.
     */
    private void setError(String field, String message) {
        errors.put(field, message);
    }

    /**
     * Recherche tous les contracts par IdOrder
     * @param idOrder
     * @return
     */
    public List<ContractsEntity> findAllContractByIdOrder(int idOrder){
        List<ContractsEntity> contractsEntities = null;
        ContractsService contractsService = new ContractsService();

        EntityManager em = JPAutil.createEntityManager("projet_bac_info2");

        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();

            //Vérification le id Order
            contractsEntities = contractsService.findAllContractByIdOrder(em, idOrder);
            ContractsException contractsException = new ContractsException();

            // Si aucune entité n'est trouvé on renvoit une exception
            try {
                contractsException.validationEntities(contractsEntities);
            } catch (Exception e) {
                setError("contract", e.getMessage());
            }
            tx.commit();
        } catch (Exception ex) {
            if (tx != null && tx.isActive()) tx.rollback();
        } finally {
            em.close();
        }

        if (errors.isEmpty()) {
            result = "Succès";
        } else {
            result = "Echec";
        }
        return contractsEntities;

    }

    /**
     * Recherche de contracts par idCar et date de reservation
     * @param idCar
     * @param dateStart
     * @param dateEnd
     * @return
     */
    public List<ContractsEntity> findContractsByIdCarAndReservationDate(int idCar, Date dateStart, Date dateEnd){
        List<ContractsEntity> contractsEntities = null;
        ContractsService contractsService = new ContractsService();

        EntityManager em = JPAutil.createEntityManager("projet_bac_info2");

        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();

            //Vérification le id Order
            contractsEntities = contractsService.findContractsByIdCarAndReservationDate(em, idCar, dateStart, dateEnd);
            tx.commit();
        } catch (Exception ex) {
            if (tx != null && tx.isActive()) tx.rollback();
        } finally {
            em.close();
        }
        return contractsEntities;

    }

    /**
     * Changement de statut de la commande
     *
     * @param ordersEntity
     * @param orderStatut
     */
    public OrdersEntity changeStatut(OrdersEntity ordersEntity, EnumOrderStatut orderStatut) {

        ordersEntity.setOrderStatut(orderStatut);
        return ordersEntity;
    }

    private static String getValeurChamp(HttpServletRequest request, String nomChamp) {
        String valeur = request.getParameter(nomChamp);
        if (valeur == null || valeur.trim().length() == 0) {
            return null;
        } else {
            return valeur.trim();
        }
    }
}

