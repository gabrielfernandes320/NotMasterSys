package model;

import java.sql.Array;
import java.util.ArrayList;

public class Graduacoes {
	
	private String id_modality;
	private ArrayList<String> graduacoes;
	
	public ArrayList<String> getGraduacoes() {
		return graduacoes;
	}
	public void setGraduacoes(ArrayList<String> graduacoes) {
		this.graduacoes = graduacoes;
	}
	
	public String getId_modality() {
		return id_modality;
	}
	public void setId_modality(String id_modality) {
		this.id_modality = id_modality;
	}
	
}
