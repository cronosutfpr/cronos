/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.cronos.controller;

import br.edu.utfpr.cm.cronos.daos.DaoPeriod;
import br.edu.utfpr.cm.cronos.daos.DaoUser;
import br.edu.utfpr.cm.cronos.model.Period;
import br.edu.utfpr.cm.cronos.model.User;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author junior
 */
@FacesConverter(value = "periodConverter")
public class PeriodConverter implements Converter {

    public static DaoPeriod daoPeriod = new DaoPeriod();
    public static List<Period> periods;


    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value.trim().equals("")) {
            return null;
        } else {
            try {
                int number = Integer.parseInt(value);
                return daoPeriod.obterPorId(number);
            } catch (NumberFormatException exception) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid player"));
            }
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return "";
        } else {
            return String.valueOf(((Period) value).getId());
        }
    }
}
