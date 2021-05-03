/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.telacad.service;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import ro.telacad.dao.PatientsDao;
import ro.telacad.entity.Patients;

/**
 *
 * @author Buzy
 */
@Stateless
public class PatientsService {
    
    @EJB
    private PatientsDao patientsDao;
    
    public Patients getByCnp(String cnp) {
        return patientsDao.getByCnp(cnp);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Patients save(Patients patients) {
        return patientsDao.save(patients);
    }
    
    public Patients getById(Integer id) {
        return patientsDao.getById(id);
    }
    
    public List<Patients> list() {
        return patientsDao.list();
    }
}
