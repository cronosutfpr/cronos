/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.cronos.daos;



import br.edu.utfpr.cm.cronos.conexao.TransactionManager;
import br.edu.utfpr.cm.cronos.interfaces.Dao;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author ehrickwilliam
 */
public class DaoGenerics<T> implements Dao<T> {

    protected Session session =  TransactionManager.getCurrentSession();  
    protected Class alvo;

    @Override
    public void persistir(T o) {       
        session.saveOrUpdate(o);
        session.flush();
    }

    @Override
    public void remover(T o) {
        session.delete(o);
        session.flush();
    }

    @Override
    public T obterPorId(int id) {
        T objeto = null;
        if (id > 0) {
            Query select = session.createQuery("From "+alvo.getSimpleName()+" where id = " + id);
            objeto = (T) select.uniqueResult();
        }
        return objeto;
    }

    @Override
    public List<T> listar(String filtro) {
        List<T> lista = null;
        if (filtro != null) {
            Query query = session.createQuery("FROM "+alvo.getSimpleName()+" WHERE nome LIKE '%"+filtro+"%'");
            lista = query.list();
        }
        return lista;
    }
}
