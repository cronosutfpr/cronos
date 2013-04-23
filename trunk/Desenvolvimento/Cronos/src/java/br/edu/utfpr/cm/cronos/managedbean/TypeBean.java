/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.cronos.managedbean;

import br.edu.utfpr.cm.cronos.model.Type;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author AnaMaciel
 */
@ManagedBean(name = "typeBean")
@SessionScoped
public class TypeBean {
    
    Type type;

    /**
     * Creates a new instance of TypeBean
     */
    public TypeBean() {
        
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
    
    public SelectItem[] getTiposSala() {
	SelectItem[] items = new SelectItem[Type.values().length];
	int i = 0;
	for(Type t: Type.values()) {
		items[i++] = new SelectItem(t, t.getNome());
	}
	return items;
}
    
}
