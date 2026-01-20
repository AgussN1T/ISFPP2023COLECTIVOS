package colectivos.modelo;

import java.util.List;
import java.util.Objects;
import java.util.ArrayList;

public class Parada {
	private int id;
	private String direccion;
	private List<Linea> lineas;
	
	public Parada(int id, String direccion) {
		this.id = id;
		this.direccion = direccion;
		this.lineas = new ArrayList<Linea>();
	}
	//elimina una linea de la lista
	public boolean eliminarLinea(Linea linea) {
		if(!lineas.contains(linea))return false;
		lineas.remove(linea);
		return true;
	}
	//agrega una linea de la lista
	public boolean agregarLinea(Linea linea) {
		if (this.lineas.contains(linea)) return false;
		
		this.lineas.add(linea);
		return true;
	}
		
	public int getId() {
		return id;
	}

	public String getDireccion() {
		return direccion;
	}
	
	public List<Linea> getLineas() {
		return lineas;
	}

	public void setLineas(List<Linea> lineas) {
		this.lineas = lineas;
	}
	//agrega una linea a la lista
	public boolean agregarLineas(Linea linea) {
		if(lineas.contains(linea)) return false;
		lineas.add(linea);
		return true;
	}
	
	@Override
	public String toString() {
		return "ID: " + id + " Direccion " + direccion;
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
		Parada other = (Parada) obj;
		return id == other.id;
	}

	

}
