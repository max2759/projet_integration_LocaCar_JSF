package entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "orders", schema = "projet_bac_info2", catalog = "")
public class OrdersEntity {
    private int id;
    private Date orderDate;
    private Object orderStatut;
    private Collection<ContractsEntity> contractsById;
    private Collection<InvoicesEntity> invoicesById;
    private UsersEntity usersByIdUsers;

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
    @Column(name = "Order_Date", nullable = false)
    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Basic
    @Column(name = "Order_Statut", nullable = false)
    public Object getOrderStatut() {
        return orderStatut;
    }

    public void setOrderStatut(Object orderStatut) {
        this.orderStatut = orderStatut;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdersEntity that = (OrdersEntity) o;
        return id == that.id &&
                Objects.equals(orderDate, that.orderDate) &&
                Objects.equals(orderStatut, that.orderStatut);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderDate, orderStatut);
    }

    @OneToMany(mappedBy = "ordersByIdOrders")
    public Collection<ContractsEntity> getContractsById() {
        return contractsById;
    }

    public void setContractsById(Collection<ContractsEntity> contractsById) {
        this.contractsById = contractsById;
    }

    @OneToMany(mappedBy = "ordersByIdOrders")
    public Collection<InvoicesEntity> getInvoicesById() {
        return invoicesById;
    }

    public void setInvoicesById(Collection<InvoicesEntity> invoicesById) {
        this.invoicesById = invoicesById;
    }

    @ManyToOne
    @JoinColumn(name = "ID_Users", referencedColumnName = "ID", nullable = false)
    public UsersEntity getUsersByIdUsers() {
        return usersByIdUsers;
    }

    public void setUsersByIdUsers(UsersEntity usersByIdUsers) {
        this.usersByIdUsers = usersByIdUsers;
    }
}
