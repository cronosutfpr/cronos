/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.cronos.tests;

import br.edu.utfpr.cm.cronos.cadastros.CadastroProfessor;
import br.edu.utfpr.cm.cronos.conexao.*;
import br.edu.utfpr.cm.cronos.model.Teacher;

/**
 *
 * @author Erick
 */
public class Teste {
    
    public static void main(String[] args) {
       CadastroProfessor cadastro = new CadastroProfessor();
        Teacher teacher = cadastro.setTeacher("quadrado");
        System.out.println(teacher.getName());
    }
    
}
