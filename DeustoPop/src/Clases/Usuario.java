package Clases;

public class Usuario{
	
	private String nombre;
	private int telefono;
	private int tarjeta;
	private int DNI;
	private String email;
	private String contrasenia;
	private Lugar vivienda;
	
	
	
	public Usuario(String nombre, int telefono, int tarjeta, int dNI, String email, String contrasenia,
			Lugar vivienda) {
		super();
		this.nombre = nombre;
		this.telefono = telefono;
		this.tarjeta = tarjeta;
		DNI = dNI;
		this.email = email;
		this.contrasenia = contrasenia;
		this.vivienda = vivienda;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public int getTelefono() {
		return telefono;
	}



	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}



	public int getTarjeta() {
		return tarjeta;
	}



	public void setTarjeta(int tarjeta) {
		this.tarjeta = tarjeta;
	}



	public int getDNI() {
		return DNI;
	}



	public void setDNI(int dNI) {
		DNI = dNI;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getContrasenia() {
		return contrasenia;
	}



	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}



	public Lugar getVivienda() {
		return vivienda;
	}



	public void setVivienda(Lugar vivienda) {
		this.vivienda = vivienda;
	}
}
	
	

