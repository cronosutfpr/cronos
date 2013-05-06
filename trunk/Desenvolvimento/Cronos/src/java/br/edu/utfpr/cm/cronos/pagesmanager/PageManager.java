package br.edu.utfpr.cm.cronos.pagesmanager;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "pageManager")
@SessionScoped
public class PageManager implements Serializable {

    public PageManager() {
    }
    private String paginaAtiva = "./pages/home.xhtml";

    public String getPaginaAtiva() {
        return paginaAtiva;
    }

    public void setPaginaAtiva(String paginaAtiva) {
        this.paginaAtiva = paginaAtiva;
    }

    public void setPg1() {
        this.paginaAtiva = "./pages/cad_salas.xhtml";
    }

    public void setPagListaSalasReserva() {
        this.paginaAtiva = "./pages/lista_salas_reserva.xhtml";
    }

    public void setPagListaSalas() {
        this.paginaAtiva = "./pages/lista_salas.xhtml";
    }
    
    public void setPagListaDisciplinas() {
        this.paginaAtiva = "./pages/lista_disciplinas.xhtml";
    }

    public void setPagListaProfs() {
        this.paginaAtiva = "./pages/lista_professores.xhtml";
    }

    public void setPagHorarioSalas() {
        this.paginaAtiva = "./pages/horario_sala.xhtml";
    }
    public void setPagCadTurmas() {
        this.paginaAtiva = "./pages/cad_turmas.xhtml";
    }
    
    public void setPagListaTurmas() {
        this.paginaAtiva = "./pages/list_turmas.xhtml";
    }

    public void setPagListaReservasUsuario(){
        this.paginaAtiva = "./pages/lista_reservas_usuario.xhtml";
    }
    public void setPg4() {
        this.paginaAtiva = "./pages/add_professores.xhtml";
    }

    public void setPg5() {
        this.paginaAtiva = "./pages/cad_disciplinas.xhtml";
    }

    public String getPg1() {
        return this.paginaAtiva;
    }

    public String getPg2() {
        return this.paginaAtiva;
    }

    public String getPg3() {
        return this.paginaAtiva;
    }

    public String getPg4() {
        return this.paginaAtiva;
    }

    public String getPg5() {
        return this.paginaAtiva;
    }
}
