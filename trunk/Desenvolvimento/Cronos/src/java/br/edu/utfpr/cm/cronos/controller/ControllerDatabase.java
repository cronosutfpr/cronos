/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.cronos.controller;

import br.edu.utfpr.cm.cronos.conexao.TransactionManager;
import br.edu.utfpr.cm.cronos.daos.DaoClassRoom;
import br.edu.utfpr.cm.cronos.daos.DaoClasse;
import br.edu.utfpr.cm.cronos.daos.DaoGenerics;
import br.edu.utfpr.cm.cronos.daos.DaoGroup;
import br.edu.utfpr.cm.cronos.daos.DaoLesson;
import br.edu.utfpr.cm.cronos.daos.DaoPeriod;
import br.edu.utfpr.cm.cronos.daos.DaoSubject;
import br.edu.utfpr.cm.cronos.daos.DaoTeacher;
import br.edu.utfpr.cm.cronos.model.ClassRoom;
import br.edu.utfpr.cm.cronos.model.Classe;
import br.edu.utfpr.cm.cronos.model.Group;
import br.edu.utfpr.cm.cronos.model.Lesson;
import br.edu.utfpr.cm.cronos.model.Period;
import br.edu.utfpr.cm.cronos.model.Subject;
import br.edu.utfpr.cm.cronos.model.Teacher;
import br.edu.utfpr.cm.cronos.readerXML.Timetable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author junior
 */
public class ControllerDatabase {

    public static void saveAll(Timetable timetable) throws Exception {
        TransactionManager.beginTransaction();
        DaoGenerics<Period> daoPeriod = new DaoPeriod();
        for (Period period : timetable.getPeriods().getPeriods()) {
            daoPeriod.persistir(period);
        }
        DaoGenerics<ClassRoom> daoClassRoom = new DaoClassRoom();
        for (ClassRoom classRoom : timetable.getClassRooms().getClassrooms()) {
            daoClassRoom.persistir(classRoom);
        }
        DaoGenerics<Teacher> daoTeacher = new DaoTeacher();
        for (Teacher teacher : timetable.getTeachers().getTeachers()) {
            daoTeacher.persistir(teacher);
        }
        DaoGenerics<Classe> daoClasse = new DaoClasse();
        for (Classe classe : timetable.getClasses().getClasses()) {
            if (classe.getTeacherid() != null) {
                for (Teacher teacher : timetable.getTeachers().getTeachers()) {
                    if (classe.getTeacherid().equals(classe.getTeacherid())) {
                        classe.setTeacher(teacher);
                    }
                }
            }
            daoClasse.persistir(classe);
        }
        DaoGenerics<Subject> daoSubjects = new DaoSubject();
        for (Subject subject : timetable.getSubjects().getSubjects()) {
            daoSubjects.persistir(subject);
        }
        DaoGenerics<Group> daoGroup = new DaoGroup();
        for (Group group : timetable.getGroups().getGroups()) {
            if (group.getClassid() != null) {
                for (Classe classe : timetable.getClasses().getClasses()) {
                    if (group.getClassid().equals(classe.getIdxml())) {
                        group.setClasse(classe);
                    }
                }
            }
            daoGroup.persistir(group);
        }
        DaoGenerics<Lesson> daoLesson = new DaoLesson();
        for (Lesson lesson : timetable.getLessons().getLessons()) {
            daoLesson.persistir(lesson);
        }
        TransactionManager.commit();
    }

    public static void updateDatabase(Timetable timetable) {
    }
}
