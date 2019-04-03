package model;
import java.util.Date;

public class Plano {
	

	private String modalidade;
	private String plano;
	private double valor_mensal;
	
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
		return valor_mensal;
	}

	public void setValor(double valor_mensal) {
		this.valor_mensal = valor_mensal;
	}

	
	public Plano(String modalidade, String plano, double valor_mensal) {
		super();
		this.modalidade = modalidade;
		this.plano = plano;
		this.valor_mensal = valor_mensal;
	}
	public Plano() {
		
	}
	
	
}
