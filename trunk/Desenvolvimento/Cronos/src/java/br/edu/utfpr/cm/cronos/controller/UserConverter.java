/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.cronos.controller;

import br.edu.utfpr.cm.cronos.daos.DaoUser;
import br.edu.utfpr.cm.cronos.model.User;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

/**
 *
 * @author AnaMaciel
 */
public class UserConverter implements Converter {
    public static DaoUser daoUser = new DaoUser();
    
    public static List<User> users;
    
    static {
        users = daoUser.listar();
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value.trim().equals("")) {
            return null;
        } else {
            try {
                int number = Integer.parseInt(value);

                for (User u : users) {  
                    if (u.getId()== number) {  
                        return u;  
                    }  
                } 

            } catch (NumberFormatException exception) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid player"));
            }
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.equals("")) {  
            return "";  
        } else {  
            return String.valueOf(((User) value).getId());  
        } 
    }
}
