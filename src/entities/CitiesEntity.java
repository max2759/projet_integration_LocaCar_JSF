package entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "cities", schema = "projet_bac_info2")
public class CitiesEntity {
    private int id;
    private String region;
    private int postalCode;
    private String label;
    private Collection<AdressesEntity> adressesById;
    private CountriesEntity countriesByIdCountries;

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
    @Column(name = "Region", nullable = false, length = 255)
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Basic
    @Column(name = "Postal_Code", nullable = false)
    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    @Basic
    @Column(name = "Label", nullable = false, length = 255)
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CitiesEntity that = (CitiesEntity) o;
        return id == that.id &&
                postalCode == that.postalCode &&
                Objects.equals(region, that.region) &&
                Objects.equals(label, that.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, region, postalCode, label);
    }

    @OneToMany(mappedBy = "citiesByIdCities")
    public Collection<AdressesEntity> getAdressesById() {
        return adressesById;
    }

    public void setAdressesById(Collection<AdressesEntity> adressesById) {
        this.adressesById = adressesById;
    }

    @ManyToOne
    @JoinColumn(name = "ID_Countries", referencedColumnName = "ID", nullable = false)
    public CountriesEntity getCountriesByIdCountries() {
        return countriesByIdCountries;
    }

    public void setCountriesByIdCountries(CountriesEntity countriesByIdCountries) {
        this.countriesByIdCountries = countriesByIdCountries;
    }
}
