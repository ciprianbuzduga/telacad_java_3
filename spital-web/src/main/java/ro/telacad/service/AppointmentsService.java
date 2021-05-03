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
import ro.telacad.dao.AppointmentsDao;
import ro.telacad.entity.Appointments;

/**
 *
 * @author Buzy
 */
@Stateless
public class AppointmentsService {
    
    @EJB
    private AppointmentsDao appointmentsDao;
    
    public List<Appointments> listAppointmentsByUserId(Integer userId) {
        return appointmentsDao.listAppointmentsByUserId(userId);
    }
    
    public Appointments getById(Integer id) {
        return appointmentsDao.getById(id);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Appointments save(Appointments app) {
        return appointmentsDao.save(app);
    }
}
