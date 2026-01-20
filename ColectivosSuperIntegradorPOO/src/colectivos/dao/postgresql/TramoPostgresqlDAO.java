package colectivos.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import colectivos.conexion.BDConexion;
import colectivos.dao.TramoDAO;
import colectivos.modelo.Parada;
import colectivos.modelo.Tramo;

public class TramoPostgresqlDAO implements TramoDAO {

	@Override
	public void insertar(Tramo tramo) {
		Connection con = null;
		PreparedStatement pstm = null;

		try {
			con = BDConexion.getConnection();
			String sql = "INSERT INTO public.tramo (inicio_id, fin_id, tiempo, tipo) VALUES (?, ?, ?, ?)";
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, tramo.getInicio().getId());
			pstm.setInt(2, tramo.getFin().getId());
			pstm.setInt(3, tramo.getTiempo());
			pstm.setInt(4, tramo.getTipo());
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
	public void actualizar(Tramo tramo) {
		Connection con = null;
		PreparedStatement pstm = null;

		try {
			con = BDConexion.getConnection();
			String sql = "UPDATE public.tramo SET tiempo = ?, tipo = ? WHERE inicio_id = ? AND fin_id = ?";
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, tramo.getTiempo());
			pstm.setInt(2, tramo.getTipo());
			pstm.setInt(3, tramo.getInicio().getId());
			pstm.setInt(4, tramo.getFin().getId());
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
	public void borrar(Tramo tramo) {
		Connection con = null;
		PreparedStatement pstm = null;

		try {
			con = BDConexion.getConnection();
			String sql = "DELETE FROM public.tramo WHERE inicio_id = ? AND fin_id = ?";
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, tramo.getInicio().getId());
			pstm.setInt(2, tramo.getFin().getId());
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
	public List<Tramo> buscarTodos() {
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Tramo> tramos = new ArrayList<>();

		try {
			con = BDConexion.getConnection();
			String sql = """
					    SELECT
					        t.inicio_id, pi.direccion AS direccion_inicio,
					        t.fin_id, pf.direccion AS direccion_fin,
					        t.tiempo, t.tipo
					    FROM public.tramo t
					    JOIN public.parada pi ON t.inicio_id = pi.id
					    JOIN public.parada pf ON t.fin_id = pf.id
					""";

			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();

			while (rs.next()) {
				int inicioId = rs.getInt("inicio_id");
				String direccionInicio = rs.getString("direccion_inicio");

				int finId = rs.getInt("fin_id");
				String direccionFin = rs.getString("direccion_fin");

				int tiempo = rs.getInt("tiempo");
				int tipo = rs.getInt("tipo");

				Parada inicio = new Parada(inicioId, direccionInicio);
				Parada fin = new Parada(finId, direccionFin);

				Tramo tramo = new Tramo(inicio, fin, tiempo, tipo);
				tramos.add(tramo);
			}

			return tramos;

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
