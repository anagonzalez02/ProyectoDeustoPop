package clases;

public class Lugar {
	private String direccion;
	private String nomCiu;
	private String nomPais;
	
	
	public Lugar(String direccion, String nomCiu, String nomPais) {
		super();
		this.direccion = direccion;
		this.nomCiu = nomCiu;
		this.nomPais = nomPais;
	}


	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNomCiu() {
		return nomCiu;
	}

	public void setNomCiu(String nomCiu) {
		this.nomCiu = nomCiu;
	}

	public String getNomPais() {
		return nomPais;
	}

	public void setNomPais(String nomPais) {
		this.nomPais = nomPais;
	}

	
	

	
	
}          

