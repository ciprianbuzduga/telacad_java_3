/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.telacad.named;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import ro.telacad.entity.Appointments;
import ro.telacad.entity.Patients;
import ro.telacad.entity.TypeConsultation;
import ro.telacad.entity.Users;
import ro.telacad.form.AppointmentForm;
import ro.telacad.service.AppointmentsService;
import ro.telacad.service.PatientsService;
import ro.telacad.service.TypeConsultationService;
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
public class AppointmentsNamed implements Serializable {
    
    @EJB
    private AppointmentsService appointmentsService;
    @EJB
    private TypeConsultationService typeConsultationService;
    @EJB
    private PatientsService patientsService;
    @EJB
    private UsersService usersService;
    
    private List<Appointments> appointments;
    private List<TypeConsultation> typeConsultations;
    private List<Patients> patientses;
    private AppointmentForm appointmentForm;
    
    @PostConstruct
    public void init() {
        appointments = appointmentsService.listAppointmentsByUserId(SessionUtil.getUserId());
        typeConsultations = typeConsultationService.listTypeConsultations();
        patientses = patientsService.list();
        appointmentForm = new AppointmentForm();
    }

    public int sortByPatient(Object o1, Object o2) {
        Patients p1 = (Patients) o1;
        Patients p2 = (Patients) o2;
        return Utils.sortByPatientFirstLastName(p1, p2);
    }
    
    public void showDlgAppointment(Appointments app) {
        appointmentForm = new AppointmentForm();
        if(app != null) {
            appointmentForm.setId(app.getId());
            appointmentForm.setDay(app.getDay());
            appointmentForm.setDescription(app.getDescription());
            appointmentForm.setPatientId(app.getPatient().getId());
            appointmentForm.setTypeConsultationId(app.getConsultation().getId());
            appointmentForm.setStartTime(app.getStartTime());
            appointmentForm.setEndTime(app.getEndTime());
        }
    }
    
    public void save() {
        Appointments app = appointmentsService.getById(appointmentForm.getId());
        if(app == null) {
            app = new Appointments();
        }
        TypeConsultation tc = typeConsultationService.getById(appointmentForm.getTypeConsultationId());
        app.setConsultation(tc);
        app.setDay(appointmentForm.getDay());
        app.setDescription(appointmentForm.getDescription());
        Patients p = patientsService.getById(appointmentForm.getPatientId());
        app.setPatient(p);
        Users u = usersService.getUserById(SessionUtil.getUserId());
        app.setUser(u);
        app.setStartTime(appointmentForm.getStartTime());
        app.setEndTime(appointmentForm.getEndTime());
        
        try {
            appointmentsService.save(app);
            appointments = appointmentsService.listAppointmentsByUserId(SessionUtil.getUserId());
            FacesMessageUtil.addMessageSaveOk();
        } catch (Exception e) {
            FacesMessageUtil.addMessageSaveError();
            e.printStackTrace();
        }
    }
    
    public List<Appointments> getAppointments() {
        return appointments;
    }

    public List<TypeConsultation> getTypeConsultations() {
        return typeConsultations;
    }

    public AppointmentForm getAppointmentForm() {
        return appointmentForm;
    }

    public List<Patients> getPatientses() {
        return patientses;
    }
}
