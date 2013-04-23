/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.cronos.model;

public enum Type {

    SALA_TEORICA("SALA TEORICA"),
    LABORATORIO("LABORATORIO"),
    ANFITEATRO("ANFITEATRO"),
    GINASIO("GINASIO");

    Type(String nome) {
        this.nome = nome;
    }
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
