package entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "projet_bac_info2")
public class UsersEntity {
    private int id;
    private String surname;
    private String name;
    private String username;
    private String password;
    private Date dateBirth;
    private boolean isActive;
    private String vatNumber;
    private Collection<AdressesEntity> adressesById;
    private Collection<OrdersEntity> ordersById;
    private RolesEntity rolesByIdRoles;
    private Collection<UsersAdsEntity> usersAdsById;
    private Collection<UsersSubscriptionsEntity> usersSubscriptionsById;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Surname")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "Username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "Password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "Date_Birth")
    public Date getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }

    @Basic
    @Column(name = "IsActive", nullable = false)
    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Basic
    @Column(name = "VATNumber")
    public String getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersEntity that = (UsersEntity) o;
        return id == that.id &&
                isActive == that.isActive &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(name, that.name) &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(dateBirth, that.dateBirth) &&
                Objects.equals(vatNumber, that.vatNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, surname, name, username, password, dateBirth, isActive, vatNumber);
    }

    @OneToMany(mappedBy = "usersByIdUsers")
    public Collection<AdressesEntity> getAdressesById() {
        return adressesById;
    }

    public void setAdressesById(Collection<AdressesEntity> adressesById) {
        this.adressesById = adressesById;
    }

    @OneToMany(mappedBy = "usersByIdUsers")
    public Collection<OrdersEntity> getOrdersById() {
        return ordersById;
    }

    public void setOrdersById(Collection<OrdersEntity> ordersById) {
        this.ordersById = ordersById;
    }

    @ManyToOne
    @JoinColumn(name = "ID_Roles", referencedColumnName = "ID", nullable = false)
    public RolesEntity getRolesByIdRoles() {
        return rolesByIdRoles;
    }

    public void setRolesByIdRoles(RolesEntity rolesByIdRoles) {
        this.rolesByIdRoles = rolesByIdRoles;
    }

    @OneToMany(mappedBy = "usersByIdUsers")
    public Collection<UsersAdsEntity> getUsersAdsById() {
        return usersAdsById;
    }

    public void setUsersAdsById(Collection<UsersAdsEntity> usersAdsById) {
        this.usersAdsById = usersAdsById;
    }

    @OneToMany(mappedBy = "usersByIdUsers")
    public Collection<UsersSubscriptionsEntity> getUsersSubscriptionsById() {
        return usersSubscriptionsById;
    }

    public void setUsersSubscriptionsById(Collection<UsersSubscriptionsEntity> usersSubscriptionsById) {
        this.usersSubscriptionsById = usersSubscriptionsById;
    }
}
