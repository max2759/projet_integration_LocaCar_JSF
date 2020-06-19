package entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "countries", schema = "projet_bac_info2", catalog = "")
public class CountriesEntity {
    private int id;
    private String label;
    private Collection<CitiesEntity> citiesById;

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
        CountriesEntity that = (CountriesEntity) o;
        return id == that.id &&
                Objects.equals(label, that.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label);
    }

    @OneToMany(mappedBy = "countriesByIdCountries")
    public Collection<CitiesEntity> getCitiesById() {
        return citiesById;
    }

    public void setCitiesById(Collection<CitiesEntity> citiesById) {
        this.citiesById = citiesById;
    }
}
