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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Buzy
 */
@Entity
@Table(name = "appointments")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Appointments.findAll", query = "SELECT a FROM Appointments a"),
    @NamedQuery(name = "Appointments.findById", query = "SELECT a FROM Appointments a WHERE a.id = :id"),
    @NamedQuery(name = "Appointments.findByDay", query = "SELECT a FROM Appointments a WHERE a.day = :day"),
    @NamedQuery(name = "Appointments.findByDescription", query = "SELECT a FROM Appointments a WHERE a.description = :description")})
public class Appointments implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "day")
    @Temporal(TemporalType.DATE)
    private Date day;
    @Size(max = 200)
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "user", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users user;
    @JoinColumn(name = "patient", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Patients patient;
    @JoinColumn(name = "consultation", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TypeConsultation consultation;
    @Basic(optional = false)
    @NotNull
    @Column(name = "start_time")
    @Temporal(TemporalType.TIME)
    private Date startTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "end_time")
    @Temporal(TemporalType.TIME)
    private Date endTime;

    public Appointments() {
    }

    public Appointments(Integer id) {
        this.id = id;
    }

    public Appointments(Integer id, Date day) {
        this.id = id;
        this.day = day;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Patients getPatient() {
        return patient;
    }

    public void setPatient(Patients patient) {
        this.patient = patient;
    }

    public TypeConsultation getConsultation() {
        return consultation;
    }

    public void setConsultation(TypeConsultation consultation) {
        this.consultation = consultation;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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
        if (!(object instanceof Appointments)) {
            return false;
        }
        Appointments other = (Appointments) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ro.telacad.entity.Appointments[ id=" + id + " ]";
    }
}
