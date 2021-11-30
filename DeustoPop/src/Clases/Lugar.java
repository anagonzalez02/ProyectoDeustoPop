package Clases;

public class Lugar {
	private String direccion;
	private int codCiu = 0;
	private String nomCiu;
	private int codPais = 0;
	private String nomPais;
	
	
	public Lugar(String direccion, String nomCiu, String nomPais) {
		super();
		this.direccion = direccion;
		this.codCiu = 0;
		this.nomCiu = nomCiu;
		this.codPais = 0;
		this.nomPais = nomPais;
		
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public int getCodCiu() {
		return codCiu;
	}


	public void setCodCiu(int codCiu) {
		this.codCiu = codCiu;
	}


	public String getNomCiu() {
		return nomCiu;
	}


	public void setNomCiu(String nomCiu) {
		this.nomCiu = nomCiu;
	}


	public int getCodPais() {
		return codPais;
	}


	public void setCodPais(int codPais) {
		this.codPais = codPais;
	}


	public String getNomPais() {
		return nomPais;
	}


	public void setNomPais(String nomPais) {
		this.nomPais = nomPais;
	}

	
	
}          

