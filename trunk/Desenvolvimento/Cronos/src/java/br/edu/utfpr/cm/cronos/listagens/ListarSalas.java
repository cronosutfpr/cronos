/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.cronos.listagens;

import br.edu.utfpr.cm.cronos.daos.DaoClassRoom;
import br.edu.utfpr.cm.cronos.model.ClassRoom;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author AnaMaciel
 */
@ManagedBean(name = "listarSalas")
@SessionScoped
public class ListarSalas {

    private List<ClassRoom> listaSalas;
    private ClassRoom selectedClassRoom;

    public List<ClassRoom> getListaSalas() {
        return listaSalas;
    }

    public void setListaSalas(List<ClassRoom> listaSalas) {
        this.listaSalas = listaSalas;
    }

    public List<ClassRoom> getSalas() {
        DaoClassRoom dC = new DaoClassRoom();
        listaSalas = dC.listar();
        //System.out.println(listaSalas);
        return listaSalas;
    }

    public ClassRoom getSelectedClassRoom() {
//        System.out.println("peguei a selecionada"+ this.selectedClassRoom.getName());
        return selectedClassRoom;
    }

    public void setSelectedClassRoom(ClassRoom selectedClassRoom) {
        this.selectedClassRoom = selectedClassRoom;
    }

    @PostConstruct
    public void construct() {
        setSelectedClassRoom(new ClassRoom());
    }
    
    public String editClassRoom() {
        DaoClassRoom dcr = new DaoClassRoom();
        dcr.persistir(this.selectedClassRoom);
        
        this.selectedClassRoom = new ClassRoom();
        FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage(null, new FacesMessage("Successful", "Sala Editada!"));
        return "cad_salas";
//        context.addMessage(null, new FacesMessage("Second Message", "Additional Info Here..."));
    }
}
