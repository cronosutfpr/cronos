/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.cronos.managedbean;

import br.edu.utfpr.cm.cronos.daos.DaoClassRoom;
import br.edu.utfpr.cm.cronos.daos.DaoGenerics;
import br.edu.utfpr.cm.cronos.model.ClassRoom;
import br.edu.utfpr.cm.cronos.pagesmanager.PageManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author junior
 */
@ManagedBean
public class TableClassRoom implements Serializable {

    private List<ClassRoom> classRooms;
    public static ClassRoom selectedClassRoom;
    private ClassRoomDataModel classRoomDataModel;

    public TableClassRoom() {
        classRooms = new ArrayList<ClassRoom>();
        buscarClassRoom();
        classRoomDataModel = new ClassRoomDataModel(classRooms);
    }

    private void buscarClassRoom() {
        DaoGenerics<ClassRoom> daoClassRoom = new DaoClassRoom();
        classRooms = daoClassRoom.listar();
    }

    public List<ClassRoom> getClassRooms() {
        return classRooms;
    }

    public ClassRoom getSelectedClassRoom() {
        return selectedClassRoom;
    }

    public void setSelectedClassRoom(ClassRoom selectedClassRoom) {
        this.selectedClassRoom = selectedClassRoom;
    }

    public ClassRoomDataModel getClassRoomDataModel() {
        return classRoomDataModel;
    }

    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Sala selecionada", String.valueOf(((ClassRoom) event.getObject()).getId()));

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Sala não selecionada", String.valueOf(((ClassRoom) event.getObject()).getId()));

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
