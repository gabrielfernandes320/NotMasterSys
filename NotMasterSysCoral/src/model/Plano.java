package model;
import java.util.Date;

public class Plano {
	

	private String modalidade;
	private String plano;
	private double valor;
	
	public String getModalidade() {
		return modalidade;
	}

	public void setModalidade(String modalidade) {
		this.modalidade = modalidade;
	}

	public String getPlano() {
		return plano;
	}

	public void setPlano(String plano) {
		this.plano = plano;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	
	public Plano(String modalidade, String plano, double valor) {
		super();
		this.modalidade = modalidade;
		this.plano = plano;
		this.valor = valor;
	}
	public Plano() {
		
	}
	
	
}
