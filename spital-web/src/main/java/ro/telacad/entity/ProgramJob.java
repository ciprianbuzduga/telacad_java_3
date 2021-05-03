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
@Table(name = "program_job")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProgramJob.findAll", query = "SELECT p FROM ProgramJob p"),
    @NamedQuery(name = "ProgramJob.findById", query = "SELECT p FROM ProgramJob p WHERE p.id = :id"),
    @NamedQuery(name = "ProgramJob.findByDay", query = "SELECT p FROM ProgramJob p WHERE p.day = :day"),
    @NamedQuery(name = "ProgramJob.findByStartTime", query = "SELECT p FROM ProgramJob p WHERE p.startTime = :startTime"),
    @NamedQuery(name = "ProgramJob.findByEndTime", query = "SELECT p FROM ProgramJob p WHERE p.endTime = :endTime"),
    @NamedQuery(name = "ProgramJob.findByJobDescription", query = "SELECT p FROM ProgramJob p WHERE p.jobDescription = :jobDescription")})
public class ProgramJob implements Serializable {
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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "job_description")
    private String jobDescription;
    @JoinColumn(name = "user", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users user;

    public ProgramJob() {
    }

    public ProgramJob(Integer id) {
        this.id = id;
    }

    public ProgramJob(Integer id, Date day, Date startTime, Date endTime, String jobDescription) {
        this.id = id;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.jobDescription = jobDescription;
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

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
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
        if (!(object instanceof ProgramJob)) {
            return false;
        }
        ProgramJob other = (ProgramJob) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ro.telacad.entity.ProgramJob[ id=" + id + " ]";
    }
    
}
