package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users_ads", schema = "projet_bac_info2")
public class UsersAdsEntity {
    private int id;
    private boolean isFavorite;
    private UsersEntity usersByIdUsers;
    private AdsEntity adsByIdAds;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "IsFavorite")
    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersAdsEntity that = (UsersAdsEntity) o;
        return id == that.id &&
                Objects.equals(isFavorite, that.isFavorite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isFavorite);
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
    @JoinColumn(name = "ID_Ads", referencedColumnName = "ID", nullable = false)
    public AdsEntity getAdsByIdAds() {
        return adsByIdAds;
    }

    public void setAdsByIdAds(AdsEntity adsByIdAds) {
        this.adsByIdAds = adsByIdAds;
    }
}
