/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.telacad.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ro.telacad.entity.MapPatientsDoctor;
import ro.telacad.entity.MapPatientsDoctor_;
import ro.telacad.entity.Patients_;
import ro.telacad.entity.Users_;

/**
 *
 * @author Buzy
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class MapPatientsDoctorDao extends BaseDao<MapPatientsDoctor> {
    @PersistenceContext(unitName = "ro.telacad_spital-web_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MapPatientsDoctorDao() {
        super(MapPatientsDoctor.class);
    }
    
    public List<MapPatientsDoctor> listPatientsByUserId(Integer idUser) {
        JpaCriteria<MapPatientsDoctor> crit = getCriteria();
        crit.addEqual(MapPatientsDoctor_.idUser, Users_.id, idUser);
        return crit.listOrderBy(MapPatientsDoctor_.idPatient, Patients_.firstName, true);
    }
    
    public MapPatientsDoctor getPatientsDoctorByCnp(Integer idUser, String cnp) {
        if(idUser == null || cnp == null) return null;
        
        JpaCriteria<MapPatientsDoctor> crit = getCriteria();
        crit.addEqual(MapPatientsDoctor_.idPatient, Patients_.cnp, cnp);
        crit.addEqual(MapPatientsDoctor_.idUser, Users_.id, idUser);
        
        return getCriteria().get();
    }
    
    public List<String> listCnpsNotUser(Integer userId, String likeCnp) {
        JpaCriteria<MapPatientsDoctor> crit = getCriteria(String.class);
        
        crit.addNotEqual(MapPatientsDoctor_.idUser, Users_.id, userId);
        crit.addLike(MapPatientsDoctor_.idPatient, Patients_.cnp, likeCnp);
        
        return crit.selectDisctinct(MapPatientsDoctor_.idPatient, Patients_.cnp);
    }
}
