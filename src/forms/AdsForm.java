package forms;


import entities.*;
import enumeration.EnumFuel;
import enumeration.EnumTypesAds;
import exceptions.AdsException;
import services.*;
import util.JPAutil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;



public class AdsForm {
    private static final String FIELD_ID = "idAds";
    private static final String FIELD_LABEL_ADS = "labelAd";
    private static final String FIELD_TYPEADS_ADS = "adType";
    private static final String FIELD_PRICE_ADS = "adPrice";
    private static final String FIELD_COLOR_CAR = "color";
    private static final String FIELD_RELEASEYEAR_CAR = "ReleaseYear";
    private static final String FIELD_KM_CAR = "kilometer";
    private static final String FIELD_HP_CAR = "horsePower";
    private static final String FIELD_CARTYPES_CAR = "carTypes";
    private static final String FIELD_BRANDS_CAR = "brands";
    private static final String FIELD_MODELS_CAR = "models";
    private static final String FIELD_FUEL_CAR = "fuel";
    private static final String FIELD_DELETE_ADS = "adsDelete";
    private static final String FIELD_IDUSERS_ADS = "idUser";
    public static final String FIELD_IDADS = "idAdToUpdate";
    public static final String FIELD_IDCARS_ADS = "idCarToUpdate";

    EntityManager em = JPAutil.createEntityManager("projet_bac_info2");


    private String result;
    private Map<String, String> errors = new HashMap<String, String>();

    public String getResult() {

        return result;
    }

    public Map<String, String> getErrors() {

        return errors;
    }

    /**
     * Crée une annonce via le formulaire
     * @param request
     * @throws ParseException
     */
    public void addAds(HttpServletRequest request) throws ParseException {

        // Les champs du form
        String color = getValeurChamp(request,FIELD_COLOR_CAR );
        String releaseYears = getValeurChamp(request, FIELD_RELEASEYEAR_CAR);
        SimpleDateFormat rYear = new SimpleDateFormat("yyyy-MM-dd");
        Date releaseYear = rYear.parse(releaseYears);
        int kilometer = Integer.parseInt(getValeurChamp(request, FIELD_KM_CAR));
        int horsePower = Integer.parseInt(getValeurChamp(request, FIELD_HP_CAR));
        String fuel = getValeurChamp(request, FIELD_FUEL_CAR);
        EnumFuel enumfuel = EnumFuel.valueOf(fuel);
        int models = Integer.parseInt(getValeurChamp(request, FIELD_MODELS_CAR));
        int brands = Integer.parseInt(getValeurChamp(request, FIELD_BRANDS_CAR));
        int carTypes = Integer.parseInt(getValeurChamp(request, FIELD_CARTYPES_CAR));
        String labelAd = getValeurChamp(request, FIELD_LABEL_ADS);
        Double priceAd = Double.parseDouble(getValeurChamp(request, FIELD_PRICE_ADS));
        String typeAds = getValeurChamp(request, FIELD_TYPEADS_ADS);
        EnumTypesAds enumTypesAds = EnumTypesAds.valueOf(typeAds);
        int idCar = 0;
        Date todayDate = new Date();
        LocalDateTime ldt = LocalDateTime.ofInstant(todayDate.toInstant(), ZoneId.systemDefault());
        Date out = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
        LocalDateTime dateEnd = ldt.plusMonths(1);
        Date endOut = Date.from(dateEnd.atZone(ZoneId.systemDefault()).toInstant());
        int idUser = Integer.parseInt(getValeurChamp(request, FIELD_IDUSERS_ADS));



        // Les entités

        AdsEntity adsEntity = new AdsEntity();
        CarsEntity carsEntity = new CarsEntity();
        CarTypesEntity carTypesEntity;
        ModelsEntity modelsEntity;
        BrandsEntity brandsEntity;
        UsersAdsEntity usersAdsEntity = new UsersAdsEntity();
        UsersEntity usersEntity;

        // Les services

        AdsService adsService = new AdsService();
        CarsService carsService = new CarsService();
        CarTypesService carTypesService = new CarTypesService();
        ModelsService modelsService = new ModelsService();
        BrandsService brandsService = new BrandsService();
        UsersAdsService usersAdsService = new UsersAdsService();
        UsersService usersService = new UsersService();

        // Transaction

        EntityTransaction tx = null;

        try{
            tx= em.getTransaction();
            tx.begin();

            //Ajout dans table cars
            carTypesEntity = carTypesService.consult(em, carTypes);
            modelsEntity = modelsService.consultModel(em, models);
            brandsEntity = brandsService.consultBrands(em, brands);

            carsEntity.setActive(true);
            carsEntity.setColor(color);
            carsEntity.setHorsePower(horsePower);
            carsEntity.setReleaseYear(releaseYear);
            carsEntity.setKilometer(kilometer);
            carsEntity.setEnumFuel(enumfuel);
            carsEntity.setCarTypesByIdCarTypes(carTypesEntity);
            carsEntity.setModelsByIdModels(modelsEntity);
            modelsEntity.setBrandsByIdBrands(brandsEntity);
            carsService.addCars(em, carsEntity);

            idCar = carsEntity.getId();

            // ajout dans ads
            carsService.consulter(em, idCar);
            adsEntity.setCarsByIdCars(carsEntity);
            adsEntity.setActive(true);
            adsEntity.setLabel(labelAd);
            adsEntity.setPrice(priceAd);
            adsEntity.setTypesAds(enumTypesAds);
            adsEntity.setDateStart(out);
            adsEntity.setDateEnd(endOut);

            adsService.addAds(em, adsEntity);

            // ajout dans users_Ads
            usersEntity = usersService.consult(em, idUser);
            usersAdsEntity.setFavorite(false);
            usersAdsEntity.setAdsByIdAds(adsEntity);
            usersAdsEntity.setUsersByIdUsers(usersEntity);

            usersAdsService.addUserAds(em, usersAdsEntity);


            tx.commit();
        }catch(Exception ex){
            if (tx != null && tx.isActive()) tx.rollback();
        }

    }


    /**
     * Methode pour rechercher une annonce à partir de l'id
     * @param idAds
     * @return
     */
    public AdsEntity checkAds(int idAds){
        AdsEntity ads = null;

        // On vérifie que c'est bien un nbr
        try {

            EntityManager em = JPAutil.createEntityManager("projet_bac_info2");

            // Recherche de l'ads

            AdsService adsService = new AdsService();

            EntityTransaction tx = null;
            try {
                tx = em.getTransaction();
                tx.begin();
                ads = adsService.consulter(em, idAds);
                tx.commit();
            } catch (Exception ex) {
                if (tx != null && tx.isActive()) tx.rollback();
            } finally {
                em.close();
            }

            AdsException adsException = new AdsException();

            try {
                adsException.validationEntity(ads);
            } catch (Exception e) {
                setError(FIELD_ID, e.getMessage());
            }
        } catch (NumberFormatException e) {
            setError(FIELD_ID, "Cette valeur n'est pas un chiffre, essaie encore !");

        }

        if (errors.isEmpty()) {
            result = "Succès";
        } else {
            result = "Échec";
        }
        return ads;
    }

    /**
     * Méthode de vérification de l'existence de l'ads
     *
     * @param request
     * @return
     */
    public AdsEntity checkAds(HttpServletRequest request) {

        AdsEntity ads = null;

        if (request.getParameter(FIELD_ID) != null) {
            // On vérifie que c'est bien un nbr
            try {

                int idAds = Integer.parseInt(request.getParameter(FIELD_ID));

                EntityManager em = JPAutil.createEntityManager("projet_bac_info2");

                // Recherche de l'ads

                AdsService adsService = new AdsService();

                EntityTransaction tx = null;
                try {
                    tx = em.getTransaction();
                    tx.begin();
                    ads = adsService.consulter(em, idAds);
                    tx.commit();
                } catch (Exception ex) {
                    if (tx != null && tx.isActive()) tx.rollback();
                } finally {
                    em.close();
                }

                AdsException adsException = new AdsException();

                try {
                    adsException.validationEntity(ads);
                } catch (Exception e) {
                    setError(FIELD_ID, e.getMessage());
                }
            } catch (NumberFormatException e) {
                setError(FIELD_ID, "Cette valeur n'est pas un chiffre, essaie encore !");

            }
        } else {
            setError(FIELD_ID, "Vide");
        }

        if (errors.isEmpty()) {
            result = "Succès";
        } else {
            result = "Échec";
        }
        return ads;
    }

    /**
     * Faits un soft delete, passe l'id de l'ads et de car à 0
     * @param request
     */
    public void removeAds(HttpServletRequest request){

        //Champs
        int idDelCar = Integer.parseInt(getValeurChamp(request, FIELD_DELETE_ADS));

        //Entité
        AdsEntity adsEntity;
        CarsEntity carsEntity;

        //Services
        AdsService adsService = new AdsService();
        CarsService carsService = new CarsService();

        // Transaction
        EntityTransaction tx = null;

        try{
            tx= em.getTransaction();
            tx.begin();
            // On récupère l'annonce par l'ID et la voiture lié à l'annonce
            adsEntity = adsService.consulter(em, idDelCar);
            carsEntity = adsEntity.getCarsByIdCars();

            //On passe les champs IsActive à 0
            adsEntity.setActive(false);
            carsEntity.setActive(false);

            adsService.updateAds(em, adsEntity);
            carsService.updateCar(em, carsEntity);
            tx.commit();

        }catch(Exception ex){
            if (tx != null && tx.isActive()) tx.rollback();
        }
    }

    /**
     * Afficher une annonce en fonction de l'id passé
     * @param id
     * @return
     */
    public AdsEntity showAd(int id){

        AdsService adsService = new AdsService();
        AdsEntity adsEntity;

        adsEntity = adsService.consulter(em, id);

        return adsEntity;

    }

    public void updateAds(HttpServletRequest request) throws ParseException {

        // Les champs du form
        String color = getValeurChamp(request,FIELD_COLOR_CAR );
        String releaseYears = getValeurChamp(request, FIELD_RELEASEYEAR_CAR);
        SimpleDateFormat rYear = new SimpleDateFormat("yyyy-MM-dd");
        Date releaseYear = rYear.parse(releaseYears);
        int kilometer = Integer.parseInt(getValeurChamp(request, FIELD_KM_CAR));
        int horsePower = Integer.parseInt(getValeurChamp(request, FIELD_HP_CAR));
        String fuel = getValeurChamp(request, FIELD_FUEL_CAR);
        EnumFuel enumfuel = EnumFuel.valueOf(fuel);
        int models = Integer.parseInt(getValeurChamp(request, FIELD_MODELS_CAR));
        int brands = Integer.parseInt(getValeurChamp(request, FIELD_BRANDS_CAR));
        int carTypes = Integer.parseInt(getValeurChamp(request, FIELD_CARTYPES_CAR));
        String labelAd = getValeurChamp(request, FIELD_LABEL_ADS);
        Double priceAd = Double.parseDouble(getValeurChamp(request, FIELD_PRICE_ADS));
        String typeAds = getValeurChamp(request, FIELD_TYPEADS_ADS);
        EnumTypesAds enumTypesAds = EnumTypesAds.valueOf(typeAds);
        int idAd = Integer.parseInt(getValeurChamp(request, FIELD_IDADS));
        int idCar = Integer.parseInt(getValeurChamp(request, FIELD_IDCARS_ADS));
        /*Date todayDate = new Date();
        LocalDateTime ldt = LocalDateTime.ofInstant(todayDate.toInstant(), ZoneId.systemDefault());
        Date out = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
        LocalDateTime dateEnd = ldt.plusMonths(1);
        Date endOut = Date.from(dateEnd.atZone(ZoneId.systemDefault()).toInstant());*/

        // Les entités
        AdsEntity adsEntity = new AdsEntity();
        CarsEntity carsEntity = new CarsEntity();
        CarTypesEntity carTypesEntity = new CarTypesEntity();
        ModelsEntity modelsEntity = new ModelsEntity();
        BrandsEntity brandsEntity = new BrandsEntity();

        // Les services
        AdsService adsService = new AdsService();
        CarsService carsService = new CarsService();
        CarTypesService carTypesService = new CarTypesService();
        ModelsService modelsService = new ModelsService();
        BrandsService brandsService = new BrandsService();

        // Transaction

        EntityTransaction tx = null;

        try{

            tx= em.getTransaction();
            tx.begin();

            //Modification dans table cars
            carsEntity = carsService.consulter(em, idCar);
            carTypesEntity = carTypesService.consult(em, carTypes);
            modelsEntity = modelsService.consultModel(em, models);
            brandsEntity = brandsService.consultBrands(em, brands);

            carsEntity.setColor(color);
            carsEntity.setHorsePower(horsePower);
            carsEntity.setReleaseYear(releaseYear);
            carsEntity.setKilometer(kilometer);
            carsEntity.setEnumFuel(enumfuel);
            carsEntity.setCarTypesByIdCarTypes(carTypesEntity);
            carsEntity.setModelsByIdModels(modelsEntity);
            modelsEntity.setBrandsByIdBrands(brandsEntity);

            carsService.updateCar(em, carsEntity);

            // Modification dans ads

            adsEntity = adsService.consulter(em, idAd);


            adsEntity.setLabel(labelAd);
            adsEntity.setPrice(priceAd);
            adsEntity.setTypesAds(enumTypesAds);
            /*adsEntity.setDateStart(out);
            adsEntity.setDateEnd(endOut);*/

            adsService.updateAds(em, adsEntity);

            tx.commit();
        }catch(Exception ex){
            if (tx != null && tx.isActive()) tx.rollback();
        }

    }

    /**
     * Ajoute un message correspondant au champ spécifié à la map des errors.
     */
    private void setError(String field, String message) {
        errors.put(field, message);
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