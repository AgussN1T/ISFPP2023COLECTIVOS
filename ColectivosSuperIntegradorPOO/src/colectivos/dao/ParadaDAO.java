package colectivos.dao;

import java.util.List;

import colectivos.modelo.Parada;

public interface ParadaDAO {

	void insertar(Parada estacion);

	void actualizar(Parada estacion);

	void borrar(Parada estacion);

	List<Parada> buscarTodos();
	
}
