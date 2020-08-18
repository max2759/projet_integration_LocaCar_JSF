package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "contract_insurances", schema = "projet_bac_info2")
public class ContractInsurancesEntity {
    private int id;
    private int idInsurance;
    private int idContract;

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
    @Column(name = "ID_Insurance", nullable = false)
    public int getIdInsurance() {
        return idInsurance;
    }

    public void setIdInsurance(int idInsurance) {
        this.idInsurance = idInsurance;
    }

    @Basic
    @Column(name = "ID_Contract", nullable = false)
    public int getIdContract() {
        return idContract;
    }

    public void setIdContract(int idContract) {
        this.idContract = idContract;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContractInsurancesEntity that = (ContractInsurancesEntity) o;
        return id == that.id &&
                idInsurance == that.idInsurance &&
                idContract == that.idContract;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idInsurance, idContract);
    }
}
