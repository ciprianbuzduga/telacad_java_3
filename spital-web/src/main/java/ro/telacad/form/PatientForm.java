/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.telacad.form;

import java.io.Serializable;
import java.math.BigInteger;

/**
 *
 * @author Buzy
 */
public class PatientForm implements Serializable {
    
    private Integer id;
    private String firstName;
    private String lastName;
    private Integer telefon;
    private String cnp;
    private String email;
    private BigInteger insurrantCode;

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

    public Integer getTelefon() {
        return telefon;
    }

    public void setTelefon(Integer telefon) {
        this.telefon = telefon;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigInteger getInsurrantCode() {
        return insurrantCode;
    }

    public void setInsurrantCode(BigInteger insurrantCode) {
        this.insurrantCode = insurrantCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    
}
