/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.cronos.listagens;

import br.edu.utfpr.cm.cronos.daos.DaoSubject;
import br.edu.utfpr.cm.cronos.model.Subject;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author paulo
 */
@ManagedBean(name = "listarDisciplinas")
@SessionScoped
public class ListarDisciplinas {
    private List<Subject> disciplinas;
    private Subject selectedSubject;

    public Subject getSelectedSubject() {
        return selectedSubject;
    }

    public void setSelectedSubject(Subject selectedSubject) {
        this.selectedSubject = selectedSubject;
    }    
    
    public List<Subject> getDisciplinas() {
        disciplinas = new DaoSubject().listar();
        return disciplinas;
    }

    public void setDisciplinas(List<Subject> disciplinas) {
        this.disciplinas = disciplinas;
    }
    
    @PostConstruct
    public void construct() {
        setSelectedSubject(new Subject());
    }
    
    public String editSubject() {
        DaoSubject dcr = new DaoSubject();
        dcr.persistir(this.selectedSubject);
        
        this.selectedSubject = new Subject();
        FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage(null, new FacesMessage("Successful", "Disciplina Editada!"));
        return "edit_subject";
//        context.addMessage(null, new FacesMessage("Second Message", "Additional Info Here..."));
    }
    
    
}
