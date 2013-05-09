package br.edu.utfpr.cm.cronosmobile.ldap;

import java.util.logging.Level;
import java.util.logging.Logger;

import br.edu.utfpr.cm.saa.entidades.Usuario;
import br.edu.utfpr.cm.saa.security.LDAPManager;

import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;

/**
 * 
 * @author Paulo Azevedo
 */
public class LoginLDAP {

	public Usuario logarNoLDAP(String login, String senha) {
		try {
			LDAPManager ldapManager = new LDAPManager("172.17.2.4:389",
					"ou=todos,dc=utfpr,dc=edu,dc=br");

			ldapManager.connect();
			String username = login;
			String password = senha;

			boolean result;
			result = ldapManager.search("(uid=" + username + ")", null);
			if (result) {
				LDAPEntry entry = ldapManager.nextEntry();
				String loginDN = entry.getDN();
				if (ldapManager.login(loginDN, password)) {
					ldapManager.disconnect();
					return ldapManager.search(login);
				} else {
					System.out.println("Fail");
					return null;
				}

			}
			return null;
		} catch (LDAPException ex) {
			Logger.getLogger(MainLDAP.class.getName()).log(Level.SEVERE, null,
					ex);
			return null;
		}
	}
}
