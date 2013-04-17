/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.cronos.managedbean;

import br.edu.utfpr.cm.cronos.daos.DaoClassRoom;
import br.edu.utfpr.cm.cronos.daos.DaoClasse;
import br.edu.utfpr.cm.cronos.daos.DaoGenerics;
import br.edu.utfpr.cm.cronos.model.ClassRoom;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author junior
 */
@ManagedBean
@SessionScoped
public class TableClassRoom {

    private List<ClassRoom> classRooms;

    public TableClassRoom() {
        classRooms = new ArrayList<ClassRoom>();
        buscarClassRoom();
    }

    private void buscarClassRoom() {
        DaoGenerics<ClassRoom> daoClassRoom = new DaoClassRoom();
        classRooms = daoClassRoom.listar();
    }

    public List<ClassRoom> getClassRooms() {
        return classRooms;
    }
}