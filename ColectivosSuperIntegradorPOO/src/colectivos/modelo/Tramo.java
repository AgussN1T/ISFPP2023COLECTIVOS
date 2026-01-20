package colectivos.modelo;

import java.util.Objects;

public class Tramo {
	private int tiempo;
	private int tipo;
	private Parada inicio;
	private Parada fin;
	public Tramo( Parada inicio, Parada fin,int tiempo, int tipo) {
		super();
		this.tipo = tipo;
		this.tiempo = tiempo;
		this.inicio = inicio;
		this.fin = fin;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public int getTiempo() {
		return tiempo;
	}
	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}
	public Parada getInicio() {
		return inicio;
	}
	public void setInicio(Parada inicio) {
		this.inicio = inicio;
	}
	public Parada getFin() {
		return fin;
	}
	public void setFin(Parada fin) {
		this.fin = fin;
	}
	//devuelve la primer linea en comun que tengan las paradas de origen y destino
	public Linea lineaComun() {
		for (int i = 0; i<this.getInicio().getLineas().size();i++) {
			for (int j = 0 ; j<this.getFin().getLineas().size();j++) {
				if(this.getFin().getLineas().contains(this.getInicio().getLineas().get(i))) 
					return this.getInicio().getLineas().get(i);
			}
		}
		
			return null;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(fin, inicio);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tramo other = (Tramo) obj;
		return Objects.equals(fin, other.fin) && Objects.equals(inicio, other.inicio);
	}
	@Override
	public String toString() {
		return "Tramo [tiempo: " + tiempo + ", tipo: " + tipo + ", inicio: " + inicio.toString() + ", fin:" + fin.toString() + "]";
	}
	
	
	
}
