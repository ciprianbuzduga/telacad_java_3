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
import ro.telacad.entity.Appointments;
import ro.telacad.entity.Appointments_;
import ro.telacad.entity.Users_;

/**
 *
 * @author Buzy
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class AppointmentsDao extends BaseDao<Appointments> {
    @PersistenceContext(unitName = "ro.telacad_spital-web_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AppointmentsDao() {
        super(Appointments.class);
    }
    
    public List<Appointments> listAppointmentsByUserId(Integer userId) {
        if(userId == null) return null;
        JpaCriteria<Appointments> crit = getCriteria();
        crit.addEqual(Appointments_.user, Users_.id, userId);
        return crit.listOrderBy(Appointments_.day, false);
    }
    
    public Appointments getById(Integer id) {
        return getCriteria().getByProp(Appointments_.id, id);
    }
}
