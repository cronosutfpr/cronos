/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.cronos.listagens;

import br.edu.utfpr.cm.cronos.daos.DaoTeacher;
import br.edu.utfpr.cm.cronos.model.Teacher;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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
}
