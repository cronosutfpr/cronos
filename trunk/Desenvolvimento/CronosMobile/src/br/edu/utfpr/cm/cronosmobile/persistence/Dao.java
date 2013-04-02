package br.edu.utfpr.cm.cronosmobile.persistence;

import java.util.ArrayList;

/**
 * 
 * @author Ana Claudia Maciel
 * @author Willyan Schultz Dworak
 * 
 */
public interface Dao<T> {

    public int salvar(T obj);
    
    public int inserir(T obj);

    public int excluir(int id);

    public int atualizar(T obj);

    public ArrayList<T> listarTodos();

    public T editar(int id);

}
