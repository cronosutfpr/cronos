package br.edu.utfpr.cm.cronosmobile.model;

public class PeriodAdapter {

    public int id = 0;
    public String nome = "";

    public PeriodAdapter(int id, String nome) {
	super();
	this.id = id;
	this.nome = nome;
    }

    public String toString() {
	return (nome);
    }
}
