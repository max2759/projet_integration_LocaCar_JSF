package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users_ads", schema = "projet_bac_info2")
public class UsersAdsEntity {
    private int id;
    private int idUsers;
    private int idAds;
    private Boolean isFavorite;

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
    @Column(name = "ID_Users", nullable = false)
    public int getIdUsers() {
        return idUsers;
    }

    public void setIdUsers(int idUsers) {
        this.idUsers = idUsers;
    }

    @Basic
    @Column(name = "ID_Ads", nullable = false)
    public int getIdAds() {
        return idAds;
    }

    public void setIdAds(int idAds) {
        this.idAds = idAds;
    }

    @Basic
    @Column(name = "IsFavorite", nullable = true)
    public Boolean getFavorite() {
        return isFavorite;
    }

    public void setFavorite(Boolean favorite) {
        isFavorite = favorite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersAdsEntity that = (UsersAdsEntity) o;
        return id == that.id &&
                idUsers == that.idUsers &&
                idAds == that.idAds &&
                Objects.equals(isFavorite, that.isFavorite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idUsers, idAds, isFavorite);
    }
}
