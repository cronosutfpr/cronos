/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.cronos.managedbean;

import br.edu.utfpr.cm.cronos.model.ClassRoom;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author AnaMaciel
 */
@ManagedBean(name = "classRoomBean")
@SessionScoped
public class ClassRoomBean {

    ClassRoom classroom;

    public ClassRoomBean() {
    }

    public void addClassRoom(ActionEvent actionEvent) {
        System.out.println("estou aqui");
        FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage(null, new FacesMessage("Successful", "Hello"));
        context.addMessage(null, new FacesMessage("Second Message", "Additional Info Here..."));
    }
}
