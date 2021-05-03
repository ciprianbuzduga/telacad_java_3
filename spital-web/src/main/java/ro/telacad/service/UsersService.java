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
import ro.telacad.dao.UsersDao;
import ro.telacad.entity.Users;

/**
 *
 * @author Buzy
 */
@Stateless
public class UsersService {

    @EJB
    private UsersDao usersDao;
    
    public Users getByUsername(String username) {
        return usersDao.getUserByUsernameAndPassword(username, null);
    }
    
    public Users getByUsernameAndPassword(String username, String password) {
        return usersDao.getUserByUsernameAndPassword(username, password);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Users save(Users user) {
        return usersDao.save(user);
    }
    
    public List<Users> list() {
        return usersDao.list();
    }
    
    public Users getUserById(Integer id) {
        return usersDao.getUserById(id);
    }
}
