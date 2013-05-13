/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.cronos.daos;

import br.edu.utfpr.cm.cronos.conexao.TransactionManager;
import br.edu.utfpr.cm.cronos.model.Book;
import br.edu.utfpr.cm.cronos.model.ClassRoom;
import br.edu.utfpr.cm.cronos.util.Utils;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Erick
 */
public class DaoBook extends DaoGenerics<Book> {

    protected Session session = TransactionManager.getCurrentSession();

    public DaoBook() {
        super.alvo = Book.class;
    }

    public List<Book> obterPorClassRoom(Long id) {
        List<Book> books;
        Query query = session.createQuery("FROM Book b WHERE b.classroom.id =" + id);
        books = query.list();

        return books;
    }

    public boolean validarReserva(Book book) {
        Query query = session.createQuery("FROM Book b where b.classroom.id = " + book.getClassroom().getId() + " and b.startdate = '" + Utils.formatDate(book.getStartdate()) + "' and  b.endDate = '" + Utils.formatDate(book.getEndDate()) + "' and  b.period.id=" + book.getPeriod().getId());
        System.out.println("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE "+"FROM Book b where b.classroom.id = " + book.getClassroom().getId() + " and b.startdate = '" + Utils.formatDatePtBR(book.getStartdate()) + "' and  b.endDate = '" + Utils.formatDatePtBR(book.getEndDate()) + "' and  b.period.id=" + book.getPeriod().getId());
        return query.list().isEmpty();
    }
}
