package colectivos.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import colectivos.dao.ParadaDAO;
import colectivos.modelo.Linea;
import colectivos.modelo.Parada;
import colectivos.conexion.BDConexion;

public class ParadaPostgresqlDAO implements ParadaDAO {

	@Override
	public void insertar(Parada parada) {
		Connection con = null;
		PreparedStatement pstm = null;
		try {
			con = BDConexion.getConnection();
			String sql = "INSERT INTO public.parada (id, direccion) VALUES (?, ?)";
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, parada.getId());
			pstm.setString(2, parada.getDireccion());
			pstm.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		} finally {
			try {
				if (pstm != null)
					pstm.close();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new RuntimeException(ex);
			}
		}

	}

	@Override
	public void actualizar(Parada parada) {
		Connection con = null;
		PreparedStatement pstm = null;

		try {
			con = BDConexion.getConnection();
			String sql = "UPDATE public.parada SET direccion = ? WHERE id = ?";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, parada.getDireccion());
			pstm.setInt(2, parada.getId());
			pstm.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		} finally {
			try {
				if (pstm != null)
					pstm.close();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new RuntimeException(ex);
			}
		}

	}

	@Override
	public void borrar(Parada parada) {
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = BDConexion.getConnection();
			String sql = "";
			sql += "DELETE FROM public.parada WHERE id = ?";
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, parada.getId());
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
	public List<Parada> buscarTodos() {

		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Parada> paradas = new ArrayList<>();

		try {
			con = BDConexion.getConnection();

			// Consulta que une paradas con lineas (si una parada no tiene linea, igual
			// aparece con lineas null)
			String sql = "SELECT p.id AS parada_id, p.direccion, lp.linea_id " + "FROM public.parada p "
					+ "LEFT JOIN public.linea_parada lp ON p.id = lp.parada_id " + "ORDER BY p.id";

			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();

			// Map para evitar crear varias veces la misma parada
			Map<Integer, Parada> mapaParadas = new LinkedHashMap<>();

			while (rs.next()) {
				int paradaId = rs.getInt("parada_id");
				String direccion = rs.getString("direccion");
				String lineaId = rs.getString("linea_id"); // puede ser null si no tiene línea

				Parada parada = mapaParadas.get(paradaId);
				if (parada == null) {
					parada = new Parada(paradaId, direccion);
					mapaParadas.put(paradaId, parada);
				}

				if (lineaId != null) {
					// Creamos solo la línea con id porque no tenemos más datos aquí
					Linea linea = new Linea(lineaId, 0, 0, 0); // o podés cargar mejor si querés
					parada.agregarLinea(linea);
				}
			}

			paradas.addAll(mapaParadas.values());
			return paradas;

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
	
}
