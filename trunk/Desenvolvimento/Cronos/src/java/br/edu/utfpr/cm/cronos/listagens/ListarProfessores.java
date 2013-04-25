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
    
    public List<Teacher> getProfessores(){        
        DaoTeacher dT = new DaoTeacher();
        listaProfessores = dT.listar();
        return listaProfessores;        
    }
    
    public void onEdit(RowEditEvent event) {  
        //(Teacher) event.getObject()).getGender()
        System.out.println(event.getObject());
        FacesMessage msg = new FacesMessage("Professor editado", String.valueOf(((Teacher) event.getObject()).getGender()));  
        
        
  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
      
    public void onCancel(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Edição cancelada!", ((Teacher) event.getObject()).getGender());  
  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
}
