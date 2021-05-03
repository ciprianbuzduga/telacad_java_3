/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.telacad.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import ro.telacad.util.Utils;

/**
 *
 * @author Buzy
 */
@FacesValidator("stringToNumberValidator")
public class StringToNumberValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String min = (String) component.getAttributes().get("min");
        String max = (String) component.getAttributes().get("max");
        
        String svalue = (String) value;
        boolean isNumber = Utils.isNumber(svalue);
        
        int slength = svalue.length();
        if(!isNumber || slength < Integer.parseInt(min) || slength > Integer.parseInt(max)) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Eroare de validare!", 
                    "Valoarea introdusa trebuie sa fie de tip numeric cu lungimea caracterelor cuprinsa intre "
            + min + " si " + max + " inclusiv!");
            throw new ValidatorException(fm);
        }
    }
}
