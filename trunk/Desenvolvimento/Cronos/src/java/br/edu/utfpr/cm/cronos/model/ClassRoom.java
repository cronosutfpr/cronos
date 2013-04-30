/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.cronos.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement(name = "classroom")
@XmlAccessorType(XmlAccessType.FIELD)
public class ClassRoom implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @XmlAttribute(name = "id")
    private String idxml;
    @XmlAttribute(name = "name")
    private String name;
    @XmlAttribute(name = "short")
    private String _short;
    @XmlAttribute(name = "capacity")
    private String capacity;
    private String type;
    private String building;
    @ManyToOne(fetch = FetchType.EAGER)
    private User owner;
    private String status;
    private boolean bookable;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdxml() {
        return idxml;
    }

    public void setIdxml(String idxml) {
        this.idxml = idxml;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShort() {
        return _short;
    }

    public void setShort(String _short) {
        this._short = _short;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBookable() {
        if(this.bookable == true) {
            return "Sim";
        }
        return "Não";
    }

    public void setBookable(boolean bookable) {
        this.bookable = bookable;
    }

    @Override
    public String toString() {
        return "ClassRoom{" + "name=" + name + ", type=" + type + '}';
    }
    
    
}
