/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.cronos.listagens;

import br.edu.utfpr.cm.cronos.daos.DaoClasse;
import br.edu.utfpr.cm.cronos.model.ClassRoom;
import br.edu.utfpr.cm.cronos.model.Classe;
import br.edu.utfpr.cm.cronos.model.Teacher;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author paulo
 */
@ManagedBean(name = "listarTurmas")
@SessionScoped
public class ListarTurmas {
    private List<Classe> turmas;
    
    
    private Teacher teacher;
    private Classe selectedClasse;

    public ListarTurmas() {
        turmas = new DaoClasse().listar();
    }   

    public List<Classe> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Classe> turmas) {
        this.turmas = turmas;
    }

    public Classe getSelectedClasse() {
        return selectedClasse;
    }

    public void setSelectedClasse(Classe selectedClasse) {
        this.selectedClasse = selectedClasse;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
    
    @PostConstruct
    public void construct() {
        setSelectedClasse(new Classe());
    }
    
    public String editClasse() {
        DaoClasse dcr = new DaoClasse();
        dcr.persistir(this.selectedClasse);
        
        this.selectedClasse = new Classe();
        FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage(null, new FacesMessage("Successful", "Turma Editada!"));
        return "edit_classe";
//        context.addMessage(null, new FacesMessage("Second Message", "Additional Info Here..."));
    }
    
    public String deleteClasse() {
            System.out.println("deletando");
        DaoClasse dcr = new DaoClasse();
        dcr.remover(selectedClasse);
        
        this.selectedClasse = new Classe();
        FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage(null, new FacesMessage("Successful", "Turma Excluida!"));
        return "del_turmas";
//        context.addMessage(null, new FacesMessage("Second Message", "Additional Info Here..."));
    }

}
