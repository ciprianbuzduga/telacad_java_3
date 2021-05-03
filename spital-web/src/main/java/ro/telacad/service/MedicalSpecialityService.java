/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.telacad.service;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ro.telacad.dao.MedicalSpecialityDao;
import ro.telacad.entity.MedicalSpeciality;

/**
 *
 * @author Buzy
 */
@Stateless
public class MedicalSpecialityService {
    
    @EJB
    private MedicalSpecialityDao medicalSpecialityFacade;
    
    public List<MedicalSpeciality> listSpeciality() {
        return medicalSpecialityFacade.listSpeciality();
    }
    
    public MedicalSpeciality getById(Integer id) {
        return medicalSpecialityFacade.getById(id);
    }
}
