/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.telacad.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Buzy
 */
public class FacesMessageUtil {
    
    public static void addMessageInfo(String message) {
        createMessage(FacesMessage.SEVERITY_INFO, message, "");
    }
    
    public static void addMessageInfo(String message, String details) {
        createMessage(FacesMessage.SEVERITY_INFO, message, details);
    }
    
    public static void addMessageWarn(String message) {
        createMessage(FacesMessage.SEVERITY_WARN, message, "");
    }
    
    public static void addMessageWarn(String message, String details) {
        createMessage(FacesMessage.SEVERITY_WARN, message, details);
    }
    
    public static void addMessageError(String message) {
        createMessage(FacesMessage.SEVERITY_ERROR, message, "");
    }
    
    public static void addMessageError(String message, String details) {
        createMessage(FacesMessage.SEVERITY_ERROR, message, details);
    }
    
    public static void addMessageSaveError() {
        createMessage(FacesMessage.SEVERITY_ERROR, "Salvare nereusita!", "Va rugam sa contactati administratorul!");
    }
    
    public static void addMessageSaveError(String details) {
        createMessage(FacesMessage.SEVERITY_ERROR, "Salvare nereusita!", details);
    }
    
     public static void addMessageSaveOk() {
        addMessageInfo("Salvare reusita!");
    }
    
    private static void createMessage(FacesMessage.Severity severity, String message, String details) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(severity, message,  details));
    }
}
