package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "adresses", schema = "projet_bac_info2", catalog = "")
public class AdressesEntity {
    private int id;
    private String street;
    private int number;
    private String box;
    private CitiesEntity citiesByIdCities;
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
    @Column(name = "Street", nullable = false, length = 255)
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Basic
    @Column(name = "Number", nullable = false)
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Basic
    @Column(name = "Box", nullable = false, length = 255)
    public String getBox() {
        return box;
    }

    public void setBox(String box) {
        this.box = box;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdressesEntity that = (AdressesEntity) o;
        return id == that.id &&
                number == that.number &&
                Objects.equals(street, that.street) &&
                Objects.equals(box, that.box);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, street, number, box);
    }

    @ManyToOne
    @JoinColumn(name = "ID_Cities", referencedColumnName = "ID", nullable = false)
    public CitiesEntity getCitiesByIdCities() {
        return citiesByIdCities;
    }

    public void setCitiesByIdCities(CitiesEntity citiesByIdCities) {
        this.citiesByIdCities = citiesByIdCities;
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
