/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.telacad.dao;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ro.telacad.entity.Role;
import ro.telacad.entity.Role_;

/**
 *
 * @author Buzy
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class RoleDao extends BaseDao<Role> {
    
    public static final Integer ROLE_ADMIN = 1;
    public static final Integer ROLE_MANAGER = 2;
    public static final Integer ROLE_DOCTOR = 3;
    
    @PersistenceContext(unitName = "ro.telacad_spital-web_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RoleDao() {
        super(Role.class);
    }
    
    public Role getAdminRole() {
        return getCriteria().getByProp(Role_.id, ROLE_ADMIN);
    }
    
    public Role getManagerRole() {
        return getCriteria().getByProp(Role_.id, ROLE_MANAGER);
    }
    
    public Role getDoctorRole() {
        return getCriteria().getByProp(Role_.id, ROLE_DOCTOR);
    }
}
