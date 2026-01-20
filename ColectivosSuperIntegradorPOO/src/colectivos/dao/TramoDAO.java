package colectivos.dao;

import java.util.List;

import colectivos.modelo.Tramo;

public interface TramoDAO {
	void insertar(Tramo estacion);

	void actualizar(Tramo estacion);

	void borrar(Tramo estacion);

	List<Tramo> buscarTodos();
}
