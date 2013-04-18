/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.cronos.managedbean;

import br.edu.utfpr.cm.cronos.model.ClassRoom;
import java.io.Serializable;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;


public class ClassRoomDataModel extends ListDataModel<ClassRoom> implements SelectableDataModel<ClassRoom> {

    public ClassRoomDataModel() {
    }

    public ClassRoomDataModel(List<ClassRoom> data) {
        super(data);
    }

    @Override
    public ClassRoom getRowData(String rowKey) {
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data  

        List<ClassRoom> classRooms = (List<ClassRoom>) getWrappedData();

        System.out.println("KKKKKKKKKKKKKKKKKKKKKKK"+ rowKey);
        for (ClassRoom classRoom : classRooms) {
            if (String.valueOf(classRoom.getId()).equals(rowKey)) {
                return classRoom;
            }
        }
        
        return classRooms.get(0);
    }

    @Override
    public Object getRowKey(ClassRoom classRoom) {
        return classRoom.getId();
    }
}
