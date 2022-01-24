package clases;

/**
 * La cuenta bancaria es una clase relacionada con la clase Usuario, donde una cuenta puede ser de más de un usuario, pero un usuario solo puede tener una cuenta.
 * Esto simula la cuenta bancaria real del usuario.
 * En caso de no tener saldo suficiente para una compra, se descontará de aquí.
 * Como atributos tendrá el número de tarjeta, y el dinnero total de la cuenta.
 * **/

public class CuentaBancaria {
	
	private int nTarjeta;
	private double dineroTotal;
	
	
	public CuentaBancaria(int nTarjeta, double dineroTotal) {
		super();
		this.nTarjeta = nTarjeta;
		this.dineroTotal = dineroTotal;
	}

	
	public int getnTarjeta() {
		return nTarjeta;
	}
	
	public void setnTarjeta(int nTarjeta) {
		this.nTarjeta = nTarjeta;
	}
	
	public double getDineroTotal() {
		return dineroTotal;
	}
	
	public void setDineroTotal(double dineroTotal) {
		this.dineroTotal = dineroTotal;
	}


	@Override
	public String toString() {
		return "CuentaBancaria [nTarjeta=" + nTarjeta + ", dineroTotal=" + dineroTotal + "]";
	}

}
