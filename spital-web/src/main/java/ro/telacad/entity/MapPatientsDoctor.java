/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.telacad.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Buzy
 */
@Entity
@Table(name = "map_patients_doctor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MapPatientsDoctor.findAll", query = "SELECT m FROM MapPatientsDoctor m"),
    @NamedQuery(name = "MapPatientsDoctor.findById", query = "SELECT m FROM MapPatientsDoctor m WHERE m.id = :id"),
    @NamedQuery(name = "MapPatientsDoctor.findByRegisterDate", query = "SELECT m FROM MapPatientsDoctor m WHERE m.registerDate = :registerDate")})
public class MapPatientsDoctor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "register_date")
    @Temporal(TemporalType.DATE)
    private Date registerDate;
    @JoinColumn(name = "id_patient", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Patients idPatient;
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users idUser;

    public MapPatientsDoctor() {
    }

    public MapPatientsDoctor(Integer id) {
        this.id = id;
    }

    public MapPatientsDoctor(Integer id, Date registerDate) {
        this.id = id;
        this.registerDate = registerDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Patients getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(Patients idPatient) {
        this.idPatient = idPatient;
    }

    public Users getIdUser() {
        return idUser;
    }

    public void setIdUser(Users idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MapPatientsDoctor)) {
            return false;
        }
        MapPatientsDoctor other = (MapPatientsDoctor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ro.telacad.entity.MapPatientsDoctor[ id=" + id + " ]";
    }
    
}
