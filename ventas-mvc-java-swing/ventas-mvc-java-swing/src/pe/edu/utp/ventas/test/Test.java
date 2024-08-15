package pe.edu.utp.ventas.test;

import pe.edu.utp.ventas.config.Conexion;

/**
 *
 * @author Docente
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Conexion cx = new Conexion();
        if(cx.getConexion()!=null){
            System.out.println("CONECTADO");
        }else{
            System.out.println("NO CONECTADO");
        }
    }
    
}
