/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.cronos.cadastros;

import br.edu.utfpr.cm.cronos.conexao.TransactionManager;
import br.edu.utfpr.cm.cronos.daos.DaoTeacher;
import br.edu.utfpr.cm.cronos.model.Teacher;
import br.edu.utfpr.cm.cronos.userLDAP.LDAP;
import br.edu.utfpr.cm.cronos.userLDAP.UserLDAP;
import br.edu.utfpr.cm.saa.entidades.Usuario;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author a1028367
 */
@ManagedBean
@SessionScoped
public class CadastroProfessor {
    private Teacher teacher;
    
    public Teacher setTeacher(String siape){
        if(!siape.isEmpty()){
            try {
                    Usuario userLdap = (Usuario) LDAP.buscarUsuario(siape);
                    
                    System.out.println(userLdap.getNome());
                    Teacher teacherInserido = null;
                    if (userLdap == null) {                     
                       
                    } else {
                        teacherInserido = cadastrarUsuarioPeloLDAP(userLdap);
                        
                    }

                    return teacherInserido;

                } catch (Exception e) {
                    System.out.println("Erro na pesquisa");
                } finally {
                    System.out.println("fim.");
                }
        }
        return null;    
    }

    private Teacher cadastrarUsuarioPeloLDAP(Usuario userLdap) {
        if (!verificarSegundoDigito(userLdap.getLogin().charAt(1))) {
            
            try {
                TransactionManager.beginTransaction();
                Teacher teacher = new DaoTeacher().obterPorNome(userLdap.getNome());

                if (teacher != null) {
                    System.out.println("Ja esta na base: "+teacher.getName());
                    return teacher;
                } else {
                    teacher = new Teacher();
                    teacher.setName(userLdap.getNome());
                    teacher.setEmail(userLdap.getEmail());
                    
                    
                }


                new DaoTeacher().persistir(teacher);
                TransactionManager.commit();
                System.out.println("Inserido agora!");
                return teacher;
            } catch (Exception e) {
                System.out.println(e);
                TransactionManager.rollback();
                System.out.println("Deu errado!");
                return null;
            }
        } else {
             System.out.println("Deu errado 2!");
             return null;
        }
    }

    private boolean verificarSegundoDigito(char charAt) {
        switch (charAt) {
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                return true;
            default:
                return false;
        }
    }
    }
        

