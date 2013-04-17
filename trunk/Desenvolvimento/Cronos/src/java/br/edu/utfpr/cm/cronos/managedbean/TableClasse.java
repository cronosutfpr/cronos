/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.cronos.managedbean;

import br.edu.utfpr.cm.cronos.daos.DaoClasse;
import br.edu.utfpr.cm.cronos.daos.DaoGenerics;
import br.edu.utfpr.cm.cronos.model.Classe;
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
public class TableClasse {

    private List<Classe> classes;

    public TableClasse() {
        classes = new ArrayList<Classe>();
        buscarClasse();
    }

    private void buscarClasse() {
        DaoGenerics<Classe> daoClasse = new DaoClasse();
        classes = daoClasse.listar();
    }

    public List<Classe> getClasses() {
        return classes;
    }
}
