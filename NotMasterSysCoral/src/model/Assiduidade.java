package model;

import java.sql.Timestamp;

public class Assiduidade {
	
	private int codigo_matricula;
	private Timestamp data_entrada;
	
	public Assiduidade() {
	}
	
	public Assiduidade(int codigo_matricula, Timestamp data_entrada) {
		super();
		this.codigo_matricula = codigo_matricula;
		this.data_entrada = data_entrada;
	}
	public int getCodigo_matricula() {
		return codigo_matricula;
	}

	public void setCodigo_matricula(int codigo_matricula) {
		this.codigo_matricula = codigo_matricula;
	}

	public Timestamp getData_entrada() {
		return data_entrada;
	}

	public void setData_entrada(Timestamp data_entrada) {
		this.data_entrada = data_entrada;
		
	}
	
	
	
	
	
}
