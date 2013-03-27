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
public class Lesson {

    private String id;
    private Subject subject;
    private Classe _class;
    private Period period;
    private Teacher teacher;
    private List<ClassRoom> classrooms;
    private Group group;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

   
    public Classe get_Class() {
        return _class;
    }

    public void setClass(Classe _class) {
        this._class = _class;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<ClassRoom> getClassrooms() {
        return classrooms;
    }

    public void setClassrooms(List<ClassRoom> classrooms) {
        this.classrooms = classrooms;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
    
}
