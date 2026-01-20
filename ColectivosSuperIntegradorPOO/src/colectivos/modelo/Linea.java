package colectivos.modelo;

import java.util.List;
import java.util.Objects;
import java.util.ArrayList;

public class Linea {
	private String id;
	private int comienza;
	private int finaliza;
	private int frecuencia;
	private List<Parada> paradas;

	public Linea(String id, int comienza, int finaliza, int frecuencia) {
		this.id = id;
		this.comienza = comienza;
		this.finaliza = finaliza;
		this.frecuencia = frecuencia;
		this.paradas = new ArrayList<Parada>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getComienza() {
		return comienza;
	}

	public void setComienza(int comienza) {
		this.comienza = comienza;
	}

	public int getFinaliza() {
		return finaliza;
	}

	public void setFinaliza(int finaliza) {
		this.finaliza = finaliza;
	}

	public int getFrecuencia() {
		return frecuencia;
	}

	public void setFrecuencia(int frecuencia) {
		this.frecuencia = frecuencia;
	}

	public List<Parada> getParadas() {
		return paradas;
	}

	public void setParadas(List<Parada> paradas) {
		this.paradas = paradas;
	}

	

	@Override
	public String toString() {
		return "Linea: " + id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Linea other = (Linea) obj;
		return Objects.equals(id, other.id);
	}

}
