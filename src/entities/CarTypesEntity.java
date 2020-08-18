package entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "car_types", schema = "projet_bac_info2")
@NamedQueries({
        // recherche de catégorie par ID
        @NamedQuery(name="car_types.findCarTypesById",
                query = "SELECT ct FROM CarTypesEntity ct WHERE ct.id = :id"
        ),
        @NamedQuery(name="car_types.listCT",
                query = "SELECT ct FROM CarTypesEntity ct"
        ),
        @NamedQuery(name="car_types.checkLabel",
                query = "SELECT ct FROM CarTypesEntity ct where ct.label = :label"
        )
})
public class CarTypesEntity {
    private int id;
    private String label;
    private Collection<CarsEntity> carsById;

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
        CarTypesEntity that = (CarTypesEntity) o;
        return id == that.id &&
                Objects.equals(label, that.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label);
    }

    @OneToMany(mappedBy = "carTypesByIdCarTypes")
    public Collection<CarsEntity> getCarsById() {
        return carsById;
    }

    public void setCarsById(Collection<CarsEntity> carsById) {
        this.carsById = carsById;
    }
}
