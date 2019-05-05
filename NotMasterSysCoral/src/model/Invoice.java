package model;

import java.util.Date;

public class Invoice {
	private int codigo_matricula;
	private Date data_vencimento;
	private double valor;
	private Date data_pagamento;
	private Date data_cancelamento;
	private String initial_date;
	private String final_date;
	
	public Invoice(int codigo_matricula, Date data_vencimento, double valor, Date data_pagamento,
			Date data_cancelamento, String initial_date, String final_date) {
		super();
		this.codigo_matricula = codigo_matricula;
		this.data_vencimento = data_vencimento;
		this.valor = valor;
		this.data_pagamento = data_pagamento;
		this.data_cancelamento = data_cancelamento;
		this.initial_date = initial_date;
		this.final_date = final_date;
	}

	public Invoice() {
		super();
	}

	public int getCodigo_matricula() {
		return codigo_matricula;
	}

	public void setCodigo_matricula(int codigo_matricula) {
		this.codigo_matricula = codigo_matricula;
	}

	public Date getData_vencimento() {
		return data_vencimento;
	}

	public void setData_vencimento(Date data_vencimento) {
		this.data_vencimento = data_vencimento;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Date getData_pagamento() {
		return data_pagamento;
	}

	public void setData_pagamento(Date data_pagamento) {
		this.data_pagamento = data_pagamento;
	}

	public Date getData_cancelamento() {
		return data_cancelamento;
	}

	public void setData_cancelamento(Date data_cancelamento) {
		this.data_cancelamento = data_cancelamento;
	}

	public String getInitial_date() {
		return initial_date;
	}

	public void setInitial_date(String initial_date) {
		this.initial_date = initial_date;
	}

	public String getFinal_date() {
		return final_date;
	}

	public void setFinal_date(String final_date) {
		this.final_date = final_date;
	}
	
	
}
