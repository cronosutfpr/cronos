/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.cronos.conexao;

/**
 *
 * @author Erick
 */
public class Teste {
    
    public static void main(String[] args) {
        HibernateConfiguration.setBase("aula");
        HibernateConfiguration.setHost("127.0.0.1:3306");
        HibernateConfiguration.setPass("root");
        HibernateConfiguration.setUser("root");
        HibernateConfiguration.criarSchema();
    }
    
}
