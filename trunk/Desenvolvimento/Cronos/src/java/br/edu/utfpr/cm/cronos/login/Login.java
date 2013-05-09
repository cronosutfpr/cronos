/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.cronos.login;

import br.edu.utfpr.cm.cronos.userLDAP.LoginLDAP;
import br.edu.utfpr.cm.cronos.userLDAP.UserLDAP;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author a1028367
 */
@ManagedBean
@SessionScoped
public class Login {
    private String senha;
    private String siape;

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSiape() {
        return siape;
    }

    public void setSiape(String siape) {
        this.siape = siape;
    }
    
    public Usuario validarUsuario() {
        LoginLDAP login = new LoginLDAP();
        Usuario user = (Usuario) login.logarNoLDAP(siape, senha);
        if (user != null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("./home.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Erro", "Dados inválidos!!!"));
        }
        return user;
    }
    
    
    
}
