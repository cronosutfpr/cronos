/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.cronos.readerXML;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "timetable")
@XmlAccessorType(XmlAccessType.FIELD)
public class Timetable {

    @XmlElement(name = "periods")
    private Periods periods;
    @XmlElement(name = "subjects")
    private Subjects subjects;
    @XmlElement(name = "lessons")
    private Lessons lessons;
    @XmlElement(name = "classes")
    private Classes classes;
    @XmlElement(name = "classrooms")
    private ClassRooms classRooms;
    @XmlElement(name = "groups")
    private Groups groups;
    @XmlElement(name = "teachers")
    private Teachers teachers;

    public Periods getPeriods() {
        return periods;
    }

    public void setPeriods(Periods periods) {
        this.periods = periods;
    }

    public Subjects getSubjects() {
        return subjects;
    }

    public void setSubjects(Subjects subjects) {
        this.subjects = subjects;
    }

    public Teachers getTeachers() {
        return teachers;
    }

    public void setTeachers(Teachers teachers) {
        this.teachers = teachers;
    }

    public Lessons getLessons() {
        return lessons;
    }

    public void setLessons(Lessons lessons) {
        this.lessons = lessons;
    }

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    public ClassRooms getClassRooms() {
        return classRooms;
    }

    public void setClassRooms(ClassRooms classRooms) {
        this.classRooms = classRooms;
    }

    public Groups getGroups() {
        return groups;
    }

    public void setGroups(Groups groups) {
        this.groups = groups;
    }
    
}
