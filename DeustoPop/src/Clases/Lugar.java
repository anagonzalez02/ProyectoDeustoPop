package Clases;

public class Lugar {
	private String direccion;
	private int codCiu;
	private String nomCiu;
	private int codPais;
	private String nomPais;
	
	
	public Lugar(String direccion, int codCiu, String nomCiu, int codPais, String nomPais) {
		super();
		this.direccion = direccion;
		this.codCiu = codCiu;
		this.nomCiu = nomCiu;
		this.codPais = codPais;
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

