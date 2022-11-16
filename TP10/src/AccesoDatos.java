import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AccesoDatos {

	private String 		host;
	private String  	usuario;
	private String  	clave;
	private int 		puerto;   
	private String 	   	bd;
	private String     	servidor; 
	private Connection 	con;

	private String getHost() {
		return host;
	}

	private void setHost(String host) {
		this.host = host;
	}

	private String getUsuario() {
		return usuario;
	}

	private void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	private String getClave() {
		return clave;
	}

	private void setClave(String clave) {
		this.clave = clave;
	}

	private int getPuerto() {
		return puerto;
	}

	private void setPuerto(int puerto) {
		this.puerto = puerto;
	}

	private String getBd() {
		return bd;
	}

	private void setBd(String bd) {
		this.bd = bd;
	}

	private String getServidor() {
		return servidor;
	}

	private void setServidor(String servidor) {
		this.servidor = servidor;
	}

	public Connection getConexion() {
		return con;
	}

	public void setConexion(Connection conexion) {
		this.con = conexion;
	}

	private void conectarBd(){
		this.setServidor("jdbc:mariadb://"+this.getHost()+":"+this.getPuerto()+"/"+this.getBd());
		try {
	        con = DriverManager.getConnection(this.getServidor()+"?user="+this.getUsuario()+"&password="+this.getClave());
	    } catch (SQLException error) {
			System.err.println("ERROR: No se pudo realizar la conexión.");
			error.printStackTrace();
			System.exit(0);
	    } 
	    System.out.println("Conectado a "+this.getBd());
	}

	public AccesoDatos(String host, String usuario, String clave, int puerto, String bd){
		this.setHost(host);
		this.setUsuario(usuario);
		this.setClave(clave);
		this.setPuerto(puerto);
		this.setBd(bd);
		conectarBd();
	}    
}
