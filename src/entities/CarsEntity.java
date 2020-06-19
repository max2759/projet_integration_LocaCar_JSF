package entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "cars", schema = "projet_bac_info2", catalog = "")
public class CarsEntity {
    private int id;
    private String color;
    private Date releaseYear;
    private int kilometer;
    private int horsePower;
    private Object fuel;
    private String picture;
    private boolean isActive;
    private Collection<AdsEntity> adsById;
    private ModelsEntity modelsByIdModels;
    private CarTypesEntity carTypesByIdCarTypes;
    private Collection<ContractsEntity> contractsById;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Color", nullable = false, length = 255)
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Basic
    @Column(name = "Release_Year", nullable = false)
    public Date getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Date releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Basic
    @Column(name = "Kilometer", nullable = false)
    public int getKilometer() {
        return kilometer;
    }

    public void setKilometer(int kilometer) {
        this.kilometer = kilometer;
    }

    @Basic
    @Column(name = "HorsePower", nullable = false)
    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    @Basic
    @Column(name = "Fuel", nullable = false)
    public Object getFuel() {
        return fuel;
    }

    public void setFuel(Object fuel) {
        this.fuel = fuel;
    }

    @Basic
    @Column(name = "Picture", nullable = true, length = 255)
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Basic
    @Column(name = "isActive", nullable = false)
    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarsEntity that = (CarsEntity) o;
        return id == that.id &&
                kilometer == that.kilometer &&
                horsePower == that.horsePower &&
                isActive == that.isActive &&
                Objects.equals(color, that.color) &&
                Objects.equals(releaseYear, that.releaseYear) &&
                Objects.equals(fuel, that.fuel) &&
                Objects.equals(picture, that.picture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, color, releaseYear, kilometer, horsePower, fuel, picture, isActive);
    }

    @OneToMany(mappedBy = "carsByIdCars")
    public Collection<AdsEntity> getAdsById() {
        return adsById;
    }

    public void setAdsById(Collection<AdsEntity> adsById) {
        this.adsById = adsById;
    }

    @ManyToOne
    @JoinColumn(name = "ID_Models", referencedColumnName = "ID", nullable = false)
    public ModelsEntity getModelsByIdModels() {
        return modelsByIdModels;
    }

    public void setModelsByIdModels(ModelsEntity modelsByIdModels) {
        this.modelsByIdModels = modelsByIdModels;
    }

    @ManyToOne
    @JoinColumn(name = "Id_Car_Types", referencedColumnName = "ID", nullable = false)
    public CarTypesEntity getCarTypesByIdCarTypes() {
        return carTypesByIdCarTypes;
    }

    public void setCarTypesByIdCarTypes(CarTypesEntity carTypesByIdCarTypes) {
        this.carTypesByIdCarTypes = carTypesByIdCarTypes;
    }

    @OneToMany(mappedBy = "carsByIdCars")
    public Collection<ContractsEntity> getContractsById() {
        return contractsById;
    }

    public void setContractsById(Collection<ContractsEntity> contractsById) {
        this.contractsById = contractsById;
    }
}
