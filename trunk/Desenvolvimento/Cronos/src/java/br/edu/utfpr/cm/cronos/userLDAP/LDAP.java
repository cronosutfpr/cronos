/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.cronos.userLDAP;

import br.edu.utfpr.cm.saa.entidades.Usuario;
import br.edu.utfpr.cm.saa.security.LDAPManager;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;

/**
 *
 * @author Paulo Azevedo
 */
public class LDAP {

    private static LDAPManager ldapManager;

    private LDAP() {
    }

    private static LDAPManager getLDAPManager() {
        if (ldapManager == null) {
            ldapManager = new br.edu.utfpr.cm.saa.security.LDAPManager("192.168.1.4:389", "ou=todos,dc=utfpr,dc=edu,dc=br");
        }
        return ldapManager;
    }

    public static void connect() {
        try {
            getLDAPManager().connect();
        } catch (LDAPException ex) {
            System.out.println(ex);
        }
    }

    public static void disconnect() {
        try {
            getLDAPManager().disconnect();
        } catch (LDAPException ex) {
            System.out.println(ex);
        }

    }

    public static boolean existe(String usuario) {
        try {
            connect();
            return getLDAPManager().search("(uid=" + usuario + ")", null);
        } catch (LDAPException ex) {
            System.out.println(ex);
            return false;
        }finally{
            disconnect();
        }
    }

    public static Usuario buscarUsuario(String usuario) {
        connect();
        if (existe(usuario)) {
            try {
                return getLDAPManager().search(usuario);
            } catch (LDAPException ex) {
                System.out.println(ex);
                return null;
            } finally {
                disconnect();
            }
        }
        disconnect();
        return null;
    }

    public static boolean autenticacao(String username, String password) {
        connect();
        if (existe(username)) {
            // obtendo  o componente
            LDAPEntry entry = getLDAPManager().nextEntry();
            // pegando o dn do usuario para efetuar o login
            if (getLDAPManager().login(entry.getDN(), password)) {
                disconnect();
                return true;
            }
            disconnect();
            return false;
        }
        disconnect();
        return false;
    }
}
