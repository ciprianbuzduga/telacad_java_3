/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.telacad.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Buzy
 */
@Entity
@Table(name = "patients")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Patients.findAll", query = "SELECT p FROM Patients p"),
    @NamedQuery(name = "Patients.findById", query = "SELECT p FROM Patients p WHERE p.id = :id"),
    @NamedQuery(name = "Patients.findByFirstName", query = "SELECT p FROM Patients p WHERE p.firstName = :firstName"),
    @NamedQuery(name = "Patients.findByLastName", query = "SELECT p FROM Patients p WHERE p.lastName = :lastName"),
    @NamedQuery(name = "Patients.findByTelephon", query = "SELECT p FROM Patients p WHERE p.telephon = :telephon"),
    @NamedQuery(name = "Patients.findByEmail", query = "SELECT p FROM Patients p WHERE p.email = :email"),
    @NamedQuery(name = "Patients.findByCnp", query = "SELECT p FROM Patients p WHERE p.cnp = :cnp"),
    @NamedQuery(name = "Patients.findByInsurantCode", query = "SELECT p FROM Patients p WHERE p.insurantCode = :insurantCode")})
public class Patients implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patient")
    private Collection<Appointments> appointmentsCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "first_name")
    private String firstName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "telephon")
    private Integer telephon;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cnp")
    private String cnp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "insurant_code")
    private BigInteger insurantCode;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPatient")
    private Collection<MapPatientsDoctor> mapPatientsDoctorCollection;

    public Patients() {
    }

    public Patients(Integer id) {
        this.id = id;
    }

    public Patients(Integer id, String firstName, String lastName, String cnp, BigInteger insurantCode) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cnp = cnp;
        this.insurantCode = insurantCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getTelephon() {
        return telephon;
    }

    public void setTelephon(Integer telephon) {
        this.telephon = telephon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public BigInteger getInsurantCode() {
        return insurantCode;
    }

    public void setInsurantCode(BigInteger insurantCode) {
        this.insurantCode = insurantCode;
    }

    @XmlTransient
    public Collection<MapPatientsDoctor> getMapPatientsDoctorCollection() {
        return mapPatientsDoctorCollection;
    }

    public void setMapPatientsDoctorCollection(Collection<MapPatientsDoctor> mapPatientsDoctorCollection) {
        this.mapPatientsDoctorCollection = mapPatientsDoctorCollection;
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
        if (!(object instanceof Patients)) {
            return false;
        }
        Patients other = (Patients) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ro.telacad.entity.Patients[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<Appointments> getAppointmentsCollection() {
        return appointmentsCollection;
    }

    public void setAppointmentsCollection(Collection<Appointments> appointmentsCollection) {
        this.appointmentsCollection = appointmentsCollection;
    }
    
}
