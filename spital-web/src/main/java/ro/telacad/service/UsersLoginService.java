/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.telacad.service;

import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import ro.telacad.dao.UsersLoginDao;
import ro.telacad.entity.Users;
import ro.telacad.entity.UsersLogin;

/**
 *
 * @author Buzy
 */
@Stateless
public class UsersLoginService {
    
    @EJB
    private UsersLoginDao usersLoginDao;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void logLoggin(Users user, boolean success) {
        UsersLogin usersLogin = new UsersLogin();
        usersLogin.setLoginDate(new Date());
        usersLogin.setLoginOk(success ? 1 : 0);
        usersLogin.setUser(user);
        
        usersLoginDao.save(usersLogin);
    }
}
