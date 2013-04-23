package br.edu.utfpr.cm.cronos.managedbean;

import br.edu.utfpr.cm.cronos.model.Subject;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Willyan Schultz Dworak
 */
@ManagedBean(name = "subjectBean")
@SessionScoped
public class SubjectBean {
    
    Subject subject;

    public String addSubject() {
        System.out.println("Adicionou a disciplina");
        return "cad_disciplinas";
//        FacesContext context = FacesContext.getCurrentInstance();
//
//        context.addMessage(null, new FacesMessage("Successful", "Hello"));
//        context.addMessage(null, new FacesMessage("Second Message", "Additional Info Here..."));
    }
}
