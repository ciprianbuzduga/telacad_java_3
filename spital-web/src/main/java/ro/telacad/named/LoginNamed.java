/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.telacad.named;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import ro.telacad.entity.MedicalSpeciality;
import ro.telacad.entity.Role;
import ro.telacad.entity.Users;
import ro.telacad.form.RegisterForm;
import ro.telacad.interceptors.LoginInterceptor;
import ro.telacad.service.MedicalSpecialityService;
import ro.telacad.service.RoleService;
import ro.telacad.service.UsersService;
import ro.telacad.util.FacesMessageUtil;
import ro.telacad.util.RequestContextUtil;
import ro.telacad.util.SessionUtil;

/**
 *
 * @author Buzy
 */
@Named
@SessionScoped
public class LoginNamed implements Serializable {
    
    /**
     * Bug Netbeans nu te lasa sa pui campuri publice daca clasa nu e adnotata Dependent
     */
    //public static final String LOGIN = "login";
    
    private String username;
    private String password;
    private Integer idRole;
    private RegisterForm registerForm;
    private List<MedicalSpeciality> medicalSpecialities;
    private boolean loggedIn;
    
    @EJB
    private UsersService usersService;
    @EJB
    private MedicalSpecialityService medicalSpecialityService;
    @EJB
    private RoleService roleService;
    
    @PostConstruct
    public void init() {
        registerForm = new RegisterForm();
        medicalSpecialities = medicalSpecialityService.listSpeciality();
    }
    
    @Interceptors(LoginInterceptor.class)
    public String login(String username) {
        Users user = usersService.getByUsernameAndPassword(username, password);
        if(user != null) {  
            SessionUtil.setUserId(user.getId());
            SessionUtil.setUserName(username);
            idRole = user.getIdRole().getId();
            loggedIn = true;
            return "main.xhtml?faces-redirect=true";
        } else {
            FacesMessageUtil.addMessageError("Logare nereusita!", 
                    "User si parola introduse gresit, sau nu exista userul! Va rugam sa va inregistrati!");
            loggedIn = false;
            return "login";
        }
    }
    
    public String logout() {
        SessionUtil.invalidateSession();
        loggedIn = false;
        return "login";
    }
    
    public void onShowDlgRegister() {
        registerForm = new RegisterForm();
    }
    
    public void register() {
        Users users = usersService.getByUsername(registerForm.getUsername());
        if(users != null) {
            FacesMessageUtil.addMessageWarn("Userul " + username + " exista deja inregistrat!");
        } else {
            users = new Users();
            users.setCnp(registerForm.getCnp());
            users.setEmail(registerForm.getEmail());
            users.setFirstName(registerForm.getFirstname());
            MedicalSpeciality medicalSpeciality = medicalSpecialityService.getById(registerForm.getIdSpeciality());
            users.setIdMedicalSpeciality(medicalSpeciality);
            Role rol = roleService.getDoctorRole();
            users.setIdRole(rol);
            users.setLastName(registerForm.getLastname());
            users.setPassword(registerForm.getPassword());
            users.setRegisterDate(new Date());
            users.setRegistrationNumber(registerForm.getNumarRegistru());
            users.setTelephon(registerForm.getTelephon());
            users.setEmail(registerForm.getEmail());
            users.setUserName(registerForm.getUsername());
            
            try {
                usersService.save(users);
                username = registerForm.getUsername();
                password = registerForm.getPassword();
                FacesMessageUtil.addMessageInfo("Inregistrare cu succes! Va rugam sa va autentificati in aplicatie.");
                RequestContextUtil.hideDialog("dlgRegister");
            } catch (Exception e) {
                FacesMessageUtil.addMessageSaveError();
                e.printStackTrace();
            }
        }
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

    public RegisterForm getRegisterForm() {
        return registerForm;
    }

    public List<MedicalSpeciality> getMedicalSpecialities() {
        return medicalSpecialities;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
    
     public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }
}
