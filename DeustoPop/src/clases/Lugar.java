package clases;

public class Lugar {
	private String direccion; 
	private String nomCiud;
	private String nomPais;
	
	
	public Lugar(String direccion, String nomCiud, String nomPais) {
		super();
		this.direccion = direccion;
		this.nomCiud = nomCiud;
		this.nomPais = nomPais;
	}


	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNomCiud() {
		return nomCiud;
	}

	public void setNomCiud(String nomCiud) {
		this.nomCiud = nomCiud;
	}

	public String getNomPais() {
		return nomPais;
	}

	public void setNomPais(String nomPais) {
		this.nomPais = nomPais;
	}

	
	

	
	
}          

