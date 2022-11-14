import java.net.InterfaceAddress;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class App {

    public static void main (String[] args) {

        AccesoDatos accesoBD = null;
        Connection con = null;
        ControllerCotizaciones controller = null;
        InterfaceCotizaciones interface = null;
        
        accesoBD = new AccesoDatos("localhost","admin","admin",3306,"cotizador");
        con = accesoBD.getConexion();
        controller = new ControllerCotizaciones(con);

        
    
        try {
            if (con != null){
                con.close();
            }
        } catch (SQLException error) {
            System.out.println("ERROR: No se pudo cerrar la conexión");
        }
    }
}
