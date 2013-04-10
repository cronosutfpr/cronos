/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.cronos.managedbean;

import br.edu.utfpr.cm.cronos.model.ClassRoom;
import br.edu.utfpr.cm.cronos.util.JSFUtil;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author AnaMaciel
 */
@ManagedBean(name = "classRoomBean")
@RequestScoped
public class ClassRoomBean {

    ClassRoom classroom;
    
    public ClassRoomBean() {
    }
    
    public void addClassRoom(){
       JSFUtil.addSuccessMessage("Ocorrência Adicionada", "Adicionada");
    }
}
