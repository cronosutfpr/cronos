/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.cronos.tests;

import br.edu.utfpr.cm.cronos.model.Period;
import java.io.File;
import java.io.StringReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author junior
 */
public class XMLtoObject {

    public static void main(String[] args) {


        Periods period = (Periods) unmarshal(Periods.class, new String("/home/junior/Desktop/teste.xml"));


        for (Period p : period.getPeriods()) {
            System.out.println("Nome do periodo: "+p.getShort());
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
