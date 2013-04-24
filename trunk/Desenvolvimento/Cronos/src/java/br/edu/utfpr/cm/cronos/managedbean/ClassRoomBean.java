/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.cronos.managedbean;

import br.edu.utfpr.cm.cronos.daos.DaoClassRoom;
import br.edu.utfpr.cm.cronos.model.ClassRoom;
import javax.faces.application.FacesMessage;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author AnaMaciel
 */
@ManagedBean(name = "classRoomBean")
@SessionScoped
public class ClassRoomBean {
    
    ClassRoom classroom;

    public ClassRoomBean() {
        classroom = new ClassRoom();
    }
    
    public ClassRoom getClassroom() {
        return classroom;
    }

    public void setClassroom(ClassRoom classroom) {
        this.classroom = classroom;
    }

    public String addClassRoom() {
        DaoClassRoom dcr = new DaoClassRoom();
        dcr.persistir(this.classroom);
        
        this.classroom = new ClassRoom();
        FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage(null, new FacesMessage("Successful", "Gravado"));
        return "cad_salas";
//        context.addMessage(null, new FacesMessage("Second Message", "Additional Info Here..."));
    }
      public String getReservavel(){
         System.out.println("BOKOKOJOJOJ" +this.classroom.isBookable());
        if(classroom.isBookable()){
            return "Sim";
        }
        return "Não";   
    }
}
