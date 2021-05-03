/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.telacad.interceptors;

import java.io.Serializable;
import java.util.Arrays;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import ro.telacad.entity.Users;
import ro.telacad.service.UsersLoginService;
import ro.telacad.service.UsersService;

/**
 *
 * @author Buzy
 */
@Stateful
public class LoginInterceptor implements Serializable {
    
    @EJB
    private UsersService usersService;
    @EJB
    private UsersLoginService usersLoginService;
    
    /**
     * Logheaza daca s-a facut un login cu succes pentru userul respectiv
     * @param context
     * @return
     * @throws Exception 
     */
    @AroundInvoke
    public Object loginIntercept(InvocationContext context) throws Exception{
        String methReturn = (String) context.proceed();
        Object[] params = context.getParameters();
        if(params != null) {
            String username = (String) params[0];
            Users user = usersService.getByUsername(username);
            if(user != null) {
                if(!methReturn.equals("login")) {
                    usersLoginService.logLoggin(user, true);
                } else {
                    usersLoginService.logLoggin(user, false);
                }
            } 
        }

        return methReturn;
    }
}
