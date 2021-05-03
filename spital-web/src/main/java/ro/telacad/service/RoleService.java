/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.telacad.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import ro.telacad.dao.RoleDao;
import ro.telacad.entity.Role;

/**
 *
 * @author Buzy
 */
@Stateless
public class RoleService {

    @EJB
    private RoleDao roleFacade;
    
    public Role getAdminRole() {
        return roleFacade.getAdminRole();
    }
    
    public Role getManagerRole() {
        return roleFacade.getManagerRole();
    }
    
    public Role getDoctorRole() {
        return roleFacade.getDoctorRole();
    }
    
}
