import java.sql.Connection;
import java.awt.EventQueue;

public class App {

    public static void main (String[] args) {

        AccesoDatos accesoBD = new AccesoDatos("localhost","admin","admin",3306,"cotizador");
        Connection con = accesoBD.getConexion();
        ControllerCotizaciones controller = new ControllerCotizaciones(con);
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CotizadorGrafico gui = new CotizadorGrafico(controller);
					gui.iniciarBucle();
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
		});
    }
}
