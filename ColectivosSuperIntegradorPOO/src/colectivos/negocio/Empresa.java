package colectivos.negocio;

import java.util.ArrayList;
import java.util.List;

import colectivos.aplicacion.Controlador;
import colectivos.modelo.*;
import colectivos.servicio.*;

public class Empresa {
	private static Empresa empresa = null;

	private String nombre;
	private List<Linea> lineas;
	private LineaService lineaService;
	private List<Parada> paradas;
	private ParadaService paradaService;
	private List<Tramo> tramos;
	private TramoService tramoService;
	private Controlador controlador;
	private Subject subject;

	public static Empresa getEmpresa() {
		if (empresa == null) {
			empresa = new Empresa();
		}
		return empresa;
	}

	private Empresa() {
		super();
		paradas = new ArrayList<Parada>();
		paradaService = new ParadaServiceImpl();
		paradas.addAll(paradaService.buscarTodos());
		lineas = new ArrayList<Linea>();
		lineaService = new LineaServiceImpl();
		lineas.addAll(lineaService.buscarTodos());
		tramos = new ArrayList<Tramo>();
		tramoService = new TramoServiceImpl();
		tramos.addAll(tramoService.buscarTodos());
	}

	public void agregarLinea(Linea linea) {
		if (lineas.contains(linea))
			throw new LineaExistenteException();
		lineas.add(linea);
		for (int i = 0; i < linea.getParadas().size(); i++) {
			Parada parada = linea.getParadas().get(i);
			parada.agregarLinea(linea);
			this.modificarParada(parada);
		}
		lineaService.insertar(linea);
		subject.refresh();
	}

	public void modificarLinea(Linea linea) {
		int pos = lineas.indexOf(linea);
		if (pos == -1)
			throw new LineaInexistenteException();
		lineas.set(pos, linea);
		lineaService.actualizar(linea);
		subject.refresh();
	}

	public void borrarLinea(Linea linea) {
		Linea emp = buscarLinea(linea);
		if (!lineas.remove(emp))
			throw new LineaInexistenteException();
		for (int i = 0; i < linea.getParadas().size(); i++) {
			Parada parada = linea.getParadas().get(i);
			parada.eliminarLinea(linea);
			this.modificarParada(parada);
		}
		lineaService.borrar(linea);
		subject.refresh();
	}

	public void agregarTramo(Tramo tramo) {
		if (tramos.contains(tramo)) {
			throw new TramoExistenteException();
		}
		tramos.add(tramo);
		tramoService.insertar(tramo);

		subject.refresh();

	}

	public void modificarTramo(Tramo tramo) {
		if (!tramos.contains(tramo))
			throw new TramoInexistenteException();
		int pos = tramos.indexOf(tramo);
		tramos.set(pos, tramo);
		tramoService.actualizar(tramo);
		subject.refresh();
	}

	public void borrarTramo(Tramo tramo) {
		if (!tramos.contains(tramo))
			throw new TramoInexistenteException();
		tramos.add(tramo);
		tramoService.borrar(tramo);
		subject.refresh();
	}

	public void agregarParada(Parada parada) {
		if (paradas.contains(parada))
			throw new ParadaExistenteException();
		paradas.add(parada);
		paradaService.insertar(parada);
		subject.refresh();
	}

	public void modificarParada(Parada parada) {
		if (!paradas.contains(parada))
			throw new ParadaInexistenteException();
		int pos = paradas.indexOf(parada);
		Parada paradaAux = paradas.get(pos);
		parada.getLineas().addAll(paradaAux.getLineas());
		paradas.set(pos, parada);
		paradaService.actualizar(parada);
		subject.refresh();
	}

	public void borrarParada(Parada parada) {
		if (!paradas.contains(parada))
			throw new ParadaInexistenteException();
		for (int i = 0; i < lineas.size(); i++) {
			if (lineas.get(i).getParadas().contains(parada))
				throw new ParadaReferenciadaException();
		}
		for (int i = 0; i < tramos.size(); i++) {
			if (tramos.get(i).getInicio().equals(parada) || tramos.get(i).getFin().equals(parada))
				throw new ParadaReferenciadaException();
		}
		paradaService.borrar(parada);
		subject.refresh();
	}

	public Linea buscarLinea(Linea Linea) {
		int pos = lineas.indexOf(Linea);
		if (pos == -1)
			throw new LineaInexistenteException();
		return lineas.get(pos);

	}

	public Parada buscarParada(Parada parada) {
		int pos = paradas.indexOf(parada);
		if (pos == -1)
			throw new ParadaInexistenteException();
		return paradas.get(pos);
	}

	public Tramo buscarTramo(Tramo tramo) {
		int pos = tramos.indexOf(tramo);
		if (pos == -1)
			throw new TramoInexistenteException();
		return tramos.get(pos);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Linea> getLineas() {
		return lineas;
	}

	public List<Parada> getParadas() {
		return paradas;
	}

	public List<Tramo> getTramos() {
		return tramos;
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public Controlador getControlador() {
		return controlador;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

}
