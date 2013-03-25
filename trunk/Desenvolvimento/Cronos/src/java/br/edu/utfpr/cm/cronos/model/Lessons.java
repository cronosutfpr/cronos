/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.cronos.model;

import java.util.List;

/**
 *
 * @author junior
 */
public class Lessons {

    private String id;
    private Subjects subject;
    private Classes _class;
    private Periods period;
    private Teachers teacher;
    private List<ClassRooms> classrooms;
    private Groups group;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Subjects getSubject() {
        return subject;
    }

    public void setSubject(Subjects subject) {
        this.subject = subject;
    }

   
    public Classes get_Class() {
        return _class;
    }

    public void setClass(Classes _class) {
        this._class = _class;
    }

    public Periods getPeriod() {
        return period;
    }

    public void setPeriod(Periods period) {
        this.period = period;
    }

    public Teachers getTeacher() {
        return teacher;
    }

    public void setTeacher(Teachers teacher) {
        this.teacher = teacher;
    }

    public List<ClassRooms> getClassrooms() {
        return classrooms;
    }

    public void setClassrooms(List<ClassRooms> classrooms) {
        this.classrooms = classrooms;
    }

    public Groups getGroup() {
        return group;
    }

    public void setGroup(Groups group) {
        this.group = group;
    }
    
}
