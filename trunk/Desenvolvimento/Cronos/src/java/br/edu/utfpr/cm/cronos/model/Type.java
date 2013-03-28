/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.cronos.model;

import java.io.Serializable;
import javax.persistence.Entity;

@Entity
public enum Type implements Serializable{
    ;
    public static final String SALA_TEORICA = "SALA_TEORICA";
    public static final String LABORATORIO = "LABORATORIO";
    public static final String GINASIO = "GINASIO";
    public static final String ANFITEATRO = "ANFITEATRO";
}
