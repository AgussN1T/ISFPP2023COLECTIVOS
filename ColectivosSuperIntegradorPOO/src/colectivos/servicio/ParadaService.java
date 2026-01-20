package colectivos.servicio;

import java.util.List;

import colectivos.modelo.Parada;


public interface ParadaService {
	void insertar(Parada parada);

	void actualizar(Parada parada);

	void borrar(Parada parada);

	List<Parada> buscarTodos();

}

