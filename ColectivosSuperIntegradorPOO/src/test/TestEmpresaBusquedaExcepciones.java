package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import colectivos.aplicacion.Controlador;
import colectivos.modelo.Linea;
import colectivos.modelo.Parada;
import colectivos.modelo.Tramo;
import colectivos.negocio.*;

class TestEmpresaBusquedaExcepciones {
	Empresa empresa;
	Controlador controlador;

	@BeforeEach
	void setUp() throws Exception {
		empresa = Empresa.getEmpresa();
	}

	@Test
	public void testLineaExcepciones() {
		assertThrows(LineaExistenteException.class, () -> empresa.agregarLinea(new Linea("L1I",1,1,1)));
		assertThrows(LineaInexistenteException.class, () -> empresa.modificarLinea(new Linea("L800I",1,1,1)));
		assertThrows(LineaInexistenteException.class, () -> empresa.borrarLinea(new Linea("L800I",1,1,1)));
		assertThrows(LineaInexistenteException.class, () -> empresa.buscarLinea(new Linea("L800I",1,1,1)));
	}

	@Test
    public void testParadaExcepciones() {
		assertThrows(ParadaExistenteException.class, () -> empresa.agregarParada(new Parada(2,"parada Existente")));
		assertThrows(ParadaInexistenteException.class, () -> empresa.modificarParada(new Parada(700,"Parada Inexistente")));
		assertThrows(ParadaInexistenteException.class, () -> empresa.borrarParada(new Parada(700,"Parada Inexistente")));        
        assertThrows(ParadaInexistenteException.class, () -> empresa.buscarParada(new Parada(Integer.parseInt("1000"),"nombre")));        
    }
	
	@Test
	public void testTramoExcepciones() {
		assertThrows(TramoExistenteException.class, () -> empresa.agregarTramo(new Tramo(new Parada(Integer.parseInt("1"),"paradaOrigenTest"),new Parada(Integer.parseInt("6"),"paradaDestinoTest"),1,1)));
		assertThrows(TramoInexistenteException.class, () -> empresa.modificarTramo(new Tramo(new Parada(Integer.parseInt("56"),"paradaOrigenTest"),new Parada(Integer.parseInt("301"),"paradaDestinoTest"),1,1)));
		assertThrows(TramoInexistenteException.class, () -> empresa.borrarTramo(new Tramo(new Parada(Integer.parseInt("56"),"paradaOrigenTest"),new Parada(Integer.parseInt("301"),"paradaDestinoTest"),1,1)));
		assertThrows(TramoInexistenteException.class, () -> empresa.buscarTramo(new Tramo(new Parada(Integer.parseInt("32"),"paradaOrigenTest"),new Parada(Integer.parseInt("300"),"paradaDestinoTest"),1,1)));
	}
}
