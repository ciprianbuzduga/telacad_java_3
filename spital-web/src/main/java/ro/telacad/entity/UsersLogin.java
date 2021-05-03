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
@Table(name = "users_login")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsersLogin.findAll", query = "SELECT u FROM UsersLogin u"),
    @NamedQuery(name = "UsersLogin.findById", query = "SELECT u FROM UsersLogin u WHERE u.id = :id"),
    @NamedQuery(name = "UsersLogin.findByLoginDate", query = "SELECT u FROM UsersLogin u WHERE u.loginDate = :loginDate"),
    @NamedQuery(name = "UsersLogin.findByLoginOk", query = "SELECT u FROM UsersLogin u WHERE u.loginOk = :loginOk")})
public class UsersLogin implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "login_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date loginDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "login_ok")
    private int loginOk;
    @JoinColumn(name = "user", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users user;

    public UsersLogin() {
    }

    public UsersLogin(Integer id) {
        this.id = id;
    }

    public UsersLogin(Integer id, Date loginDate, int loginOk) {
        this.id = id;
        this.loginDate = loginDate;
        this.loginOk = loginOk;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public int getLoginOk() {
        return loginOk;
    }

    public void setLoginOk(int loginOk) {
        this.loginOk = loginOk;
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
        if (!(object instanceof UsersLogin)) {
            return false;
        }
        UsersLogin other = (UsersLogin) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ro.telacad.entity.UsersLogin[ id=" + id + " ]";
    }
    
}
