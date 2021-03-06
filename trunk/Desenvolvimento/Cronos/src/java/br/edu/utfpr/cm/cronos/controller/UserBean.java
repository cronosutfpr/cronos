/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.cronos.controller;

import br.edu.utfpr.cm.cronos.daos.DaoGenerics;
import br.edu.utfpr.cm.cronos.daos.DaoUser;
import br.edu.utfpr.cm.cronos.model.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author AnaMaciel
 */
@ManagedBean(name = "userBean")
@SessionScoped
public class UserBean implements Serializable {

    private User user;
    static List<User> users;

    public UserBean() {
        user = new User();
        users = UserConverter.users;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static List<User> getUsers() {
        return users;
    }

    public static void setUsers(List<User> users) {
        UserBean.users = users;
    }

    public List<User> completeUser() {
        users =  new ArrayList<User>();
        DaoGenerics<User> daoUser = new DaoUser();
        users = daoUser.listar();
        return users;
    }
}
