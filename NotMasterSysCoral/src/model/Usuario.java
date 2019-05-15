package model;

public class Usuario {

	private String usuario;
	private String perfil;
	private String password;
	
	public Usuario() {
	}
	
	public Usuario( String perfil,String usuario, String senha) {
		super();
		this.perfil = perfil;
		this.usuario = usuario;

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
