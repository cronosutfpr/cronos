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
    private String novoSexo;
    private String novoEmail;
    private String novoNome;

    public String getNovoNome() {
        return novoNome;
    }

    public void setNovoNome(String novoNome) {
        this.novoNome = novoNome;
    }

    public String getNovoEmail() {
        return novoEmail;
    }

    public void setNovoEmail(String novoEmail) {
        this.novoEmail = novoEmail;
    }

    public String getNovoSexo() {
        return novoSexo;
    }

    public void setNovoSexo(String novoSexo) {
        this.novoSexo = novoSexo;
    }

    public List<Teacher> getProfessores() {
        DaoTeacher dT = new DaoTeacher();
        listaProfessores = dT.listar();
        return listaProfessores;
    }

    public void onEdit(RowEditEvent event) {
        //(Teacher) event.getObject()).getGender()
        Teacher teacher = (Teacher) event.getObject();
        
        preencherComDadosAtualizados(teacher);
        
        new DaoTeacher().persistir(teacher);
        
        
        FacesMessage msg = new FacesMessage("Professor editado", "Nome: "+teacher.getName() + " \nEmail: " + teacher.getEmail() +" \nSexo: " +teacher.getGender());



        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edição cancelada!", ((Teacher) event.getObject()).getGender());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    
    private void preencherComDadosAtualizados(Teacher teacher) {
        if (!novoSexo.isEmpty()) {
            teacher.setGender(novoSexo);
            this.novoSexo = "";
        }
        if (!novoEmail.isEmpty()) {
            teacher.setEmail(novoEmail);
            this.novoEmail = "";
        }
        if (!novoNome.isEmpty()) {
            teacher.setEmail(novoNome);
            this.novoNome= "";
        }
    }
}
