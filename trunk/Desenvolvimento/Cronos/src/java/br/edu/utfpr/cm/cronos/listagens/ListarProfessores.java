/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.cronos.listagens;

import br.edu.utfpr.cm.cronos.daos.DaoTeacher;
import br.edu.utfpr.cm.cronos.model.Teacher;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author paulo
 */
@ManagedBean
@SessionScoped
public class ListarProfessores {
    private List<Teacher> listaProfessores;
    private Teacher selectedTeacher;

    public List<Teacher> getListaProfessores() {
        return listaProfessores;
    }

    public void setListaProfessores(List<Teacher> listaProfessores) {
        this.listaProfessores = listaProfessores;
    }

    public Teacher getSelectedTeacher() {
        return selectedTeacher;
    }

    public void setSelectedTeacher(Teacher selectedTeacher) {
        this.selectedTeacher = selectedTeacher;
    }   
    

    public List<Teacher> getProfessores() {
        DaoTeacher dT = new DaoTeacher();
        listaProfessores = dT.listar();
        return listaProfessores;
    }
    
    public String editTeacher() {
        DaoTeacher dcr = new DaoTeacher();
        dcr.persistir(this.selectedTeacher);
        
        this.selectedTeacher = new Teacher();
        FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage(null, new FacesMessage("Successful", "Professor Editado!"));
        return "edit_teacher";
//        context.addMessage(null, new FacesMessage("Second Message", "Additional Info Here..."));
    }
    
    public String deleteTeacher() {
            System.out.println("deletando");
        DaoTeacher dcr = new DaoTeacher();
        dcr.remover(this.selectedTeacher);
        
        this.selectedTeacher = new Teacher();
        FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage(null, new FacesMessage("Successful", "Professor Excluido!"));
        return "del_teacher";
//        context.addMessage(null, new FacesMessage("Second Message", "Additional Info Here..."));
    }

}
