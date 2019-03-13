package model;

public class Usuario {

	private String usuario;
	private String perfil;
	
	public Usuario(String usuario, String perfil) {
		super();
		this.usuario = usuario;
		this.perfil = perfil;
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
	

}
