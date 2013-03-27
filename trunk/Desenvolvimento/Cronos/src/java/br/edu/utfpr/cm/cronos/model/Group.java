/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.cronos.model;

/**
 *
 * @author junior
 */
public class Groups {
    private String id;
	 
	private String name;
	 
	private Classes _class;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Classes _getClass() {
        return _class;
    }

    public void setClass(Classes _class) {
        this._class = _class;
    }
        
}
