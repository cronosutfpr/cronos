/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.cronos.listagens;

import br.edu.utfpr.cm.cronos.model.Book;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;


public class BookDataModel extends ListDataModel<Book> implements SelectableDataModel<Book> {

    public BookDataModel() {
    }

    public BookDataModel(List<Book> data) {
        super(data);
    }

    @Override
    public Book getRowData(String rowKey) {
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data  

        List<Book> books = (List<Book>) getWrappedData();
        for (Book classRoom : books) {
            if (String.valueOf(classRoom.getId()).equals(rowKey)) {
                return classRoom;
            }
        }
        
        return books.get(0);
    }

    @Override
    public Object getRowKey(Book classRoom) {
        return classRoom.getId();
    }
}

