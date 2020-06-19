package entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "invoices", schema = "projet_bac_info2", catalog = "")
public class InvoicesEntity {
    private int id;
    private Date invoiceDate;
    private String label;
    private OrdersEntity ordersByIdOrders;

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
    @Column(name = "Invoice_Date", nullable = false)
    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
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
        InvoicesEntity that = (InvoicesEntity) o;
        return id == that.id &&
                Objects.equals(invoiceDate, that.invoiceDate) &&
                Objects.equals(label, that.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, invoiceDate, label);
    }

    @ManyToOne
    @JoinColumn(name = "ID_Orders", referencedColumnName = "ID", nullable = false)
    public OrdersEntity getOrdersByIdOrders() {
        return ordersByIdOrders;
    }

    public void setOrdersByIdOrders(OrdersEntity ordersByIdOrders) {
        this.ordersByIdOrders = ordersByIdOrders;
    }
}
