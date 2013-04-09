/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.cronos.readerXML;

import br.edu.utfpr.cm.cronos.conexao.HibernateConfiguration;
import br.edu.utfpr.cm.cronos.controller.ControllerDatabase;
import br.edu.utfpr.cm.cronos.model.Group;
import br.edu.utfpr.cm.cronos.model.Period;
import br.edu.utfpr.cm.cronos.model.Subject;
import br.edu.utfpr.cm.cronos.model.Teacher;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.hibernate.Hibernate;

/**
 *
 * @author junior
 */
public class ReaderXML {

    public static void importXML(String path) {
        Timetable timetables = (Timetable) unmarshal(Timetable.class, path);
        try {
            ControllerDatabase.saveAll(timetables);
        } catch (Exception ex) {
            ex.printStackTrace();
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
