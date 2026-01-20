package colectivos.aplicacion;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import colectivos.GUI.*;
import colectivos.negocio.Calculo;
import colectivos.negocio.Empresa;
import colectivos.negocio.Subject;

public class Aplicacion {

	public static void main(String[] args) throws IOException {
		ExecutorService es = Executors.newFixedThreadPool(1);
		try {
			FrameCarga fc = new FrameCarga();
			es.execute(fc);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		es.shutdown();
		
		Empresa empresa = Empresa.getEmpresa();
		Configuracion configuracion = Configuracion.getConfiguracion();
		Controlador controlador = new Controlador();
		MenuPrincipal menuPrincipal = new MenuPrincipal();
		Calculo calculo = new Calculo();
	
		Subject subject = new Subject();
		calculo.setSubject(subject);
		empresa.setSubject(subject);
		
		configuracion.setControlador(controlador);
		calculo.setControlador(controlador);
		
		controlador.setConfiguracion(configuracion);
		controlador.setRb(configuracion.getResourceBundle());
		controlador.setEmpresa(empresa);
		controlador.setCalculo(calculo);
		controlador.setInterfaz(menuPrincipal);
		
		empresa.setControlador(controlador);
		controlador.iniciarCalculo();
		menuPrincipal.setControlador(controlador);
		try {
			Thread.sleep(2300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		menuPrincipal.iniciar();
		
	}
}