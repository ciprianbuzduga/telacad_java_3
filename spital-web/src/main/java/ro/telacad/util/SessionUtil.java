/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.telacad.util;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
/**
 *
 * @author Buzy
 */
public class SessionUtil {
    
    public static final String USER_NAME_KEY = "username";
    public static final String USER_ID_KEY = "userId";
    
    public static HttpSession getSession() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession httpSession = (HttpSession) externalContext.getSession(false);
        return httpSession;
    }
 
    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
    }
 
    public static String getUserName() {
        return (String) getSessionAttribute(USER_NAME_KEY);
    }
 
    public static Integer getUserId() {
       return (Integer) getSessionAttribute(USER_ID_KEY);
    }
    
    public static void setUserName(String username) {
        setSessionAttribute(USER_NAME_KEY, username);
    }
    
    public static void setUserId(Integer userId) {
        setSessionAttribute(USER_ID_KEY, userId);
    }
    
    private static Object getSessionAttribute(String key) {
        HttpSession session = getSession();
        if (session != null) {
            return session.getAttribute(key);
        }
        return null;
    }
    
    private static void setSessionAttribute(String key, Object param) {
        HttpSession session = getSession();
        session.setAttribute(key, param);
    }
    
    public static void invalidateSession() {
        HttpSession session = getSession();
        session.invalidate();
    }
}
