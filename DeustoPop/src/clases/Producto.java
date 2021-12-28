package clases;

import java.awt.Image;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

/**
 * La clase Producto, clase madre de Calzado y Ropa, define cada producto que se sube a DeustoPop listo para venderse.
 * Estos productos son los productos que se suben los usuarios con cuenta de Deustopop a la plataforma deustopop (se registran en la base de datos) y que el resto de usuarios ven, comentan o compran.
 * Para ello, se definen con distintos atributos:
 * 		id -> 			Identificativo único de cada producto que lo distinguirá del resto y se hará una vez registrado en la base de datos
 * 		nombre -> 		Nombre descriptivo por el que los compradores se guíen para saber qué es (camiseta, pantalón, deportivas, chanclas)
 * 		fechaSubida -> 	Para saber cuándo se registró/subió el producto a la plataforma
 * 		etiquetas -> 	Etiquetas relacionadas con el producto. Por ejemplo: vintage, gótico...
 * 		precio -> 		Precio por el que el vendedor va vender el producto
 * 		imagen -> 		Imagen que los usuarios suban del producto a la hora de vender y por la que los compradores se guíen a la hora de comprar
 * 		estado -> 		Estado del producto. Sólo hay las tres opciones de la enumeración: malo, medio, bueno. 
 *		color -> 		Color principal del producto, por si en la imagen no se ve correctamente. Las opciones serán las de la enumeración Colores.
 *		usuario -> 		Usuario vendedor
 *		enVenta -> 		Un booleano que nos hará saber si el producto está en venta (true) o no (false). 
 *						En caso de estarlo, estará visible para todos usuarios compradores. De no estarlo, no será visible para nadie, pero seguirá existiendo en los registros de DeustoPop
 *						La única forma de borrarlo completamente, será mediante eliminarProducto() de la base de datos.
 * 		comentario ->	Un HashMap de comentarios en el que los usuarios interesados en comprarlo puedan comentar: opiniones, Pros, Contras... Esto facilitará al resto de compradores su decisión de compra.
 * 
 * 
 * Tenemos dos constructores
 * 		El primero: hay que definir todos los parámetros de la clase Producto.
 * 		El segundo: hay que definir solamente los parámetros que el programa pide al usuario en la ventanaVenderRopa o ventanaVenderCalzado.
 * 					El resto (id, fechaSubida, enVenta y comentarios) se inicializarán en la base de datos o se irán completando (otros uduarios hacen comentarios o otr usuario compra el producto)
 * **/

public class Producto { 
	
	private static final Image Image = null;

	private static int contador = 0;
	
	protected int id;
	protected String nombre;
	protected Calendar fechaSubida;
	protected String etiquetas;
	protected double precio;
	protected Image imagen;
	protected Estado estado;
	protected Colores color;
	protected Usuario usuario;
	protected boolean enVenta;
	protected HashMap <Usuario, String> comentario;
	
	
	public Producto (int id, String nombre, Calendar fechaSubida, String etiquetas, double precio, Image imagen, Estado estado, Colores color, Usuario usuario, boolean enVenta, HashMap <Usuario, String> comentario) {
		super();
		contador++;
		this.id = contador;
		this.nombre = nombre;
		this.fechaSubida = Calendar.getInstance();
		this.etiquetas = etiquetas;
		this.precio = precio;
		this.imagen = imagen;
		this.estado = estado;
		this.color = color;
		this.usuario = usuario;
		this.enVenta = true;
		this.comentario = comentario;
	}


	public Producto (String nombre, String etiquetas, double precio, Image imagen, Estado estado, Colores color, Usuario usuario) {
		super();
		contador++;
		this.id = id;
		this.nombre = nombre;
		this.fechaSubida = Calendar.getInstance();
		this.etiquetas = etiquetas;
		this.precio = precio;
		this.imagen = imagen;
		this.estado = estado;
		this.color = color;
		this.usuario = usuario;
		this.enVenta = true;
		this.comentario = comentario;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = contador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Calendar getFechaSubida() {
		return fechaSubida;
	}

	public void setFechaSubida(Calendar fechaSubida) {
		this.fechaSubida = Calendar.getInstance();
	}

	public String getEtiquetas() {
		return etiquetas;
	}

	public void setEtiquetas(String etiquetas) {
		this.etiquetas = etiquetas;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Image getImagen() {
		return imagen;
	}

	public void setImagen(Image imagen) {
		this.imagen = imagen;
	}

	
	
	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public Colores getColor() {
		return color;
	}

	public void setColor(Colores color) {
		this.color = color;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public boolean isEnVenta() {
		return enVenta;
	}

	public void setEnVenta(boolean enVenta) {
		this.enVenta = enVenta;
	}

	public HashMap<Usuario, String> getComentario() {
		return comentario;
	}

	public void setComentario(HashMap<Usuario, String> comentario) {
		this.comentario = comentario;
	}


	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", fechaSubida=" + fechaSubida + ", etiquetas=" + etiquetas
				+ ", precio=" + precio + ", imagen=" + imagen + ", estado=" + estado + ", color=" + color + ", usuario="
				+ usuario + ", enVenta=" + enVenta + ", comentario=" + comentario + "]";
	}


	
	
	
	

}
