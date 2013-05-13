package br.edu.utfpr.cm.cronosmobile.controller;

import br.edu.utfpr.cm.cronosmobile.model.Period;
/**
 * 
 * @author Willyan Schultz Dworak
 * 
 */
public class PeriodAdapter {

	public int id = 0;
	public Period period;

	public PeriodAdapter(int id, Period period) {
		super();
		this.id = id;
		this.period = period;
	}

	public String toString() {
		return this.period.getName() + " (" + this.period.getStarttime() + " - " + this.period.getEndtime() + ")";
	}
}
