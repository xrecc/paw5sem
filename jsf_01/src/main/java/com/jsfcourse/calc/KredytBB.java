package com.jsfcourse.calc;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

@Named
@RequestScoped
//@SessionScoped
public class KredytBB {
	private String kwota;
	private String czas;
	private String procent;
	private Double result;

	@Inject
	FacesContext ctx;

	public String getkwota() {
		return kwota;
	}

	public void setkwota(String kwota) {
		this.kwota = kwota;
	}

	public String getczas() {
		return czas;
	}

	public void setczas(String czas) {
		this.czas = czas;
	}
	public String getprocent() {
		return procent;
	}

	public void setprocent(String procent) {
		this.procent = procent;
	}

	public Double getResult() {
		return result;
	}

	public void setResult(Double result) {
		this.result = result;
	}

	public boolean doTheMath() {
		try {
			double kwota = Double.parseDouble(this.kwota);
			double czas = Double.parseDouble(this.czas);
			double procent = Double.parseDouble(this.procent);
			result = (double) Math.round(((kwota/(czas*12))+(kwota/(czas*12))*(procent/100))*100)/100;

			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacja wykonana poprawnie", null));
			return true;
		} catch (Exception e) {
			ctx.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd podczas przetwarzania parametrów", null));
			return false;
		}
	}

	// Go to "showresult" if ok
	public String calc() {
		if (doTheMath()) {
			return "showresult";
		}
		return null;
	}

	// Put result in messages on AJAX call
	public String calc_AJAX() {
		if (doTheMath()) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Rata miesięczna: " + result, null));
		}
		return null;
	}

	public String info() {
		return "info";
	}
}
