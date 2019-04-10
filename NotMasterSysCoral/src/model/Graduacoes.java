package model;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class Graduacoes {
	
	private String id_modality;
	private List<String> graduacoes = new ArrayList<String>();
	
	public List<String> getGraduacoes() {
		return graduacoes;
	}
	public void setGraduacoes(List<String> graduacoes) {
		this.graduacoes = graduacoes;
	}
	public String getId_modality() {
		return id_modality;
	}
	public void setId_modality(String id_modality) {
		this.id_modality = id_modality;
	}
	
}
