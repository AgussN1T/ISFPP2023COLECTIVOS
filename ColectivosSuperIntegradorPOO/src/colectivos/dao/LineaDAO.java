package colectivos.dao;

import java.util.List;

import colectivos.modelo.Linea;

public interface LineaDAO {
	
	void insertar(Linea estacion);

	void actualizar(Linea estacion);

	void borrar(Linea estacion);

	List<Linea> buscarTodos();
}
