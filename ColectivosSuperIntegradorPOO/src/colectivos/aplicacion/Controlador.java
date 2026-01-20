package colectivos.aplicacion;

import java.util.List;
import java.util.ResourceBundle;

import colectivos.GUI.MenuPrincipal;
import colectivos.modelo.*;
import colectivos.negocio.*;
import colectivos.util.Time;

public class Controlador {
	private Calculo calculo;
	private MenuPrincipal menuPrincipal;
	private Empresa empresa;
	private Configuracion configuracion;
	private ResourceBundle rb;

	public Controlador() {
	}

	
	public void debug() {
		for(Linea l :empresa.getLineas()) {
			System.out.println(l.getId());
			System.out.println(l.getComienza());
			System.out.println(l.getFinaliza());
			System.out.println(l.getFrecuencia());
			for(Parada parada : l.getParadas()) {
				System.out.println(parada.getDireccion());
			}
			
		}
		for(Parada p :empresa.getParadas()) {
			System.out.println(p.toString());
		}
		for(Tramo t :empresa.getTramos()) {
			System.out.println(t.toString());
		}
		
	}
	
	public MenuPrincipal getMenuPrincipal() {
		return menuPrincipal;
	}

	public void setMenuPrincipal(MenuPrincipal menuPrincipal) {
		this.menuPrincipal = menuPrincipal;
	}

	public Configuracion getConfiguracion() {
		return configuracion;
	}

	public void setConfiguracion(Configuracion configuracion) {
		this.configuracion = configuracion;
	}

	public ResourceBundle getRb() {
		return rb;
	}

	public void setRb(ResourceBundle rb) {
		this.rb = rb;
	}

	public Calculo getCalculo() {
		return calculo;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void iniciarCalculo() {
		calculo.iniciar(getParadas(), getLineas(), getTramos());
	}

	public ResourceBundle getResourceBundle() {
		return configuracion.getResourceBundle();
	}

	public void setCalculo(Calculo calculo) {
		this.calculo = calculo;
	}

	public void setInterfaz(MenuPrincipal interfaz) {
		this.menuPrincipal = interfaz;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<Parada> getParadas() {
		return empresa.getParadas();
	}

	public List<Linea> getLineas() {
		return empresa.getLineas();
	}

	public List<Tramo> getTramos() {
		return empresa.getTramos();
	}

	public ResourceBundle cambiarLenguaje(int n) {
		configuracion.cambiarIdioma(n);
		setRb(configuracion.getResourceBundle());
		return this.rb;
	}

	// manda un mensaje de error
	private void mensajeErrorMP(String s) {
		this.menuPrincipal.mensajeError(s);
	}

	public void actualizarComboBoxMenuPrincipal() {
		this.getMenuPrincipal().actualizarComboBox();
	}

	// calcula el camino mas corto
	private List<List<Tramo>> calcularCaminoMasCorto(String idOrigen, String idDestino, int tiempo, int cantLineas) {
		List<Parada> paradasAux = empresa.getParadas();
		int pos1;
		int pos2;
		try {
			pos1 = paradasAux.indexOf(new Parada(Integer.parseInt(idOrigen), ""));
			pos2 = paradasAux.indexOf(new Parada(Integer.parseInt(idDestino), ""));
		} catch (NumberFormatException ex) {
			return null;
		}
		if (pos1 == -1 || pos2 == -1) {
			mensajeErrorMP("Una de las paradas ingresadas son inválidas");
			return null;
		}

		List<List<Tramo>> caminoMasCorto = calculo.recorridos(paradasAux.get(pos1), paradasAux.get(pos2), tiempo,
				cantLineas);
		return caminoMasCorto;
	}

	// Devuelve una lista con los caminos mas cortos a la interfaz
	public String obtenerCaminoMasCorto(String idOrigen, String idDestino, String tiempo, String cantLineas) {
		if (idOrigen.equals(""))
			return "Ingrese un ID de origen válido";
		if (idDestino.equals(""))
			return "Ingrese un ID de destino válido";

		List<List<Tramo>> caminoMasCorto = this.calcularCaminoMasCorto(idOrigen, idDestino, Time.toMins(tiempo),
				Integer.parseInt(cantLineas));

		if (caminoMasCorto == null || caminoMasCorto.isEmpty())
			return "No hay recorridos posibles";
		if (idOrigen.equalsIgnoreCase(idDestino))
			return "El origen y el destino son iguales";
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < caminoMasCorto.size(); i++) {
			// int tiempoTotal = 0;
			int tiempoTotal = Time.toMins(tiempo);
			sb.append("==recorrido: " + (i + 1) + "== \n");
			sb.append(" Llega a la parada: " + tiempo + "\n");
			for (int j = 0; j < caminoMasCorto.get(i).size() - 1; j++) {
				sb.append(" " + Time.toTime(tiempoTotal) + "-- "
						+ caminoMasCorto.get(i).get(j).getInicio().getDireccion() + " --> ");
				sb.append(caminoMasCorto.get(i).get(j).getFin().getDireccion() + " ");
				// sb.append(" en " + caminoMasCorto.get(i).get(j).getTiempo() + " minutos ");
				if (caminoMasCorto.get(i).get(j).lineaComun() == null) {
					sb.append("\n");
				} else {
					sb.append(caminoMasCorto.get(i).get(j).lineaComun() + "\n");
				}
				tiempoTotal += caminoMasCorto.get(i).get(j).getTiempo();
			}
			sb.append("Horario de llegada estimado " + Time.toTime(tiempoTotal) + " \n \n");
		}

		return sb.toString();

	}

	// devuelve una lista con las paradas
	public String listarParadas() {
		StringBuilder sb = new StringBuilder();
		for (Parada iterador : this.getParadas()) {
			sb.append(" ID: " + iterador.getId());
			sb.append(" Direccion: " + iterador.getDireccion() + "\n" + " Lineas: ");
			for (int i = 0; i < iterador.getLineas().size(); i++) {
				sb.append(iterador.getLineas().get(i).getId() + ";");
			}
			sb.setLength(sb.length() - 1);
			sb.append("\n");
		}

		return sb.toString();
	}

	// devuelve una lista con las lineas
	public String listarLineas() {
		StringBuilder sb = new StringBuilder();
		for (Linea iterador : this.getLineas()) {
			sb.append("Linea: " + iterador.getId() + "\n");
			sb.append("Desde " + Time.toTime(iterador.getComienza()) + " Hasta " + Time.toTime(iterador.getFinaliza()));
			sb.append(" Frecuencia: " + Time.toTime(iterador.getFrecuencia()) + " minutos \n");
			sb.append("Paradas:");
			for (int i = 0; i < iterador.getParadas().size(); i++) {
				sb.append(" " + iterador.getParadas().get(i).getId());
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	// devuelve una lista con los tramos
	public String listarTramos() {
		StringBuilder sb = new StringBuilder();
		for (Tramo iterador : this.getTramos()) {
			sb.append(" " + iterador.getInicio().toString());
			sb.append(" --> " + iterador.getFin().toString());
			sb.append(" tiempo: " + iterador.getTiempo());
			sb.append(" tipo: " + iterador.getTipo() + "\n");
		}
		return sb.toString();
	}

	public void insertarLinea(String id, int comienza, int finaliza, int frecuencia, String paradasL) {
		Linea nuevaLinea = new Linea(id, comienza, finaliza, frecuencia);
		List<Parada> paradasAux = empresa.getParadas();
		String[] separador = paradasL.split(";");
		for (int i = 0; i < separador.length; i++) {
			if (!paradasAux.contains(new Parada(Integer.parseInt(separador[i]), "")))
				throw new ParadaInexistenteException();
			nuevaLinea.getParadas()
					.add(paradasAux.get(paradasAux.indexOf(new Parada(Integer.parseInt(separador[i]), ""))));
		}
		empresa.agregarLinea(nuevaLinea);
		mensajeABM(rb.getString("Mensaje_LineaCorrectaInsertar"));
	}

	public void borrarLinea(String id) {
		try {
			empresa.borrarLinea(new Linea(id, 0, 0, 0));
		} catch (LineaInexistenteException ex) {
			mensajeABM(rb.getString("Exception_LineaInexistente"));
			return;
		}
		mensajeABM(rb.getString("Mensaje_LineaCorrectaEliminar"));
	}

	public void actualizarLinea(String id, int comienza, int finaliza, int frecuencia, String paradas) {
		Linea nuevaLinea = new Linea(id, comienza, finaliza, frecuencia);
		List<Parada> paradasAux = empresa.getParadas();
		String[] separador = paradas.split(";");
		try {
			for (int i = 0; i < separador.length; i++) {
				if (!paradasAux.contains(new Parada(Integer.parseInt(separador[i]), "")))
					throw new ParadaInexistenteException();
				nuevaLinea.getParadas()
						.add(paradasAux.get(paradasAux.indexOf(new Parada(Integer.parseInt(separador[i]), ""))));
			}
		} catch (NumberFormatException ex) {
			mensajeABM(rb.getString("Exception_ErrorEntradaDatos"));
			return;
		} catch (ParadaInexistenteException ex) {
			mensajeABM(rb.getString("Exception_ParadaInexistente"));
			return;
		}
		try {
			empresa.modificarLinea(nuevaLinea);
		} catch (LineaInexistenteException ex) {
			mensajeABM(rb.getString("Exception_LineaInexistente"));
			return;
		}
		mensajeABM(rb.getString("Mensaje_LineaCorrectaActualizar"));
	}

	public void insertarTramo(String idOrigen, String idDestino, String tiempo, String tipo) {
		List<Parada> p = empresa.getParadas();
		Parada p1 = p.get(p.indexOf(new Parada(Integer.parseInt(idOrigen), "")));
		Parada p2 = p.get(p.indexOf(new Parada(Integer.parseInt(idDestino), "")));
		try {
			empresa.agregarTramo(new Tramo(p1, p2, Integer.parseInt(tiempo), Integer.parseInt(tipo)));
			mensajeABM(rb.getString("Mensaje_TramoCorrectoInsertar"));
		} catch (TramoExistenteException ex) {
			mensajeABM(rb.getString("Exception_TramoExistente"));
		}
		catch(NumberFormatException ex) {
			mensajeABM("Algunos de los ID's no son validos");
		}
	}

	public void borrarTramo(String idOrigen, String idDestino) {
		try {
			empresa.borrarTramo(new Tramo(new Parada(Integer.parseInt(idOrigen), ""),
					new Parada(Integer.parseInt(idDestino), ""), 1, 1));
		} catch (NumberFormatException ex) {
			mensajeABM(rb.getString("Exception_TramoInexistente"));
			return;
		} catch (TramoInexistenteException ex) {
			mensajeABM(rb.getString("Exception_TramoInexistente"));
			return;
		}
		mensajeABM(rb.getString("Mensaje_TramoCorrectoEliminar"));
	}

	public void actualizarTramo(String idOrigen, String idDestino, int tiempo, int tipo) {
		List<Parada> p = empresa.getParadas();
		Parada p1 = p.get(p.indexOf(new Parada(Integer.parseInt(idOrigen), "")));
		Parada p2 = p.get(p.indexOf(new Parada(Integer.parseInt(idDestino), "")));
		try {
			empresa.modificarTramo(new Tramo(p1, p2, tiempo, tipo));
			mensajeABM(rb.getString("Mensaje_TramoCorrectoActualizar"));
		} catch (TramoInexistenteException ex) {
			mensajeABM(rb.getString("Exception_TramoInexistente"));
		}
		catch(NumberFormatException ex) {
			mensajeABM("El id no es válido");
		}
	}

	public void insertarParada(String id, String nombre) {
		try {
			empresa.agregarParada(new Parada(Integer.parseInt(id), nombre));
			mensajeABM(rb.getString("Mensaje_ParadaCorrectaInsertar"));
		} catch (ParadaExistenteException ex) {
			mensajeABM(rb.getString("Exception_ParadaExistente"));
		}
		catch(NumberFormatException ex) {
			mensajeABM("El id no es válido");
		}
	}

	public void borrarParada(String id) {
		try {
			empresa.borrarParada(new Parada(Integer.parseInt(id), ""));
		} catch (ParadaInexistenteException ex) {
			mensajeABM(rb.getString("Exception_ParadaInexistente"));
			return;
		} catch (ParadaReferenciadaException ex) {
			mensajeABM(rb.getString("Exception_ParadaReferenciada"));
			return;
		}
		catch(NumberFormatException ex) {
			mensajeABM("La parada con id: " + id + " no existe");
		}
		mensajeABM(rb.getString("Mensaje_ParadaCorrectaEliminar"));
	}

	// llama al metodo modificar parada de la empresa
	public void actualizarParada(String id, String nombre) {
		try {
			empresa.modificarParada(new Parada(Integer.parseInt(id), nombre));
			mensajeABM(rb.getString("Mensaje_ParadaCorrectaActualizar"));
		} catch (ParadaInexistenteException ex) {
			mensajeABM(rb.getString("Exception_ParadaInexistente"));
			return;
		}
		catch(NumberFormatException ex) {
			mensajeABM("El ID no es válido");
		}
	}

	// retorna la linea correspondiente
	public Linea buscarLinea(String id) {
		try {
			return empresa.buscarLinea(new Linea(id, 1, 1, 1));
		} catch (LineaInexistenteException ex) {
			return null;
		}
	}

	public Tramo buscarTramo(String idOrigen, String idDestino) {
		try {
			return empresa.buscarTramo(new Tramo(new Parada(Integer.parseInt(idOrigen), ""),
					new Parada(Integer.parseInt(idDestino), ""), 1, 1));
		} catch (TramoInexistenteException ex) {
			return null;
		}
		catch(NumberFormatException ex) {
			mensajeABM("El ID no es válido");
			return null;
		}
	}

	public Parada buscarParada(String id) {
		try {
			return empresa.buscarParada(new Parada(Integer.parseInt(id), ""));
		} catch (ParadaInexistenteException ex) {
			return null;
		}
		catch(NumberFormatException ex) {
			mensajeABM("El ID no es válido");
			return null;
		}
	}

	public void mensajeABM(String s) {
		menuPrincipal.mensajeABM(s);
	}

}
