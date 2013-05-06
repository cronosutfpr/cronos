/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.cronos.listagens;

import br.edu.utfpr.cm.cronos.daos.DaoSubject;
import br.edu.utfpr.cm.cronos.daos.DaoTeacher;
import br.edu.utfpr.cm.cronos.model.Subject;
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
public class ListarDisciplinas {
    private List<Subject> disciplinas;
    private String novoNome;
    private String novoShort;

    public String getNovoNome() {
        return novoNome;
    }

    public void setNovoNome(String novoNome) {
        this.novoNome = novoNome;
    }

    public String getNovoShort() {
        return novoShort;
    }

    public void setNovoShort(String novoShort) {
        this.novoShort = novoShort;
    }
    
    
    public List<Subject> getDisciplinas() {
        disciplinas = new DaoSubject().listar();
        return disciplinas;
    }

    public void setDisciplinas(List<Subject> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public void onEdit(RowEditEvent event) {
        //(Teacher) event.getObject()).getGender()
        Subject subject = (Subject) event.getObject();
        
        preencherComDadosAtualizados(subject);
        
        new DaoSubject().persistir(subject);
        
        
        FacesMessage msg = new FacesMessage("Disciplina editado", "Nome: "+subject.getName());



        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edição cancelada!", ((Subject) event.getObject()).getName());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    private void preencherComDadosAtualizados(Subject subject) {
        if(!novoNome.isEmpty()){
            subject.setName(novoNome);
        }
        if(!novoShort.isEmpty()){
            subject.setShort(novoShort);
        }
    }
    
    
}
