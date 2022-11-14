import java.sql.Connection;
import java.sql.SQLException;

public class App {

    public static void main (String[] args) {

        //AccesoDatos accesoBD = new AccesoDatos("localhost","admin","admin",3306,"cotizador");
        //Connection con = accesoBD.getConexion();
        //ControllerCotizaciones controller = new ControllerCotizaciones(con);
        InterfaceCotizaciones gui = new InterfaceCotizaciones(null);

        // try {
        //     if (con != null){
        //         con.close();
        //     }
        // } catch (SQLException error) {
        //     System.out.println("ERROR: No se pudo cerrar la conexión");
        // }
    }
}
