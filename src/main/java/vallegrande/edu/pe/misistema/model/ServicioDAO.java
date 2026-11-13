package vallegrande.edu.pe.misistema.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicioDAO {

    // CONSULTAR
    public List<Servicio> listar() throws SQLException {
        List<Servicio> lista = new ArrayList<>();
        String sql = "SELECT * FROM servicios ORDER BY id_servicio";
        try (Connection c = Conexion.getConexion();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Servicio(
                        rs.getInt("id_servicio"),
                        rs.getString("nombre_servicio"),
                        rs.getString("descripcion"),
                        rs.getInt("duracion_minutos"),
                        rs.getDouble("precio"),
                        rs.getString("especialista"),
                        rs.getString("estado")));
            }
        }
        return lista;
    }

    // REGISTRAR
    public void insertar(Servicio s) throws SQLException {
        String sql = "INSERT INTO servicios (nombre_servicio, descripcion, duracion_minutos, precio, especialista, estado) VALUES (?,?,?,?,?,?)";
        try (Connection c = Conexion.getConexion();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, s.getNombreServicio());
            ps.setString(2, s.getDescripcion());
            ps.setInt(3, s.getDuracionMinutos());
            ps.setDouble(4, s.getPrecio());
            ps.setString(5, s.getEspecialista());
            ps.setString(6, s.getEstado());
            ps.executeUpdate();
        }
    }

    // ACTUALIZAR
    public void actualizar(Servicio s) throws SQLException {
        String sql = "UPDATE servicios SET nombre_servicio=?, descripcion=?, duracion_minutos=?, precio=?, especialista=?, estado=? WHERE id_servicio=?";
        try (Connection c = Conexion.getConexion();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, s.getNombreServicio());
            ps.setString(2, s.getDescripcion());
            ps.setInt(3, s.getDuracionMinutos());
            ps.setDouble(4, s.getPrecio());
            ps.setString(5, s.getEspecialista());
            ps.setString(6, s.getEstado());
            ps.setInt(7, s.getIdServicio());
            ps.executeUpdate();
        }
    }

    // ELIMINAR
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM servicios WHERE id_servicio=?";
        try (Connection c = Conexion.getConexion();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}