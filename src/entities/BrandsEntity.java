package entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "brands", schema = "projet_bac_info2", catalog = "")
public class BrandsEntity {
    private int id;
    private String label;
    private Collection<ModelsEntity> modelsById;

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
        BrandsEntity that = (BrandsEntity) o;
        return id == that.id &&
                Objects.equals(label, that.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label);
    }

    @OneToMany(mappedBy = "brandsByIdBrands")
    public Collection<ModelsEntity> getModelsById() {
        return modelsById;
    }

    public void setModelsById(Collection<ModelsEntity> modelsById) {
        this.modelsById = modelsById;
    }
}
