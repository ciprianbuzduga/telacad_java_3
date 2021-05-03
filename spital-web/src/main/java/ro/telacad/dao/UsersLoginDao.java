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
import ro.telacad.entity.UsersLogin;

/**
 *
 * @author Buzy
 */
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Stateless
public class UsersLoginDao extends BaseDao<UsersLogin> {
    
    @PersistenceContext(unitName = "ro.telacad_spital-web_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersLoginDao() {
        super(UsersLogin.class);
    }
}
