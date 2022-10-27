import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws Exception {
        mostrarPiezasBD();
    }

    public static void mostrarPiezasBD()
	{
		//Objeto para ejecutar la consulta en la base de datos
    	AccesoDatos accesoBD = null;
    	Connection con = null;
    	Statement sentencia = null;
		ResultSet rs = null;
		
		// Se crea la consulta usando lenguaje SQL Estandar y almacenando en la variable query
		String query = "SELECT * FROM tipopieza";
        //En este bloque se ejecutan las sentencias necesarias para acceder a la BD y obtener la informacion
        try {
        	// Instancio un objeto de acceso a datos
        	accesoBD = new AccesoDatos("localhost","admin","admin",3306,"ajedrez");
			
            //Obtener la conexion para poder generar la sentencia de consulta
            con = accesoBD.getConexion();
            sentencia = con.createStatement();
            
            //Ejecuta la consulta y almacena el resultado en rs 
            rs  = sentencia.executeQuery(query);
           
            // Procesa el resultSet y muestra la informacion obtenida desde la BD
            while (rs.next()){
            	
				int idPieza = rs.getInt("idTipoPieza");
				System.out.println("Pieza Nro: " + String.valueOf(idPieza)
				+ " tipo " + rs.getString("Descripciondepieza")	);            	         		
            	            			
			}    
        } catch (SQLException error) {
            System.err.println("ERROR: No se pudieron cargar los datos.");
        }
       	finally {
        	try	{
        		// Cierra el ResultSet
        		if (rs!= null) rs.close();
        		// Cierra la sentencia
        		if (sentencia!= null) sentencia.close();
        		// Cierra la conexion
        		if (con != null) con.close();
        		
        	}catch (SQLException error)
        	{
        		System.err.println("ERROR: No se pudo cerrar la conexión.");
        	}
        }
	}
	
}
