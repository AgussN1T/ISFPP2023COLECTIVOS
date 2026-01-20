package colectivos.dao.secuencial;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.Hashtable;
import java.util.List;
import java.util.ResourceBundle;

import colectivos.conexion.Factory;
import colectivos.dao.ParadaDAO;
import colectivos.dao.TramoDAO;
import colectivos.modelo.Tramo;
import colectivos.modelo.Parada;

	public class TramoSecuencialDAO implements TramoDAO{
	
		private List<Tramo> list;
		private String name;
		private Hashtable<Integer, Parada> paradas;
		private boolean actualizar;

		public TramoSecuencialDAO() {
			paradas = cargarParadas();
			ResourceBundle rb = ResourceBundle.getBundle("secuencial");
			name = rb.getString("tramo");
			actualizar = true;
		}
		//lee los datos del archivo
		private List<Tramo> readFromFile(String file) {
			ArrayList<Tramo> tramos = new ArrayList<Tramo>();
			
			
			try (BufferedReader br = new BufferedReader(new FileReader(file))) {

				String linea;

				while ((linea = br.readLine()) != null) {
					String[] elementos = linea.split(";");
					
					Parada inicio = this.paradas.get(Integer.parseInt(elementos[0]));
					Parada fin = this.paradas.get(Integer.parseInt(elementos[1]));
					int tiempo = Integer.parseInt(elementos[2]);
					int tipo = Integer.parseInt(elementos[3]);
					tramos.add(new Tramo(inicio, fin,tiempo, tipo));

				}
			} catch (IOException e) {
				System.out.println("Error al leer el archivo de tramos");
			}
			return tramos;
		}

		private void writeToFile(List<Tramo> list, String file) {
			Formatter outFile = null;
			try {
				outFile = new Formatter(file);
				for (Tramo e : list) {
					outFile.format("%s;%s;%d;%d;\n", e.getInicio().getId(), e.getFin().getId(),
							e.getTiempo(), e.getTipo());
				}
			} catch (FileNotFoundException fileNotFoundException) {
				System.err.println("Error creating file.");
			} catch (FormatterClosedException formatterClosedException) {
				System.err.println("Error writing to file.");
			} finally {
				if (outFile != null)
					outFile.close();
			}
		}
		//devuelve una lista con todos los tramos
		@Override
		public List<Tramo> buscarTodos() {
			if (actualizar) {
				list = readFromFile(name);
				actualizar = false;
			}
			return list;
		}
		
		//agrega el tramo a la lista
		@Override
		public void insertar(Tramo tramo) {
			list.add(tramo);
			writeToFile(list, name);
			actualizar = true;
		}
		//actualiza el tramo de la lista
		@Override
		public void actualizar(Tramo tramo) {
			int pos = list.indexOf(tramo);
			list.set(pos, tramo);
			writeToFile(list, name);
			actualizar = true;
		}
		//borra el tramo de la lista
		@Override
		public void borrar(Tramo tramo) {
			list.remove(tramo);
			writeToFile(list, name);
			actualizar = true;
		}

		//carga las paradas
		private Hashtable<Integer,Parada> cargarParadas() {
			Hashtable<Integer, Parada> paradas = new Hashtable<Integer, Parada>();
			ParadaDAO paradaDAO = (ParadaDAO) Factory.getInstancia("PARADA");
			List<Parada> ds = paradaDAO.buscarTodos();
			for (Parada d : ds)
				paradas.put(d.getId(), d);
			return paradas;	
			}

}
