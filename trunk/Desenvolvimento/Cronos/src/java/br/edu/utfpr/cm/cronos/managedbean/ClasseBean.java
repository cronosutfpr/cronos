/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.cronos.managedbean;

import br.edu.utfpr.cm.cronos.controller.TeacherConverter;
import br.edu.utfpr.cm.cronos.daos.DaoClasse;
import br.edu.utfpr.cm.cronos.daos.DaoTeacher;
import br.edu.utfpr.cm.cronos.model.Classe;
import br.edu.utfpr.cm.cronos.model.Teacher;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author a1028367
 */
@ManagedBean
@SessionScoped
public class ClasseBean {
    private Classe classe;
    
    private List<Teacher> teachers;

    public ClasseBean() {
        classe = new Classe();
        teachers = TeacherConverter.teachers;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }
    
    

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classes) {
        this.classe = classes;
    }

    public void cadastrarClasse() {
        try {
            new DaoClasse().persistir(classe);
            
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Sucesso", classe.getName() + " foi inserido com sucesso."));
            classe = new Classe();
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Erro", classe.getName() + " Erro no cadastro"));
  
        }
    }  
    
    public List<Teacher> completeTeacher(String value) {
        return new DaoTeacher().listar(value);
    }
    
}
