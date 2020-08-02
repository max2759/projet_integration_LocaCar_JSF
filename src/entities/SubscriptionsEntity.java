package entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "subscriptions", schema = "projet_bac_info2")
public class SubscriptionsEntity {
    private int id;
    private String label;
    private double price;
    private Collection<UsersSubscriptionsEntity> usersSubscriptionsById;

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
        SubscriptionsEntity that = (SubscriptionsEntity) o;
        return id == that.id &&
                Double.compare(that.price, price) == 0 &&
                Objects.equals(label, that.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label, price);
    }

    @OneToMany(mappedBy = "subscriptionsByIdSubscriptions")
    public Collection<UsersSubscriptionsEntity> getUsersSubscriptionsById() {
        return usersSubscriptionsById;
    }

    public void setUsersSubscriptionsById(Collection<UsersSubscriptionsEntity> usersSubscriptionsById) {
        this.usersSubscriptionsById = usersSubscriptionsById;
    }
}
