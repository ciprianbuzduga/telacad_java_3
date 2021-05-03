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
import ro.telacad.entity.Patients;
import ro.telacad.entity.Patients_;

/**
 *
 * @author Buzy
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class PatientsDao extends BaseDao<Patients> {
    @PersistenceContext(unitName = "ro.telacad_spital-web_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PatientsDao() {
        super(Patients.class);
    }
    
    public Patients getByCnp(String cnp) {
        return getCriteria().getByProp(Patients_.cnp, cnp);
    }
    
    public Patients getById(Integer id) {
        return getCriteria().getByProp(Patients_.id, id);
    }
    
    public List<Patients> list() {
        return getCriteria().listOrderBy(Patients_.firstName, true);
    }
}
