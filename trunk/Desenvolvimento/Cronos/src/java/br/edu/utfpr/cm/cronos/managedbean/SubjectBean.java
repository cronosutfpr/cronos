package br.edu.utfpr.cm.cronos.managedbean;

import br.edu.utfpr.cm.cronos.daos.DaoSubject;
import br.edu.utfpr.cm.cronos.model.Subject;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Willyan Schultz Dworak
 */
@ManagedBean(name = "subjectBean")
@SessionScoped
public class SubjectBean {

    private Subject subject;

    public SubjectBean() {
        this.subject = new Subject();
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
    
    public String addSubject() {
        FacesContext context = FacesContext.getCurrentInstance();
        DaoSubject ds = new DaoSubject();
        ds.persistir(this.subject);
        
        System.out.println("Adicionou a disciplina");
        return "cad_disciplinas";
    }
}
