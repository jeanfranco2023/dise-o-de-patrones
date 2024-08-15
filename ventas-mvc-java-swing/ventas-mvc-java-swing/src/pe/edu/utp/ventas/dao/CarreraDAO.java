package pe.edu.utp.ventas.dao;

import java.util.List;
import pe.edu.utp.ventas.entity.Carrera;

/**
 *
 * @author Docente
 */
public interface CarreraDAO {
    public boolean createCarrera(Carrera c);
    public boolean updateCarrera(Carrera c);
    public boolean deleteCarrera(Carrera c);
    public boolean readCarrera(Carrera c);
    public List<Carrera> readAllCarrera();
}
