package colectivos.aplicacion;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;


public class Configuracion {
	private static Configuracion configuracion = null;
	
	private Controlador controlador;
	private ResourceBundle resourceBundle;	
	private Properties prop;
	public static Configuracion getConfiguracion() {
		if (configuracion == null) {
			configuracion = new Configuracion();
		}
		return configuracion;
	}

	private Configuracion() {
	
		prop = new Properties();
		InputStream input;
		try {
			input = new FileInputStream("config.properties");
			prop.load(input);
			
			Locale.setDefault(new Locale(prop.getProperty("language_1"), prop.getProperty("country_1")));
			resourceBundle = ResourceBundle.getBundle(prop.getProperty("labels"));

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public Controlador getControlador() {
		return controlador;
	}
	
	public void cambiarIdioma(int n) {
		Properties prop = new Properties();
		InputStream input;
		try {
			input = new FileInputStream("config.properties");
			prop.load(input);
			
			Locale.setDefault(new Locale(prop.getProperty("language_" + n), prop.getProperty("country_" + n)));
			resourceBundle = ResourceBundle.getBundle(prop.getProperty("labels"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

