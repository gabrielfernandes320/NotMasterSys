package model;

public class Usuario {

	private String usuario;
	private String perfil;
	private String password;
	
	public Usuario() {
	}
	
	public Usuario(String usuario, String perfil, String senha) {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
