package colectivos.servicio;

import java.util.List;

import colectivos.modelo.Parada;
import colectivos.conexion.Factory;
import colectivos.dao.ParadaDAO;

public class ParadaServiceImpl implements ParadaService {

	private ParadaDAO paradaDAO;

	public ParadaServiceImpl() {
		paradaDAO = (ParadaDAO) Factory.getInstancia("PARADA");
	}

	@Override
	public void insertar(Parada parada) {
		paradaDAO.insertar(parada);

	}

	@Override
	public void actualizar(Parada parada) {
		paradaDAO.actualizar(parada);

	}

	@Override
	public void borrar(Parada parada) {
		paradaDAO.borrar(parada);

	}

	@Override
	public List<Parada> buscarTodos() {
		return paradaDAO.buscarTodos();
	}

}
