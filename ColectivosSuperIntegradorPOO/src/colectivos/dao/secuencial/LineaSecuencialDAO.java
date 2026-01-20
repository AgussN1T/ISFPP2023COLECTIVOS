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
import colectivos.dao.ParadaDAO;
import colectivos.conexion.Factory;
import colectivos.dao.LineaDAO;
import colectivos.modelo.Linea;
import colectivos.modelo.Parada;
import colectivos.util.Time;

public class LineaSecuencialDAO implements LineaDAO{


	private List<Linea> list;
	private String name;
	private boolean actualizar;
	private Hashtable<Integer,Parada> paradas;
	
	public LineaSecuencialDAO() {
		paradas = cargarParadas();
		ResourceBundle rb = ResourceBundle.getBundle("secuencial");
		name = rb.getString("linea");
		actualizar = true;
	}
	//lee los datos del archivo
	private List<Linea> readFromFile(String file) {
		
		List<Linea> lineas  = new ArrayList<Linea>();		
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {

			String linea;

			while ((linea = br.readLine()) != null) {

				String[] elementos = linea.split(";");

				String id = elementos[0];
				int comienza = Time.toMins(elementos[1]);
				int finaliza = Time.toMins(elementos[2]);
				int frecuencia = Integer.parseInt(elementos[3]);
				
				Linea nuevaLinea = new Linea(id,comienza,finaliza,frecuencia);

				
				ArrayList<Parada> paradasLinea = new ArrayList<Parada>();
				
					for (int i = 4; i < elementos.length; i++) {
						paradasLinea.add(this.paradas.get(Integer.parseInt(elementos[i])));
						
						if(!this.paradas.get(Integer.parseInt(elementos[i])).getLineas().contains(nuevaLinea)) {
						this.paradas.get(Integer.parseInt(elementos[i])).agregarLinea(nuevaLinea);}
					
					}
					nuevaLinea.setParadas(paradasLinea);
					lineas.add(nuevaLinea);
					}
			
			
		} catch(IOException e) {
			System.err.println("Error al leer el archivo de lineas");
		}
		return lineas;
		}
	//escribe en el archivo
	public static void writeToFile(List<Linea> list, String file) {
        try (Formatter  outFile = new Formatter (file)) {
            for (Linea linea : list) {
                outFile.format("%s;%s;%s;%d;", linea.getId(),Time.toTime(linea.getComienza()) ,Time.toTime(linea.getFinaliza()), linea.getFrecuencia());
     
                List<Parada> paradas = linea.getParadas();
                for (Parada parada : paradas) {
                    outFile.format("%d;", parada.getId());
                }
                outFile.format("\n");
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error creating file: " + e.getMessage());
        } catch (FormatterClosedException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
	
	
	//devuelve todas las lineas
	@Override
	public List<Linea> buscarTodos() {
		if (actualizar) {
			list = readFromFile(name);
			actualizar = false;
		}
		return list;
	}
	//inserta la nueva linea
	@Override
	public void insertar(Linea linea) {
		list.add(linea);
		writeToFile(list, name);
		actualizar = true;
	}
	//actualiza la linea
	@Override
	public void actualizar(Linea linea) {
		int pos = list.indexOf(linea);
		list.set(pos, linea);
		writeToFile(list, name);
		actualizar = true;
	}
	//verifica si existe la linea
	public boolean nombreLineaExiste(List<Linea> list, String nombre) {
	    for (Linea linea : list) {
	        if (linea.getId().equals(nombre)) {
	        	return true; 
	        }
	    }
	    return false; 
	}
	
	//borra una linea de la lista
	@Override
	public void borrar(Linea linea) {
		
		for(int i = 0;i<list.size();i++) {
			if(list.get(i).equals(linea))
				list.remove(linea);
		}
		//list.remove(linea);
		for(int i = 0;i<linea.getParadas().size();i++) {
			linea.getParadas().get(i).eliminarLinea(linea);
		}
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