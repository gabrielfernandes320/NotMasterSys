package model;

public class Cidade {
	
	private String cidade;
	private char estado;
	private String pais;
	
	public Cidade(String cidade, char estado, String pais) {
		super();
		this.cidade = cidade;
		this.estado = estado;
		this.pais = pais;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public char getEstado() {
		return estado;
	}
	public void setEstado(char estado) {
		this.estado = estado;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	
}
