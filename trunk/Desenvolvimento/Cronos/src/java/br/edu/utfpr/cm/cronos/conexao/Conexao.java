/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.cronos.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ehrickwilliam
 */
public abstract class Conexao {
    private static Connection conexaoJasper = null;
    private static Connection conexao = null;
    private static com.mysql.jdbc.Driver mysqlDriver;

    /**
     * Estabelece uma conexão a base de dados
     * @return A conexão estabelecida
     */
    public static Connection getConnection() throws SQLException {
        String conecaoMysql = "jdbc:mysql://"+HibernateConfiguration.getHost()+"/?user=" + HibernateConfiguration.getUser() +
                "&password=" + HibernateConfiguration.getPass();
        if (conexao == null) {
            mysqlDriver = new com.mysql.jdbc.Driver();
            conexao = DriverManager.getConnection(conecaoMysql);
        }
        return conexao;
    }

    public static Connection getConnectionJasper() throws SQLException {
        String conecaoMysql = "jdbc:mysql://"+HibernateConfiguration.getHost()+"/" + HibernateConfiguration.getBase()
                + "?user=" + HibernateConfiguration.getUser() + "&password=" + HibernateConfiguration.getPass();
        if (conexaoJasper == null) {
            mysqlDriver = new com.mysql.jdbc.Driver();
            conexaoJasper = DriverManager.getConnection(conecaoMysql);
        }
        return conexaoJasper;
    }

    public static ResultSet executeSQL(String sql) throws SQLException {
        Statement stm = getConnectionJasper().createStatement();
        return stm.executeQuery(sql);
    }
}
