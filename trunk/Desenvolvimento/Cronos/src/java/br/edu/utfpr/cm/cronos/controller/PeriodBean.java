/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.cronos.controller;

import br.edu.utfpr.cm.cronos.daos.DaoGenerics;
import br.edu.utfpr.cm.cronos.daos.DaoPeriod;
import br.edu.utfpr.cm.cronos.daos.DaoUser;
import br.edu.utfpr.cm.cronos.model.Period;
import br.edu.utfpr.cm.cronos.model.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author junior
 */
@ManagedBean(name = "periodBean")
@SessionScoped
public class PeriodBean implements Serializable {

    private Period period;
    static List<Period> periods;

    public PeriodBean() {
        period = new Period();
        periods = PeriodConverter.periods;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public static List<Period> getPeriods() {
        return periods;
    }

    public static void setUsers(List<User> users) {
        UserBean.users = users;
    }

    public List<Period> completeUser() {
        periods =  new ArrayList<Period>();
        DaoGenerics<Period> daoPeriod = new DaoPeriod();
        periods = daoPeriod.listar();
        return periods;
    }
}