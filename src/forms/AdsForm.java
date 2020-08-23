package forms;


import entities.*;
import enumeration.EnumFuel;
import enumeration.EnumTypesAds;
import exceptions.AdsException;
import services.*;
import util.JPAutil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
    public static final String FIELD_IDADS_TO_ACTIVATE = "idAdsToActivate";
    public static final String FIELD_IDCARS_TO_ACTIVATE = "idCarsToActivate";
    private static final int TAILLE_TAMPON = 10240;


    private static final String SAVE_DIR = "web\\resources\\img";

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
     *
     * @param request
     * @throws ParseException
     */
    public void addAds(HttpServletRequest request) throws ParseException, IOException, ServletException {

        // Les champs du form
        String color = getValeurChamp(request, FIELD_COLOR_CAR);
        String releaseYears = getValeurChamp(request, FIELD_RELEASEYEAR_CAR);
        SimpleDateFormat rYear = new SimpleDateFormat("yyyy-MM-dd");
        Date releaseYear = rYear.parse(releaseYears);
        int kilometer = Integer.parseInt(getValeurChamp(request, FIELD_KM_CAR));
        int horsePower = Integer.parseInt(getValeurChamp(request, FIELD_HP_CAR));
        String fuel = getValeurChamp(request, FIELD_FUEL_CAR);
        EnumFuel enumfuel = EnumFuel.valueOf(fuel);
        int models = Integer.parseInt(getValeurChamp(request, FIELD_MODELS_CAR));
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
        Part filePart = request.getPart("fileToUpload");


        // Les entités

        AdsEntity adsEntity = new AdsEntity();
        CarsEntity carsEntity = new CarsEntity();
        CarTypesEntity carTypesEntity;
        ModelsEntity modelsEntity;
        UsersAdsEntity usersAdsEntity = new UsersAdsEntity();
        UsersEntity usersEntity;

        // Les services

        AdsService adsService = new AdsService();
        CarsService carsService = new CarsService();
        CarTypesService carTypesService = new CarTypesService();
        ModelsService modelsService = new ModelsService();
        UsersAdsService usersAdsService = new UsersAdsService();
        UsersService usersService = new UsersService();

        // Transaction

        EntityTransaction tx = null;

        try {
            tx = em.getTransaction();
            tx.begin();

            //Ajout dans table cars
            carTypesEntity = carTypesService.consult(em, carTypes);
            modelsEntity = modelsService.consultModel(em, models);

            carsEntity.setActive(true);
            carsEntity.setColor(color);
            carsEntity.setHorsePower(horsePower);
            carsEntity.setReleaseYear(releaseYear);
            carsEntity.setKilometer(kilometer);
            carsEntity.setEnumFuel(enumfuel);
            carsEntity.setCarTypesByIdCarTypes(carTypesEntity);
            carsEntity.setModelsByIdModels(modelsEntity);

            // Ajout d'image
            /*InputStream fileInputStream = filePart.getInputStream();

            /*File fileToSave = new File("web/resources/img/" + filePart.getSubmittedFileName());

            String fileName = filePart.getName();
            Files.copy(fileInputStream, fileToSave.toPath(), StandardCopyOption.REPLACE_EXISTING);*/

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
        } catch (Exception ex) {
            if (tx != null && tx.isActive()) tx.rollback();
        }

    }


    /**
     * Methode pour rechercher une annonce à partir de l'id
     *
     * @param idAds
     * @return
     */
    public AdsEntity checkAds(int idAds) {
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

    public void reActivateAds(HttpServletRequest request){

        //Champs
        int idAdsToActivate = Integer.parseInt(getValeurChamp(request, FIELD_IDADS_TO_ACTIVATE));
        int idCarsToActivate = Integer.parseInt(getValeurChamp(request, FIELD_IDCARS_TO_ACTIVATE));
        Date todayDate = new Date();
        LocalDateTime ldt = LocalDateTime.ofInstant(todayDate.toInstant(), ZoneId.systemDefault());
        Date out = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
        LocalDateTime dateEnd = ldt.plusMonths(1);
        Date endOut = Date.from(dateEnd.atZone(ZoneId.systemDefault()).toInstant());

        //Entité
        AdsEntity adsEntity;
        CarsEntity carsEntity;

        //Services
        AdsService adsService = new AdsService();
        CarsService carsService = new CarsService();

        // Transaction
        EntityTransaction tx = null;

        try {
            tx = em.getTransaction();
            tx.begin();
            // On récupère l'annonce par l'ID et la voiture lié à l'annonce
            adsEntity = adsService.consulter(em, idAdsToActivate);
            carsEntity = adsEntity.getCarsByIdCars();

            //On passe les champs IsActive à 1
            adsEntity.setActive(true);
            carsEntity.setActive(true);
            // On remet la date d'aujourd'hui et ajoute 1 mois à l'annonce
            adsEntity.setDateStart(out);
            adsEntity.setDateEnd(endOut);

            adsService.updateAds(em, adsEntity);
            carsService.updateCar(em, carsEntity);
            tx.commit();

        } catch (Exception ex) {
            if (tx != null && tx.isActive()) tx.rollback();
        }

    }

    /**
     * Faits un soft delete, passe l'id de l'ads et de car à 0
     *
     * @param request
     */
    public void removeAds(HttpServletRequest request) {

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

        try {
            tx = em.getTransaction();
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

        } catch (Exception ex) {
            if (tx != null && tx.isActive()) tx.rollback();
        }
    }

    /**
     * Afficher une annonce en fonction de l'id passé
     *
     * @param id
     * @return
     */
    public AdsEntity showAd(int id) {

        AdsService adsService = new AdsService();
        AdsEntity adsEntity;

        adsEntity = adsService.consulter(em, id);

        return adsEntity;

    }

    /**
     * Permet de mettre à jour l'annonce et la voiture en fonction de l'ID
     *
     * @param request
     * @throws ParseException
     */
    public void updateAds(HttpServletRequest request) throws ParseException, IOException, ServletException {

        // Les champs du form
        String color = getValeurChamp(request, FIELD_COLOR_CAR);
        String releaseYears = getValeurChamp(request, FIELD_RELEASEYEAR_CAR);
        SimpleDateFormat rYear = new SimpleDateFormat("yyyy-MM-dd");
        Date releaseYear = rYear.parse(releaseYears);
        int kilometer = Integer.parseInt(getValeurChamp(request, FIELD_KM_CAR));
        int horsePower = Integer.parseInt(getValeurChamp(request, FIELD_HP_CAR));
        String fuel = getValeurChamp(request, FIELD_FUEL_CAR);
        EnumFuel enumfuel = EnumFuel.valueOf(fuel);
        int models = Integer.parseInt(getValeurChamp(request, FIELD_MODELS_CAR));
        int carTypes = Integer.parseInt(getValeurChamp(request, FIELD_CARTYPES_CAR));
        String labelAd = getValeurChamp(request, FIELD_LABEL_ADS);
        Double priceAd = Double.parseDouble(getValeurChamp(request, FIELD_PRICE_ADS));
        String typeAds = getValeurChamp(request, FIELD_TYPEADS_ADS);
        EnumTypesAds enumTypesAds = EnumTypesAds.valueOf(typeAds);
        int idAd = Integer.parseInt(getValeurChamp(request, FIELD_IDADS));
        int idCar = Integer.parseInt(getValeurChamp(request, FIELD_IDCARS_ADS));
        /*Part filePart = request.getPart("fileToUpload");*/


        // Les entités
        AdsEntity adsEntity = new AdsEntity();
        CarsEntity carsEntity = new CarsEntity();
        CarTypesEntity carTypesEntity = new CarTypesEntity();
        ModelsEntity modelsEntity = new ModelsEntity();

        // Les services
        AdsService adsService = new AdsService();
        CarsService carsService = new CarsService();
        CarTypesService carTypesService = new CarTypesService();
        ModelsService modelsService = new ModelsService();

        // Transaction

        EntityTransaction tx = null;

        try {

            tx = em.getTransaction();
            tx.begin();

            //Modification dans table cars
            carsEntity = carsService.consulter(em, idCar);
            carTypesEntity = carTypesService.consult(em, carTypes);
            modelsEntity = modelsService.consultModel(em, models);

            carsEntity.setColor(color);
            carsEntity.setHorsePower(horsePower);
            carsEntity.setReleaseYear(releaseYear);
            carsEntity.setKilometer(kilometer);
            carsEntity.setEnumFuel(enumfuel);
            carsEntity.setCarTypesByIdCarTypes(carTypesEntity);
            carsEntity.setModelsByIdModels(modelsEntity);

            /*File upload = new File("/web/resources/img");*/

            // récupère le nom du fichier envoyer
            /*String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

            InputStream is = filePart.getInputStream();
            /*String path = request.getServletContext().getRealPath("web\\upload" + File.separator + fileName);*/
            /*String path = "C:\\Users\\maxim\\IdeaProjects\\Importation_DB3\\web\\upload\\";
            String path = "\\Importation_DB3\\web\\upload\\";

            BufferedInputStream entree = null;
            BufferedOutputStream sortie = null;

            try{
                entree = new BufferedInputStream(is, TAILLE_TAMPON);
                sortie = new BufferedOutputStream(new FileOutputStream(new File(path + fileName)), TAILLE_TAMPON);
                byte[] tampon = new byte[TAILLE_TAMPON];
                int longueur = 0;
                while((longueur = entree.read(tampon))>0){
                    sortie.write(tampon, 0, longueur);
                }
             }finally {
                try {
                    sortie.close();
                } catch ( IOException ignore ) {
                }
                try {
                    entree.close();
                } catch ( IOException ignore ) {
                }
            }*/


            /*String path = request.getServletContext().getRealPath("/" + "upload" + File.separator + fileName);*/

            /*String path = request.getServletContext()


            InputStream is = filePart.getInputStream();

            try{
                byte[] bytes = new byte[is.available()];
                is.read();
                FileOutputStream fOps = new FileOutputStream(path);
                fOps.write(bytes);
                fOps.flush();
                fOps.close();

            } catch (Exception e){
                e.printStackTrace();
            }*/

            /*File file = new File(upload, fileName);

            try (InputStream input = filePart.getInputStream()){
                Files.copy(input, file.toPath());
            }*/

            /*String appPath = request.getServletContext().getRealPath("");
            String savePath = appPath + File.separator + SAVE_DIR;
            filePart.write(appPath + File.separator + filePart);


            // refines the fileName in case it is an absolute path
            fileName = new File(fileName).getName();
            filePart.write(savePath + File.separator + fileName);*/


            /*carsEntity.setPicture(fileName);*/

            carsService.updateCar(em, carsEntity);

            // Modification dans ads

            // On récupère l'annonce grâce à l'ID
            adsEntity = adsService.consulter(em, idAd);

            adsEntity.setLabel(labelAd);
            adsEntity.setPrice(priceAd);
            adsEntity.setTypesAds(enumTypesAds);

            // Update de l'annonce
            adsService.updateAds(em, adsEntity);

            tx.commit();
        } catch (Exception ex) {
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