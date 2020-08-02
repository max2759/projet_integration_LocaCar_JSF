package entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "users_subscriptions", schema = "projet_bac_info2")
public class UsersSubscriptionsEntity {
    private int id;
    private double finalPrice;
    private Date startDate;
    private Date endDate;
    private boolean isActive;
    private UsersEntity usersByIdUsers;
    private SubscriptionsEntity subscriptionsByIdSubscriptions;

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
    @Column(name = "Final_Price", nullable = false, precision = 0)
    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    @Basic
    @Column(name = "Start_Date", nullable = false)
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "End_Date", nullable = false)
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
        UsersSubscriptionsEntity that = (UsersSubscriptionsEntity) o;
        return id == that.id &&
                Double.compare(that.finalPrice, finalPrice) == 0 &&
                isActive == that.isActive &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, finalPrice, startDate, endDate, isActive);
    }

    @ManyToOne
    @JoinColumn(name = "ID_Users", referencedColumnName = "ID", nullable = false)
    public UsersEntity getUsersByIdUsers() {
        return usersByIdUsers;
    }

    public void setUsersByIdUsers(UsersEntity usersByIdUsers) {
        this.usersByIdUsers = usersByIdUsers;
    }

    @ManyToOne
    @JoinColumn(name = "ID_Subscriptions", referencedColumnName = "ID", nullable = false)
    public SubscriptionsEntity getSubscriptionsByIdSubscriptions() {
        return subscriptionsByIdSubscriptions;
    }

    public void setSubscriptionsByIdSubscriptions(SubscriptionsEntity subscriptionsByIdSubscriptions) {
        this.subscriptionsByIdSubscriptions = subscriptionsByIdSubscriptions;
    }
}
