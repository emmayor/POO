import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Scanner;

public class ListadoCotizaciones {
    enum tipoVehiculo {None, Auto, Minibus, Camion, Furgoneta}

    private static Scanner scanner = new Scanner(System.in);

    private static void insertCotizacion(Vehiculo vehiculo, Connection con) {
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

    private static ResultSet selectCotizacion(Connection con) {
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

    private static void imprimirListado(ResultSet cotizaciones) {
        System.out.format("%-15s%-15s%-15s%-22s%-15s\n","idCotizacion","tipoVehiculo","cantidadDias","precioCotizacion","Fecha_Creacion");
        try {
            while (cotizaciones.next()){
                int idCotizacion = cotizaciones.getInt("idCotizacion");
                String tipoVehiculo = cotizaciones.getString("tipoVehiculo");
                int cantidadDias = cotizaciones.getInt("cantidadDias");
                double precioCotizacion = cotizaciones.getDouble("precioCotizacion");
                String fechaCreacion = cotizaciones.getDate("Fecha_Creacion").toString();

                System.out.format("%-15d%-15s%-15d%-22.02f%-15s\n",idCotizacion,tipoVehiculo,cantidadDias,precioCotizacion,fechaCreacion);
            }
        } catch (SQLException error) {
            System.out.println("ERROR: No se pudieron imprimir uno o algunos registros");
            error.printStackTrace();
        }
    }

    private static void truncateCotizacion(Connection con) {
        try {
            PreparedStatement statement = con.prepareStatement("TRUNCATE TABLE `cotizacion`;");
            statement.executeQuery();
        } catch (SQLException error) {
            System.out.println("ERROR: No se pudieron eliminar los registros");
            error.printStackTrace();
        }
    }

    private static Vehiculo cotizarAlquiler(int tipoVehiculo) {
        int cantDias = 0;
        Vehiculo vehiculo = null;
        while(cantDias < 1) {
            System.out.println("Ingrese la cantidad de días del alquiler:");
            cantDias = scanner.nextInt(); 
        }
            switch(tipoVehiculo) {
                case 1:
                    vehiculo = new Auto(cantDias, 4);
                    break;
                case 2:
                    vehiculo = new Minibus(cantDias, 20);
                    break;
                case 3:
                    vehiculo = new Furgoneta(cantDias, 2.5f);
                    break;
                case 4:
                    vehiculo = new Camion(cantDias, 5);
                    break;
            }
        
        return vehiculo;
    }

    private static int seleccionarVehiculo(){
        int opcion = -1;
        while (opcion < 1 || opcion > 4) {
            System.out.println("Seleccione el tipo de vehículo");
            System.out.println("1: Auto (4 plazas)");
            System.out.println("2: Minibus (20 plazas)");
            System.out.println("3: Furgoneta (2.5 Toneladas)");
            System.out.println("4: Camión (5 Toneladas)");
            opcion = scanner.nextInt(); 
        }
        return opcion;
    }

    private static void mostrarInterfaz(ResultSet cotizaciones) {
        EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazCotizaciones frame = new InterfazCotizaciones(cotizaciones);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    }

    public static void main (String[] args) {
        AccesoDatos accesoBD = null;
        Connection con = null;
        accesoBD = new AccesoDatos("localhost","admin","admin",3306,"cotizador");
        con = accesoBD.getConexion();
        int input = -1;
        while (input != 0){
            System.out.println("COTIZADOR - MENU PRINCIPAL:");
            System.out.println("Seleccione una opción");
            System.out.println("1: Añadir cotización a la base de datos");
            System.out.println("2: Mostrar listado de cotizaciones");
            System.out.println("3: Mostrar listado de cotizaciones con interfaz gráfica");
            System.out.println("4: Quitar cotizaciones de la base de datos");
            System.out.println("0: Salir");
            input = scanner.nextInt(); 
            switch(input){
                case 1:
                    int tipoVehiculo = seleccionarVehiculo();
                    Vehiculo vehiculo = cotizarAlquiler(tipoVehiculo);
                    insertCotizacion(vehiculo, con);
                    break;
                case 2:
                    imprimirListado(selectCotizacion(con));
                    break;
                case 3:
                    mostrarInterfaz(selectCotizacion(con));
                    break;
                case 4: 
                    truncateCotizacion(con);
                    break;
            }
        }
        try {
            if (con != null){
                con.close();
            }
        } catch (SQLException error) {
            System.out.println("ERROR: No se pudo cerrar la conexión");
        }
        }
    }

