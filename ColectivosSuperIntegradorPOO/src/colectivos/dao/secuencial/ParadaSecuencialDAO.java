package colectivos.dao.secuencial;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.List;
import java.util.ResourceBundle;
import colectivos.dao.ParadaDAO;
import colectivos.modelo.Parada;
public class ParadaSecuencialDAO implements ParadaDAO{
	private List<Parada> list;
	private String name;
	private boolean actualizar;

	public ParadaSecuencialDAO() {
		ResourceBundle rb = ResourceBundle.getBundle("secuencial");
		name = rb.getString("parada");
		actualizar = true;
	}
	//lee los datos del archivo
	private List<Parada> readFromFile(String file) {
		List<Parada> paradas = new ArrayList<Parada>();

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String linea;

			while ((linea = br.readLine()) != null) {

				String[] elementos = linea.split(";");

				String id = elementos[0];
				String direccion = elementos[1];

				paradas.add(new Parada(Integer.parseInt(id), direccion));
			}
		} catch (IOException e) {
			System.out.println("Error al leer el archivo de paradas");
		}

		return paradas;
	}
	//escribe en el archivo
	private void writeToFile(List<Parada> list, String file) {
		Formatter outFile = null;
		try {
			outFile = new Formatter(file);
			for (Parada e : list) {
				outFile.format("%s;%s;\n", e.getId(), e.getDireccion());
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
	//retorna una lista con todas las paradas
	@Override
	public List<Parada> buscarTodos() {
		if (actualizar) {
			list = readFromFile(name);
			actualizar = false;
		}
		return list;
	}
	//inserta a la lista una nueva parada
	@Override
	public void insertar(Parada parada) {
		list.add(parada);
		writeToFile(list, name);
		actualizar = true;
	}
	//actualiza la parada
	@Override
	public void actualizar(Parada parada) {
		for(Parada rel : list) {
			if (rel.equals(parada)) {
				int pos = list.indexOf(rel);
				list.set(pos, parada);
			}
		}
		writeToFile(list, name);
	}
	//elimina la parada solicitadda
	@Override
	public void borrar(Parada parada) {
		list.remove(parada);
		writeToFile(list, name);
		actualizar = true;
	}


	
	
}
