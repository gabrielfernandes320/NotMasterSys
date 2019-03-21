package model;

public class Usuario {

	private String usuario;
	private String perfil;
	private String senha;
	
	public Usuario() {
	}
	
	public Usuario(String usuario, String perfil, String senha) {
		super();
		this.usuario = usuario;
		this.perfil = perfil;
		this.senha = senha;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPerfil() {
		return perfil;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
}
