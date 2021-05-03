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
import ro.telacad.entity.ProgramJob;
import ro.telacad.entity.Users;
import ro.telacad.form.ProgramForm;
import ro.telacad.service.ProgramJobService;
import ro.telacad.service.UsersService;
import ro.telacad.util.FacesMessageUtil;
import ro.telacad.util.Utils;

/**
 *
 * @author Buzy
 */
@Named
@ViewScoped
public class ProgramNamed implements Serializable {
    
    @EJB
    private ProgramJobService programJobService;
    @EJB
    private UsersService usersService;
    
    private List<ProgramJob> programJobs;
    private ProgramForm programForm;
    private List<Users> users;
    
    @PostConstruct
    public void init() {
        programJobs = programJobService.list();
        users = usersService.list();
        programForm = new ProgramForm();
    }
    
    public int sortByUser(Object o1, Object o2) {
        Users a1 = (Users) o1;
        Users a2 = (Users) o2;
        return Utils.sortByUserFirstLastName(a1, a2);
    }
    
    public void save() {
        ProgramJob pj = new ProgramJob();
        pj.setDay(programForm.getDay());
        pj.setJobDescription(programForm.getJobDescription());
        pj.setEndTime(programForm.getEndTime());
        pj.setStartTime(programForm.getStartTime());
        Users user = usersService.getUserById(programForm.getIdUser());
        pj.setUser(user);
        try {
            programJobService.save(pj);
            programJobs = programJobService.list();
            FacesMessageUtil.addMessageSaveOk();
        } catch (Exception e) {
            FacesMessageUtil.addMessageSaveError();
            e.printStackTrace();
        }
    }

    public List<ProgramJob> getProgramJobs() {
        return programJobs;
    }

    public ProgramForm getProgramForm() {
        return programForm;
    }

    public List<Users> getUsers() {
        return users;
    }
}
