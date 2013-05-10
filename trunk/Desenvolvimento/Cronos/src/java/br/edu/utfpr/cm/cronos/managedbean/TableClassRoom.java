/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.cronos.managedbean;

import br.edu.utfpr.cm.cronos.daos.DaoBook;
import br.edu.utfpr.cm.cronos.daos.DaoClassRoom;
import br.edu.utfpr.cm.cronos.daos.DaoGenerics;
import br.edu.utfpr.cm.cronos.model.Book;
import br.edu.utfpr.cm.cronos.model.ClassRoom;
import br.edu.utfpr.cm.cronos.pagesmanager.PageManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Scope;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;

/**
 *
 * @author junior
 */
@ManagedBean
@SessionScoped
public class TableClassRoom implements Serializable {

    private List<ClassRoom> classRooms;
    public static ClassRoom selectedClassRoom;
    private ClassRoomDataModel classRoomDataModel;
    private String descricao = "";
    private String tipo = "";
    private String abreviatura = "";
    private String capacidade = "";
    private String reservavel = "0";
    private DaoClassRoom daoClassRoom;

    public TableClassRoom() {
        classRooms = new ArrayList<ClassRoom>();
        buscarClassRoom();
        classRoomDataModel = new ClassRoomDataModel(classRooms);
    }

    private void buscarClassRoom() {
        daoClassRoom = new DaoClassRoom();
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

        selectedClassRoom = (ClassRoom) event.getObject();
        BookBean.eventModel = new DefaultScheduleModel();
        List<Book> books = new DaoBook().obterPorClassRoom(TableClassRoom.selectedClassRoom.getId());
        for (Book book : books) {
            book.getStartdate().setTime(10000);
            BookBean.eventModel.addEvent(new DefaultScheduleEvent(book.getPeriod().getStarttime() + " - " + book.getPeriod().getEndtime(), book.getStartdate(), book.getEndDate()));
        }
    }

    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Sala não selecionada", String.valueOf(((ClassRoom) event.getObject()).getId()));

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void searchBooks() {
        classRooms.clear();

        System.out.println("DADOS: " + descricao + "-" + tipo + "-" + abreviatura + "-" + capacidade + "-" + reservavel);
        classRooms = daoClassRoom.ObterPorFiltro(descricao, tipo, abreviatura, capacidade, reservavel);
        clearParam();
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public String getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(String capacidade) {
        this.capacidade = capacidade;
    }

    public String getReservavel() {
        return reservavel;
    }

    public void setReservavel(String reservavel) {
        this.reservavel = reservavel;
    }

    private void clearParam() {
        descricao = "";
        abreviatura = "";
        capacidade = "";
        reservavel = "0";
        tipo = "";
    }
}
