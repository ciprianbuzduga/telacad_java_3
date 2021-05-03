/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.telacad.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Buzy
 */
@Entity
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findById", query = "SELECT u FROM Users u WHERE u.id = :id"),
    @NamedQuery(name = "Users.findByFirstName", query = "SELECT u FROM Users u WHERE u.firstName = :firstName"),
    @NamedQuery(name = "Users.findByLastName", query = "SELECT u FROM Users u WHERE u.lastName = :lastName"),
    @NamedQuery(name = "Users.findByUserName", query = "SELECT u FROM Users u WHERE u.userName = :userName"),
    @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password"),
    @NamedQuery(name = "Users.findByRegisterDate", query = "SELECT u FROM Users u WHERE u.registerDate = :registerDate"),
    @NamedQuery(name = "Users.findByRegistrationNumber", query = "SELECT u FROM Users u WHERE u.registrationNumber = :registrationNumber"),
    @NamedQuery(name = "Users.findByTelephon", query = "SELECT u FROM Users u WHERE u.telephon = :telephon"),
    @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email"),
    @NamedQuery(name = "Users.findByCnp", query = "SELECT u FROM Users u WHERE u.cnp = :cnp")})
public class Users implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Collection<UsersLogin> usersLoginCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Collection<Appointments> appointmentsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Collection<ProgramJob> programJobCollection;
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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "user_name")
    private String userName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Column(name = "register_date")
    @Temporal(TemporalType.DATE)
    private Date registerDate;
    @Column(name = "registration_number")
    private String registrationNumber;
    @Column(name = "telephon")
    private Integer telephon;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "email")
    private String email;
    @Column(name = "cnp")
    private String cnp;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUser")
    private Collection<MapPatientsDoctor> mapPatientsDoctorCollection;
    @JoinColumn(name = "id_medical_speciality", referencedColumnName = "id")
    @ManyToOne
    private MedicalSpeciality idMedicalSpeciality;
    @JoinColumn(name = "id_role", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Role idRole;

    public Users() {
    }

    public Users(Integer id) {
        this.id = id;
    }

    public Users(Integer id, String firstName, String lastName, String userName, String password, Date registerDate, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.registerDate = registerDate;
        this.email = email;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
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

    @XmlTransient
    public Collection<MapPatientsDoctor> getMapPatientsDoctorCollection() {
        return mapPatientsDoctorCollection;
    }

    public void setMapPatientsDoctorCollection(Collection<MapPatientsDoctor> mapPatientsDoctorCollection) {
        this.mapPatientsDoctorCollection = mapPatientsDoctorCollection;
    }

    public MedicalSpeciality getIdMedicalSpeciality() {
        return idMedicalSpeciality;
    }

    public void setIdMedicalSpeciality(MedicalSpeciality idMedicalSpeciality) {
        this.idMedicalSpeciality = idMedicalSpeciality;
    }

    public Role getIdRole() {
        return idRole;
    }

    public void setIdRole(Role idRole) {
        this.idRole = idRole;
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
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ro.telacad.entity.Users[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<Appointments> getAppointmentsCollection() {
        return appointmentsCollection;
    }

    public void setAppointmentsCollection(Collection<Appointments> appointmentsCollection) {
        this.appointmentsCollection = appointmentsCollection;
    }

    @XmlTransient
    public Collection<ProgramJob> getProgramJobCollection() {
        return programJobCollection;
    }

    public void setProgramJobCollection(Collection<ProgramJob> programJobCollection) {
        this.programJobCollection = programJobCollection;
    }

    @XmlTransient
    public Collection<UsersLogin> getUsersLoginCollection() {
        return usersLoginCollection;
    }

    public void setUsersLoginCollection(Collection<UsersLogin> usersLoginCollection) {
        this.usersLoginCollection = usersLoginCollection;
    }
    
}
