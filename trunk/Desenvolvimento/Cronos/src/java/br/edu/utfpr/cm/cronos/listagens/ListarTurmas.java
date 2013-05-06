/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.cronos.listagens;

import br.edu.utfpr.cm.cronos.daos.DaoClasse;
import br.edu.utfpr.cm.cronos.model.Classe;
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
public class ListarTurmas {
    private List<Classe> turmas;
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
    
    
    public List<Classe> getTurmas() {
        turmas = new DaoClasse().listar();
        return turmas;
    }

    public void setTurma(List<Classe> turmas) {
        this.turmas = turmas;
    }

    public void onEdit(RowEditEvent event) {
        //(Teacher) event.getObject()).getGender()
        Classe classe = (Classe) event.getObject();
        
        preencherComDadosAtualizados(classe);
        
        new DaoClasse().persistir(classe);
        
        
        FacesMessage msg = new FacesMessage("Disciplina editado", "Nome: "+classe.getName());



        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edição cancelada!", ((Classe) event.getObject()).getName());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    private void preencherComDadosAtualizados(Classe classe) {
        if(!novoNome.isEmpty()){
            classe.setName(novoNome);
        }
        if(!novoShort.isEmpty()){
            classe.setShort(novoShort);
        }
    }
}
