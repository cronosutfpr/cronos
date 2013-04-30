/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.cronos.listagens;

import br.edu.utfpr.cm.cronos.daos.DaoClassRoom;
import br.edu.utfpr.cm.cronos.model.ClassRoom;
import br.edu.utfpr.cm.cronos.model.User;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author AnaMaciel
 */
@ManagedBean(name = "listarSalas")
@SessionScoped
public class ListarSalas {
    private List<ClassRoom> listaSalas;
    private String name;
    private String _short;
    private String capacity;
    private String type;
    private String building;
    private User owner;
    private String status;
    private boolean bookable;

    public String getShort() {
        return _short;
    }

    public void setShort(String _short) {
        this._short = _short;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isBookable() {
        return bookable;
    }

    public void setBookable(boolean bookable) {
        this.bookable = bookable;
    }   

    public List<ClassRoom> getListaSalas() {
        return listaSalas;
    }

    public void setListaSalas(List<ClassRoom> listaSalas) {
        this.listaSalas = listaSalas;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    public List<ClassRoom> getSalas(){        
        DaoClassRoom dC = new DaoClassRoom();
        listaSalas = dC.listar();
        //System.out.println(listaSalas);
        return listaSalas;        
    }
    
    public void onEdit(RowEditEvent event) {  
        ClassRoom classroom = (ClassRoom) event.getObject();
//        if (!name.isEmpty()) {
//            classroom.setName(name);
//            this.name = "";
//        }
//        
//        new DaoClassRoom().persistir(classroom);
//        
//        
        FacesMessage msg = new FacesMessage("Professor editado", "teste");



        FacesContext.getCurrentInstance().addMessage(null, msg);
        
        //System.out.println("oi!");
    }  
      
    public void onCancel(RowEditEvent event) {  
//        FacesMessage msg = new FacesMessage("Edição cancelada!", ((ClassRoom) event.getObject()).getName());  
//  
//        FacesContext.getCurrentInstance().addMessage(null, msg);  
        
        ClassRoom classroom = (ClassRoom) event.getObject();
        
        new DaoClassRoom().persistir(classroom);
    }  
}
