package model;

import java.util.Date;

public class Matricula {
	
	private int codigo_matricula;
	private int codigo_aluno;
	private Date data_matricula;
	private int dia_vencimento;
	private Date data_encerramento;
	private String aluno;
	
	public Matricula() {
	}
	
	public Matricula(int codigo_matricula, int codigo_aluno, Date data_matricula, int dia_vencimento, Date data_encerramento, String aluno) {
		super();
		this.codigo_matricula = codigo_matricula;
		this.codigo_aluno = codigo_aluno;
		this.data_matricula = data_matricula;
		this.dia_vencimento = dia_vencimento;
		this.data_encerramento = data_encerramento;
		this.aluno = aluno;
	}


	public int getCodigo_matricula() {
		return codigo_matricula;
	}

	public void setCodigo_matricula(int codigo_matricula) {
		this.codigo_matricula = codigo_matricula;
	}

	public int getCodigo_aluno() {
		return codigo_aluno;
	}

	public void setCodigo_aluno(int codigo_aluno) {
		this.codigo_aluno = codigo_aluno;
	}

	public Date getData_matricula() {
		return data_matricula;
	}

	public void setData_matricula(Date data_matricula) {
		this.data_matricula = data_matricula;
	}

	public int getDia_vencimento() {
		return dia_vencimento;
	}

	public void setDia_vencimento(int dia_vencimento) {
		this.dia_vencimento = dia_vencimento;
	}

	public Date getData_encerramento() {
		return data_encerramento;
	}

	public void setData_encerramento(Date data_encerramento) {
		this.data_encerramento = data_encerramento;
	}

	public String getAluno() {
		return aluno;
	}

	public void setAluno(String aluno) {
		this.aluno = aluno;
	}

}
