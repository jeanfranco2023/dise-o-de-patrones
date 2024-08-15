package pe.edu.utp.ventas.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Docente
 */
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Carrera {

    private int idCarrera;
    private String codigo;
    private String nombre;
    private String curso;
    private String docente;
    private int creditosCurso;
    private int numeroDeCiclo;
    private int horasDelCurso;
}
