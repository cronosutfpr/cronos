package br.edu.utfpr.cm.cronos.tests;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import br.edu.utfpr.cm.cronos.conexao.HibernateConfiguration;
import br.edu.utfpr.cm.cronos.readerXML.ReaderXML;
/**
 *
 * @author junior
 */
public class TestReaderXML {

    public static void main(String[] args) {
        HibernateConfiguration.criarSchema();
        ReaderXML.importXML("D:/Meus Documentos/Faculdade 2012/Segundo Semestre/PI35A/cronos/trunk/Desenvolvimento/Cronos/data/projeto.xml");
    }
}
