package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "insurances", schema = "projet_bac_info2", catalog = "")
public class InsurancesEntity {
    private int id;
    private String label;
    private String description;
    private double price;

    @Id
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

    @Basic
    @Column(name = "Description", nullable = false, length = 255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "Price", nullable = false, precision = 0)
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InsurancesEntity that = (InsurancesEntity) o;
        return id == that.id &&
                Double.compare(that.price, price) == 0 &&
                Objects.equals(label, that.label) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label, description, price);
    }
}
