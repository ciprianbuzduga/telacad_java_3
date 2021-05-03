/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.telacad.named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.json.JsonArray;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.primefaces.event.SelectEvent;
import ro.telacad.entity.Appointments;
import ro.telacad.entity.MapPatientsDoctor;
import ro.telacad.entity.Patients;
import ro.telacad.entity.Users;
import ro.telacad.form.PatientForm;
import ro.telacad.service.MapPatientsService;
import ro.telacad.service.PatientsService;
import ro.telacad.service.UsersService;
import ro.telacad.util.FacesMessageUtil;
import ro.telacad.util.SessionUtil;
import ro.telacad.util.Utils;

/**
 *
 * @author Buzy
 */
@Named
@ViewScoped
public class MapPatientsNamed implements Serializable {
    
    @EJB
    private MapPatientsService mapPatientsService;
    @EJB
    private PatientsService patientsService;
    @EJB
    private UsersService usersService;
    
    private List<MapPatientsDoctor> mapPatients;
    private PatientForm patientForm;
    
    @PostConstruct
    public void init() {
        Integer idUser = SessionUtil.getUserId();
        mapPatients = mapPatientsService.listPatientsByUserId(idUser);
    }
    
    public void showPatientDlg() {
        patientForm = new PatientForm();
    }
    
    public void savePatient() {
        Patients patients = patientsService.getById(patientForm.getId());
        Patients newPatients = null;
        if(patients == null) {
            patients = new Patients();
            patients.setCnp(patientForm.getCnp());
            patients.setEmail(patientForm.getEmail());
            patients.setFirstName(patientForm.getFirstName());
            patients.setInsurantCode(patientForm.getInsurrantCode());
            patients.setLastName(patientForm.getLastName());
            patients.setTelephon(patientForm.getTelefon());
            try {
                newPatients = patientsService.save(patients);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            newPatients = patients;
        }
        
        if(newPatients == null) {
            FacesMessageUtil.addMessageSaveError();
            return;
        }
        
        MapPatientsDoctor mapPatientsDoctor = new MapPatientsDoctor();
        mapPatientsDoctor.setIdPatient(newPatients);
        mapPatientsDoctor.setRegisterDate(new Date());
        Integer idUser = SessionUtil.getUserId();
        Users users = usersService.getUserById(idUser);
        mapPatientsDoctor.setIdUser(users);
        try {
            mapPatientsService.save(mapPatientsDoctor);
            mapPatients = mapPatientsService.listPatientsByUserId(idUser);
            FacesMessageUtil.addMessageSaveOk();
        } catch (Exception e) {
            FacesMessageUtil.addMessageSaveError();
            e.printStackTrace();
        }
                
    }
    
    public List<String> completeTextCnp(String query) {
        if(query == null) {
            return null;
        }
        
        Integer userId = SessionUtil.getUserId();
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/spital-web/rest/cnp/search?"
                + "userId=" + userId + "&query=" + query);
        JsonArray response = target.request(MediaType.APPLICATION_JSON).get(JsonArray.class);
        List<String> cnps = new ArrayList<>();
        if(response != null) {
           for(Object obj : response) {
               cnps.add(obj.toString().replaceAll("\"", ""));
           }
        }
        return cnps;
    }
    
    public int sortByPatient(Object o1, Object o2) {
        Patients p1 = (Patients) o1;
        Patients p2 = (Patients) o2;
        return Utils.sortByPatientFirstLastName(p1, p2);
    }
    
    public void onItemCnpSelect(SelectEvent event) {
        String cnp = (String)event.getObject();     
        if (cnp != null) {
            FacesMessageUtil.addMessageInfo("A fost gasit pacientul cu cnp " + cnp + " in baza de date.");
            Patients patients = patientsService.getByCnp(cnp);
            patientForm.setId(patients.getId());
            patientForm.setCnp(patients.getCnp());
            patientForm.setEmail(patients.getEmail());
            patientForm.setFirstName(patients.getFirstName());
            patientForm.setInsurrantCode(patients.getInsurantCode());
            patientForm.setLastName(patients.getLastName());
            patientForm.setTelefon(patients.getTelephon());
        }
    }

    public List<MapPatientsDoctor> getMapPatients() {
        return mapPatients;
    }

    public PatientForm getPatientForm() {
        return patientForm;
    }
}
