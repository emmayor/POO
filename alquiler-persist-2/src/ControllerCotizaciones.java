import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ControllerCotizaciones {
    enum tipoVehiculo {None, Auto, Minibus, Camion, Furgoneta}

    private Connection con;

    public Connection getConnection() {
        return con;
    }

    public void closeConnection() {
        try {
            if (con != null) { 
                con.close();
            }
        } catch (SQLException error) {
            System.out.println("ERROR: No se pudo cerrar la conexión.");
            error.printStackTrace();
        }
    }

    private void insertCotizacion(Vehiculo vehiculo) {
        // COLUMNAS: idTipoVehiculo, cantidadDias, precioCotizacion Fecha_Creacion
        String insert = "INSERT INTO `cotizacion` (`idTipoVehiculo`, `cantidadDias`, `precioCotizacion`, `Fecha_Creacion`) VALUES (?,?,?,?);";
        int idTipoVehiculo = tipoVehiculo.valueOf(vehiculo.getClass().getSimpleName()).ordinal();
        java.util.Date fechaCreacionRaw = new java.util.Date();
        java.sql.Date fechaCreacion = new java.sql.Date(fechaCreacionRaw.getTime());

        try {
            PreparedStatement statement = con.prepareStatement(insert);
            statement.setInt(1,idTipoVehiculo);
            statement.setInt(2,vehiculo.getCantDias());
            statement.setDouble(3,vehiculo.getPrecioAlquiler());
            statement.setDate(4,fechaCreacion);
            statement.execute();
            System.out.println("Cotización cargada");


        } catch (SQLException error) {
            System.out.println("ERROR: No se pudo cargar la cotización.");
            error.printStackTrace();
        }
    }

    private Vehiculo instanciarVehiculo(int tipoVehiculo, int cantDias) {
        Vehiculo vehiculo = null;
        switch(tipoVehiculo) {
            case 0:
                vehiculo = new Auto(cantDias, 4);
                break;
            case 1:
                vehiculo = new Minibus(cantDias, 20);
                break;
            case 2:
                vehiculo = new Furgoneta(cantDias, 2.5f);
                break;
            case 3:
                vehiculo = new Camion(cantDias, 5);
                break;
        }
        return vehiculo;
    }

    public ResultSet fetchCotizaciones() {
        ResultSet rs = null;
        try {
            String select = "SELECT idCotizacion, V.tipoVehiculo, cantidadDias, precioCotizacion, Fecha_Creacion FROM cotizacion AS CT INNER JOIN vehiculo AS V ON V.idVehiculo = CT.idTipoVehiculo;";
            PreparedStatement statement = con.prepareStatement(select);
            rs = statement.executeQuery();
        } catch (SQLException error) {
            System.out.println("ERROR: No se pudo obtener el listado de cotizaciones");
            error.printStackTrace();
        }
        return rs;
    }

    public void truncateCotizacion() {
        try {
            PreparedStatement statement = con.prepareStatement("TRUNCATE TABLE `cotizacion`;");
            statement.executeQuery();
        } catch (SQLException error) {
            System.out.println("ERROR: No se pudieron eliminar los registros");
            error.printStackTrace();
        }
    }

    public void agregarCotizacion(int tipoVehiculo, int cantDias){
        Vehiculo vehicle = instanciarVehiculo(tipoVehiculo,cantDias);
        insertCotizacion(vehicle);
    }

    public ControllerCotizaciones(Connection con) {
        this.con = con;
    }
}

