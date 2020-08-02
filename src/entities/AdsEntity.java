package entities;

import enumeration.EnumFuel;
import enumeration.EnumTypesAds;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "ads", schema = "projet_bac_info2")
public class AdsEntity {
    private int id;
    private double price;
    private Date dateStart;
    private Date dateEnd;
    private EnumTypesAds typesAds;
    private String label;
    private boolean isActive;
    private CarsEntity carsByIdCars;

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
    @Column(name = "Price", nullable = false, precision = 0)
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "Date_Start", nullable = false)
    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    @Basic
    @Column(name = "Date_End", nullable = false)
    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }


    @Enumerated(EnumType.STRING)
    @Basic
    @Column(name = "Types_Ads", nullable = false)
    public EnumTypesAds getTypesAds() {
        return typesAds;
    }



    public void setTypesAds(EnumTypesAds typesAds) {
        this.typesAds = typesAds;
    }

    @Basic
    @Column(name = "Label", nullable = false, length = 255)
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Basic
    @Column(name = "IsActive", nullable = false)
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
        AdsEntity adsEntity = (AdsEntity) o;
        return id == adsEntity.id &&
                Double.compare(adsEntity.price, price) == 0 &&
                isActive == adsEntity.isActive &&
                Objects.equals(dateStart, adsEntity.dateStart) &&
                Objects.equals(dateEnd, adsEntity.dateEnd) &&
                Objects.equals(typesAds, adsEntity.typesAds) &&
                Objects.equals(label, adsEntity.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, dateStart, dateEnd, typesAds, label, isActive);
    }

    @ManyToOne
    @JoinColumn(name = "ID_Cars", referencedColumnName = "ID", nullable = false)
    public CarsEntity getCarsByIdCars() {
        return carsByIdCars;
    }

    public void setCarsByIdCars(CarsEntity carsByIdCars) {
        this.carsByIdCars = carsByIdCars;
    }
}
