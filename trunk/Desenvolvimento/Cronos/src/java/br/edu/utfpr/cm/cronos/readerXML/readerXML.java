/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.cronos.readerXML;

import br.edu.utfpr.cm.cronos.model.Period;
import br.edu.utfpr.cm.cronos.model.Subject;
import br.edu.utfpr.cm.cronos.model.Teacher;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author junior
 */
public class readerXML {
    public static void main(String[] args) {
        
       Timetable timetables =  (Timetable) unmarshal(Timetable.class, new String("/home/junior/Desktop/projeto.xml"));

        for (Period p : timetables.getPeriods().getPeriods()) {
            System.out.println("Nome do periodo: "+p.getShort());
        }
        for (Subject s :timetables.getSubjects().getSubjects()) {
            System.out.println("Materia: "+s.getName());
        }
        for (Teacher t : timetables.getTeachers().getTeachers()) {
            System.out.println("Professor: "+t.getName());
        }
    
    }
        public static Object unmarshal(Class clazz, String path) {
        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return unmarshaller.unmarshal(
                    new File(path));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
