package pe.edu.utp.ventas.daoImpl;

import java.util.List;
import pe.edu.utp.ventas.config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pe.edu.utp.ventas.entity.Carrera;
import pe.edu.utp.ventas.dao.CarreraDAO;

/**
 *
 * @author Docente
 */
public class CarreraDaoImpl extends Conexion implements CarreraDAO {

    @Override
    public boolean createCarrera(Carrera c) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "INSERT INTO carrera (codigo, nombre, curso, docente, creditos_curso, numero_de_ciclo, horas_del_curso) VALUES(?,?,?,?,?,?,?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, c.getCodigo());
            ps.setString(2, c.getNombre());
            ps.setString(3, c.getCurso());
            ps.setString(4, c.getDocente());
            ps.setInt(5, c.getCreditosCurso());
            ps.setInt(6, c.getNumeroDeCiclo());
            ps.setInt(7, c.getHorasDelCurso());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                System.err.println(e);
            }
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    @Override
    public boolean updateCarrera(Carrera c) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "UPDATE carrera SET codigo=?, nombre=?, curso=?, docente=?, creditos_curso=?, numero_de_ciclo=?, horas_del_curso=? WHERE id_carrera=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, c.getCodigo());
            ps.setString(2, c.getNombre());
            ps.setString(3, c.getCurso());
            ps.setString(4, c.getDocente());
            ps.setInt(5, c.getCreditosCurso());
            ps.setInt(6, c.getNumeroDeCiclo());
            ps.setInt(7, c.getHorasDelCurso());
            ps.setInt(8, c.getIdCarrera());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    @Override
    public boolean deleteCarrera(Carrera c) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "DELETE FROM carrera WHERE id_carrera=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, c.getIdCarrera());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    @Override
    public boolean readCarrera(Carrera c) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        String sql = "SELECT * FROM carrera WHERE codigo=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, c.getCodigo());
            rs = ps.executeQuery();

            if (rs.next()) {
                c.setIdCarrera(rs.getInt("id_carrera"));
                c.setCodigo(rs.getString("codigo"));
                c.setNombre(rs.getString("nombre"));
                c.setCurso(rs.getString("curso"));
                c.setDocente(rs.getString("docente"));
                c.setCreditosCurso(rs.getInt("creditos_curso"));
                c.setNumeroDeCiclo(rs.getInt("numero_de_ciclo"));
                c.setHorasDelCurso(rs.getInt("horas_del_curso"));
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    @Override
    public List<Carrera> readAllCarrera() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        String sql = "SELECT * FROM carrera";
        List<Carrera> lista = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Carrera c = new Carrera();
                c.setIdCarrera(rs.getInt("id_carrera"));
                c.setCodigo(rs.getString("codigo"));
                c.setNombre(rs.getString("nombre"));
                c.setCurso(rs.getString("curso"));
                c.setDocente(rs.getString("docente"));
                c.setCreditosCurso(rs.getInt("creditos_curso"));
                c.setNumeroDeCiclo(rs.getInt("numero_de_ciclo"));
                c.setHorasDelCurso(rs.getInt("horas_del_curso"));
                lista.add(c);
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        return lista;
    }

}
