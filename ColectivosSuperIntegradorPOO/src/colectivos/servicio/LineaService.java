package colectivos.servicio;

import java.util.List;

import colectivos.modelo.Linea;

public interface LineaService {

		void insertar(Linea linea);

		void actualizar(Linea linea);

		void borrar(Linea linea);

		List<Linea> buscarTodos();

}
