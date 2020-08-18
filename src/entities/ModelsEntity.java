package entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "models", schema = "projet_bac_info2")
public class ModelsEntity {
    private int id;
    private String label;
    private Collection<CarsEntity> carsById;
    private BrandsEntity brandsByIdBrands;

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
        ModelsEntity that = (ModelsEntity) o;
        return id == that.id &&
                Objects.equals(label, that.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label);
    }

    @OneToMany(mappedBy = "modelsByIdModels")
    public Collection<CarsEntity> getCarsById() {
        return carsById;
    }

    public void setCarsById(Collection<CarsEntity> carsById) {
        this.carsById = carsById;
    }

    @ManyToOne
    @JoinColumn(name = "ID_Brands", referencedColumnName = "ID", nullable = false)
    public BrandsEntity getBrandsByIdBrands() {
        return brandsByIdBrands;
    }

    public void setBrandsByIdBrands(BrandsEntity brandsByIdBrands) {
        this.brandsByIdBrands = brandsByIdBrands;
    }
}
