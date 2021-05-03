/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.telacad.service;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ro.telacad.dao.TypeConsultationDao;
import ro.telacad.entity.TypeConsultation;

/**
 *
 * @author Buzy
 */
@Stateless
public class TypeConsultationService {
    
    @EJB
    private TypeConsultationDao typeConsultationDao;
    
     public List<TypeConsultation> listTypeConsultations() {
        return typeConsultationDao.listTypeConsultations();
    }
    
    public TypeConsultation getById(Integer id) {
        return typeConsultationDao.getById(id);
    }
}
