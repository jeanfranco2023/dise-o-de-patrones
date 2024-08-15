package pe.edu.utp.ventas.run;

import pe.edu.utp.ventas.controller.CarreraController;
import pe.edu.utp.ventas.daoImpl.CarreraDaoImpl;
import pe.edu.utp.ventas.entity.Carrera;
import pe.edu.utp.ventas.vista.CarreraForm;
import pe.edu.utp.ventas.dao.CarreraDAO;

/**
 *
 * @author Docente
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Carrera carrera = new Carrera();
        CarreraDAO carreraDAO = new CarreraDaoImpl();
        CarreraForm carreraForm = new CarreraForm();

        CarreraController carreraController = new CarreraController(carrera, carreraDAO, carreraForm);
        carreraController.iniciar();
        carreraForm.setLocationRelativeTo(null);
        carreraForm.setVisible(true);
    }
}
