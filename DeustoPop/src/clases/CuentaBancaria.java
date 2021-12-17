package clases;

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
	
	public void setnTarjeta(double dineroTotal) {
		this.dineroTotal = dineroTotal;
	}
	
	public double getDineroTotal() {
		return dineroTotal;
	}
	
	public void setDineroTotal(double d) {
		this.dineroTotal = d;
	}


	@Override
	public String toString() {
		return "CuentaBancaria [nTarjeta=" + nTarjeta + ", dineroTotal=" + dineroTotal + "]";
	}

}
