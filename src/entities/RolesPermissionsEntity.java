package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "roles_permissions", schema = "projet_bac_info2")
public class RolesPermissionsEntity {
    private int id;
    private boolean isActive;
    private RolesEntity rolesByIdRoles;
    private PermissionsEntity permissionsByIdPermissions;

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
        RolesPermissionsEntity that = (RolesPermissionsEntity) o;
        return id == that.id &&
                isActive == that.isActive;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isActive);
    }

    @ManyToOne
    @JoinColumn(name = "ID_Roles", referencedColumnName = "ID", nullable = false)
    public RolesEntity getRolesByIdRoles() {
        return rolesByIdRoles;
    }

    public void setRolesByIdRoles(RolesEntity rolesByIdRoles) {
        this.rolesByIdRoles = rolesByIdRoles;
    }

    @ManyToOne
    @JoinColumn(name = "ID_Permissions", referencedColumnName = "ID", nullable = false)
    public PermissionsEntity getPermissionsByIdPermissions() {
        return permissionsByIdPermissions;
    }

    public void setPermissionsByIdPermissions(PermissionsEntity permissionsByIdPermissions) {
        this.permissionsByIdPermissions = permissionsByIdPermissions;
    }
}
