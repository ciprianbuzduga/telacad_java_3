/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.telacad.form;

import java.io.Serializable;

/**
 *
 * @author Buzy
 */
public class RegisterForm implements Serializable {
    
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String cnp;
    private Integer idSpeciality;
    private String numarRegistru;
    private Integer telephon;
    private String email;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public Integer getIdSpeciality() {
        return idSpeciality;
    }

    public void setIdSpeciality(Integer idSpeciality) {
        this.idSpeciality = idSpeciality;
    }

    public String getNumarRegistru() {
        return numarRegistru;
    }

    public void setNumarRegistru(String numarRegistru) {
        this.numarRegistru = numarRegistru;
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
    
}
