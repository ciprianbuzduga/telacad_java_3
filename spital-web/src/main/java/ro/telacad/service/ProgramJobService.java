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
import ro.telacad.dao.ProgramJobDao;
import ro.telacad.entity.ProgramJob;

/**
 *
 * @author Buzy
 */
@Stateless
public class ProgramJobService {
    
    @EJB
    private ProgramJobDao programJobDao;
    
    public List<ProgramJob> list() {
        return programJobDao.list();
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public ProgramJob save(ProgramJob programJob) {
        return programJobDao.save(programJob);
    }
}
