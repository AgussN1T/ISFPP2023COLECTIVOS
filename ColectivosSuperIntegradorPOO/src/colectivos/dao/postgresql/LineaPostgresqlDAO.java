package colectivos.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import colectivos.conexion.BDConexion;
import colectivos.dao.LineaDAO;
import colectivos.modelo.Linea;
import colectivos.modelo.Parada;

public class LineaPostgresqlDAO implements LineaDAO {

	@Override
	public void insertar(Linea linea) {
		 Connection con = null;
		    PreparedStatement pstmLinea = null;
		    PreparedStatement pstmParadas = null;

		    try {
		        con = BDConexion.getConnection();
		        con.setAutoCommit(false); // Transacción

		        // 1. Insertar línea
		        String sqlLinea = "INSERT INTO public.linea (id, comienza, finaliza, frecuencia) VALUES (?, ?, ?, ?)";
		        pstmLinea = con.prepareStatement(sqlLinea);
		        pstmLinea.setString(1, linea.getId());
		        pstmLinea.setInt(2, linea.getComienza());
		        pstmLinea.setInt(3, linea.getFinaliza());
		        pstmLinea.setInt(4, linea.getFrecuencia());
		        pstmLinea.executeUpdate();
		        pstmLinea.close();

		        // 2. Insertar paradas en linea_parada
		        String sqlParada = "INSERT INTO public.linea_parada (linea_id, parada_id, orden) VALUES (?, ?, ?)";
		        pstmParadas = con.prepareStatement(sqlParada);

		        List<Parada> paradas = linea.getParadas();
		        for (int i = 0; i < paradas.size(); i++) {
		            Parada parada = paradas.get(i);
		            pstmParadas.setString(1, linea.getId());
		            pstmParadas.setInt(2, parada.getId());
		            pstmParadas.setInt(3, i); // orden comienza en 0
		            pstmParadas.addBatch();
		        }

		        pstmParadas.executeBatch();
		        con.commit();

		    } catch (Exception ex) {
		        ex.printStackTrace();
		        try {
		            if (con != null) con.rollback();
		        } catch (Exception rollbackEx) {
		            rollbackEx.printStackTrace();
		        }
		        throw new RuntimeException(ex);
		    } finally {
		        try {
		            if (pstmLinea != null) pstmLinea.close();
		            if (pstmParadas != null) pstmParadas.close();
		            if (con != null) con.setAutoCommit(true);
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
		    }

	}

	@Override
	public void actualizar(Linea linea) {
		 Connection con = null;
		    PreparedStatement pstm = null;

		    try {
		        con = BDConexion.getConnection();
		        con.setAutoCommit(false); // Begin transaction

		        // 1. Actualizar datos de la línea
		        String sqlLinea = "UPDATE linea SET comienza = ?, finaliza = ?, frecuencia = ? WHERE id = ?";
		        pstm = con.prepareStatement(sqlLinea);
		        pstm.setInt(1, linea.getComienza());
		        pstm.setInt(2, linea.getFinaliza());
		        pstm.setInt(3, linea.getFrecuencia());
		        pstm.setString(4, linea.getId());
		        pstm.executeUpdate();
		        pstm.close();

		        // 2. Eliminar paradas anteriores asociadas a la línea
		        String deleteParadas = "DELETE FROM linea_parada WHERE linea_id = ?";
		        pstm = con.prepareStatement(deleteParadas);
		        pstm.setString(1, linea.getId());
		        pstm.executeUpdate();
		        pstm.close();

		        // 3. Insertar nuevas paradas
		        String insertParada = "INSERT INTO linea_parada (linea_id, parada_id, orden) VALUES (?, ?, ?)";
		        pstm = con.prepareStatement(insertParada);

		        List<Parada> paradas = linea.getParadas();
		        for (int i = 0; i < paradas.size(); i++) {
		            pstm.setString(1, linea.getId());
		            pstm.setInt(2, paradas.get(i).getId());
		            pstm.setInt(3, i);
		            pstm.addBatch();
		        }

		        pstm.executeBatch();
		        con.commit();

		    } catch (Exception ex) {
		        ex.printStackTrace();
		        try {
		            if (con != null) con.rollback(); // Rollback si hay error
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		        throw new RuntimeException(ex);
		    } finally {
		        try {
		            if (pstm != null) pstm.close();
		            if (con != null) con.setAutoCommit(true);
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
		    }

	}

	@Override
	public void borrar(Linea linea) {
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = BDConexion.getConnection();
			String sql = "";
			sql += "DELETE FROM public.linea WHERE id = ?";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, linea.getId());
			pstm.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new RuntimeException(ex);
			}
		}


	}

	@Override
	public List<Linea> buscarTodos() {
		List<Linea> lineas = new ArrayList<Linea>();
	    Connection con = null;
	    PreparedStatement pstmLinea = null;
	    PreparedStatement pstmParadas = null;
	    ResultSet rsLinea = null;
	    ResultSet rsParadas = null;

	    try {
	        con = BDConexion.getConnection();

	        // 1. Traer todas las líneas
	        String sqlLinea = "SELECT id, comienza, finaliza, frecuencia FROM public.linea";
	        pstmLinea = con.prepareStatement(sqlLinea);
	        rsLinea = pstmLinea.executeQuery();

	        while (rsLinea.next()) {
	            String id = rsLinea.getString("id");
	            int comienza = rsLinea.getInt("comienza");
	            int finaliza = rsLinea.getInt("finaliza");
	            int frecuencia = rsLinea.getInt("frecuencia");

	            Linea linea = new Linea(id, comienza, finaliza, frecuencia);

	            String sqlParadas = """
	                SELECT p.id, p.direccion
	                FROM public.linea_parada lp
	                JOIN public.parada p ON lp.parada_id = p.id
	                WHERE lp.linea_id = ?
	                ORDER BY lp.orden ASC
	            """;

	            pstmParadas = con.prepareStatement(sqlParadas);
	            pstmParadas.setString(1, id);
	            rsParadas = pstmParadas.executeQuery();

	            while (rsParadas.next()) {
	                int paradaId = rsParadas.getInt("id");
	                String direccion = rsParadas.getString("direccion");

	                Parada parada = new Parada(paradaId, direccion);
	                linea.getParadas().add(parada);
	            }

	            rsParadas.close();
	            pstmParadas.close();

	            lineas.add(linea);
	        }

	    } catch (Exception ex) {
	        ex.printStackTrace();
	        throw new RuntimeException(ex);
	    } finally {
	        try {
	            if (rsParadas != null) rsParadas.close();
	            if (rsLinea != null) rsLinea.close();
	            if (pstmParadas != null) pstmParadas.close();
	            if (pstmLinea != null) pstmLinea.close();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            throw new RuntimeException(ex);
	        }
	    }

	    return lineas;
	}

}
