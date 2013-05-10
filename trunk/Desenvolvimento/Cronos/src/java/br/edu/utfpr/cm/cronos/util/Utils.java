/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.cronos.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author junior
 */
public class Utils {
    public static String formatDatePtBR(Date date){
         DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
         return df.format(date);
    }
}
