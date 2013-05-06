package br.edu.utfpr.cm.cronos.managedbean;

import br.edu.utfpr.cm.cronos.daos.DaoSubject;
import br.edu.utfpr.cm.cronos.model.Subject;
import javax.faces.application.FacesMessage;

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

    public void addSubject() {
        DaoSubject ds = new DaoSubject();
        ds.persistir(this.subject);

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Sucesso", subject.getName() + " foi inserido com sucesso."));

    }
}
