package model;
import java.util.Date;

public class Plano {
	
	private int codigo_matricula;
	private Date data_entrada;
	
	public Plano(int codigo_matricula, Date data_entrada) {
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

	public Date getData_entrada() {
		return data_entrada;
	}

	public void setData_entrada(Date data_entrada) {
		this.data_entrada = data_entrada;
	} 
	
	
	
	
}
