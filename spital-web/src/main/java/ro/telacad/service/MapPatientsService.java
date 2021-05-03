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
import ro.telacad.dao.MapPatientsDoctorDao;
import ro.telacad.entity.MapPatientsDoctor;

/**
 *
 * @author Buzy
 */
@Stateless
public class MapPatientsService {
    
    @EJB
    private MapPatientsDoctorDao mapPatientsDoctorDao;
    
    public List<MapPatientsDoctor> listPatientsByUserId(Integer idUser) {
        return mapPatientsDoctorDao.listPatientsByUserId(idUser);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public MapPatientsDoctor save(MapPatientsDoctor mpd) {
        return mapPatientsDoctorDao.save(mpd);
    }
    
    public MapPatientsDoctor getPatientsDoctorByCnp(Integer idUser, String cnp) {
        return mapPatientsDoctorDao.getPatientsDoctorByCnp(idUser, cnp);
    }
    
    public List<String> listCnpsNotUser(Integer userId, String likeCnp) {
        return mapPatientsDoctorDao.listCnpsNotUser(userId, likeCnp);
    }
}
