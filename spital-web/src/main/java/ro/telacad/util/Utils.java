/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.telacad.util;

import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ro.telacad.entity.Appointments;
import ro.telacad.entity.Patients;
import ro.telacad.entity.Users;

/**
 *
 * @author Buzy
 */
public class Utils {
    
    public static BigInteger getBigInteger(String s) {
        BigInteger big = null;
        try {
            big = new BigInteger(s);
        } catch (Exception e) {
        }
        return big;
    }
    
    public static boolean isNumber(String n) {
        if(n == null) return false;
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(n);
        return matcher.matches();
    }
    
    public static boolean isEmpty(String s) {
        return s == null || s.trim().length() == 0;
    }
    
    public static int sortByPatientFirstLastName(Patients p1, Patients p2) {
        String fl1 = p1.getFirstName() + " " + p2.getLastName();
        String fl2 = p2.getFirstName() + " " + p2.getLastName();
        
        return fl1.compareTo(fl2);
    }
    
    public static int sortByUserFirstLastName(Users a1, Users a2) {
        String fl1 = a1.getFirstName() + " " + a2.getLastName();
        String fl2 = a2.getFirstName() + " " + a2.getLastName();
        
        return fl1.compareTo(fl2);
    }
}
