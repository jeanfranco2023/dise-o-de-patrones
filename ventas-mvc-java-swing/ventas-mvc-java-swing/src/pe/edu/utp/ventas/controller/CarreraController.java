package pe.edu.utp.ventas.controller;

import com.google.gson.Gson;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import pe.edu.utp.ventas.dao.CarreraDAO;
import pe.edu.utp.ventas.entity.Carrera;
import pe.edu.utp.ventas.vista.CarreraForm;

/**
 *
 * @author Docente
 */
public class CarreraController implements ActionListener {

    private Carrera modelo;
    private CarreraDAO cdao;
    private CarreraForm vista;
    private DefaultTableModel dtmodel = new DefaultTableModel();
    private JTable tabla;

    public CarreraController(Carrera modelo, CarreraDAO cdao, CarreraForm vista) {
        this.modelo = modelo;
        this.cdao = cdao;
        this.vista = vista;        
        initialize();
    }

    private void initialize() {
        this.vista.btnGuardar.addActionListener(this);
        this.vista.btnModificar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
        this.vista.btnBuscar.addActionListener(this);
    }

    public void iniciar() {
        vista.setTitle("Carreras");
        vista.setLocationRelativeTo(null);
        vista.txtId.setVisible(false);
        listar();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.btnGuardar) {
            
    modelo.setCodigo(vista.txtCodigo.getText());
    modelo.setNombre(vista.txtNombre.getText());
    modelo.setCurso(vista.txtCurso.getText());
    modelo.setDocente(vista.txtDocente.getText());
    modelo.setCreditosCurso(Integer.parseInt(vista.txtCreditosCurso.getText()));
    modelo.setNumeroDeCiclo(Integer.parseInt(vista.txtNumeroDeCiclo.getText()));
    modelo.setHorasDelCurso(Integer.parseInt(vista.txtHorasDelCurso.getText()));

            if (cdao.createCarrera(modelo)) {
                JOptionPane.showMessageDialog(null, "Registro Guardado");
                limpiar();
                limpiarTabla();
                listar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Guardar");
                limpiar();
            }
        }

        if (e.getSource() == vista.btnModificar) {
            modelo.setIdCarrera(Integer.parseInt(vista.txtId.getText()));
            modelo.setCodigo(vista.txtCodigo.getText());
            modelo.setNombre(vista.txtNombre.getText());
            modelo.setCurso(vista.txtCurso.getText());
            modelo.setDocente(vista.txtDocente.getText());
            modelo.setCreditosCurso(Integer.parseInt(vista.txtCreditosCurso.getText()));
            modelo.setNumeroDeCiclo(Integer.parseInt(vista.txtNumeroDeCiclo.getText()));
            modelo.setHorasDelCurso(Integer.parseInt(vista.txtHorasDelCurso.getText()));

            if (cdao.updateCarrera(modelo)) {
                JOptionPane.showMessageDialog(null, "Registro Modificado");
                limpiar();
                limpiarTabla();
                listar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Modificar");
                limpiar();
            }
        }

        if (e.getSource() == vista.btnEliminar) {
            modelo.setIdCarrera(Integer.parseInt(vista.txtId.getText()));

            if (cdao.deleteCarrera(modelo)) {
                JOptionPane.showMessageDialog(null, "Registro Eliminado");
                limpiar();
                limpiarTabla();
                listar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Eliminar");
                limpiar();
            }
        }

        if (e.getSource() == vista.btnBuscar) {
            modelo.setCodigo(vista.txtCodigo.getText());

            if (cdao.readCarrera(modelo)) {

                
    vista.txtId.setText(String.valueOf(modelo.getIdCarrera()));
    vista.txtCodigo.setText(modelo.getCodigo());
    vista.txtNombre.setText(modelo.getNombre());
    vista.txtCurso.setText(modelo.getCurso());
    vista.txtDocente.setText(modelo.getDocente());
    vista.txtCreditosCurso.setText(String.valueOf(modelo.getCreditosCurso()));
    vista.txtNumeroDeCiclo.setText(String.valueOf(modelo.getNumeroDeCiclo()));
    vista.txtHorasDelCurso.setText(String.valueOf(modelo.getHorasDelCurso()));

            } else {
                JOptionPane.showMessageDialog(null, "No se encontró registro");
                limpiar();
            }
        }

        if (e.getSource() == vista.btnLimpiar) {
            limpiar();
        }
    }

    public void limpiar() {
    
    vista.txtId.setText(null);
    vista.txtCodigo.setText(null);
    vista.txtNombre.setText(null);
    vista.txtCurso.setText(null);
    vista.txtDocente.setText(null);
    vista.txtCreditosCurso.setText(null);
    vista.txtNumeroDeCiclo.setText(null);
    vista.txtHorasDelCurso.setText(null);
    }

    public void listar() {
        Gson g = new Gson();
        dtmodel = (DefaultTableModel) vista.tbCarreras.getModel();
        vista.tbCarreras.setModel(dtmodel);
        List<Carrera> lista = cdao.readAllCarrera();
        System.out.println(g.toJson(lista));
        Object[] objeto = new Object[9];
        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = i + 1;
            objeto[1] = lista.get(i).getIdCarrera();
            objeto[2] = lista.get(i).getCodigo();
            objeto[3] = lista.get(i).getNombre();
            objeto[4] = lista.get(i).getCurso();
            objeto[5] = lista.get(i).getDocente();
            objeto[6] = lista.get(i).getCreditosCurso();
            objeto[7] = lista.get(i).getNumeroDeCiclo();
            objeto[8] = lista.get(i).getHorasDelCurso();
            dtmodel.addRow(objeto);
        }
        vista.tbCarreras.setModel(dtmodel);
    }

    private void limpiarTabla() {
        for (int i = 0; i < vista.tbCarreras.getRowCount(); i++) {
            dtmodel.removeRow(i);
            i = i - 1;
        }
    }
}
