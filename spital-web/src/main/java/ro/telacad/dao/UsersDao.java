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
import ro.telacad.entity.Role_;
import ro.telacad.entity.Users;
import ro.telacad.entity.Users_;

/**
 *
 * @author Buzy
 */
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Stateless
public class UsersDao extends BaseDao<Users> {
    @PersistenceContext(unitName = "ro.telacad_spital-web_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersDao() {
        super(Users.class);
    }
    
    public Users getUserByUsernameAndPassword(String username, String password) {
        JpaCriteria<Users> criteria = getCriteria();
        criteria.addEqual(Users_.userName, username);
        
        if(password != null) {
            criteria.addEqual(Users_.password, password);
        }
        
        return criteria.get();
    }
    
     public Users getUserById(Integer id) {
        JpaCriteria<Users> criteria = getCriteria();
        criteria.addEqual(Users_.id, id);
 
        return criteria.get();
    }
    
    public List<Users> list() {
        JpaCriteria<Users> crit = getCriteria();
        crit.addNotEqual(Users_.idRole, Role_.id, RoleDao.ROLE_ADMIN);
        
        return crit.listOrderBy(Users_.firstName, true);
    }
}
