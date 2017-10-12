package mundo;

import java.sql.Timestamp;
import java.util.Date;

public class Objeto {

	private int numSecuencia;
	
	private Timestamp fechaCreacion;

	
	public Objeto(int numSecuencia, Timestamp fechaCreacion) {
		super();
		this.numSecuencia = numSecuencia;
		this.fechaCreacion = fechaCreacion;
	}


	public int getNumSecuencia() {
		return numSecuencia;
	}


	public void setNumSecuencia(int numSecuencia) {
		this.numSecuencia = numSecuencia;
	}


	public Timestamp getFechaCreacion() {
		return fechaCreacion;
	}


	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	
}
