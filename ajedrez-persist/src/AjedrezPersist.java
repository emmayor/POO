import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Scanner;
import java.text.SimpleDateFormat;  
import java.util.Date;  

public class AjedrezPersist {

    enum tipoPiezas {None, Reina, Rey, Torre, Alfil, Caballo, Peon}
    enum materiales {None, Plastico, Madera}
    enum colores {None, Blanco, Negro}

    private static Scanner scanner = new Scanner(System.in);

    public static String calcularPosicion(int i, int j){
        // Toma valores usados como indices de matrices y devuelve coordenadas de ajedrez
        return String.valueOf(i+1)+(char)(65+j);
    }

    private static Pieza[] instanciarPiezas(String color, String material) {
        // Instancia todas las 16 piezas de un mismo color y material
        int i = 0;
        int indice = 0;
        Pieza piezas[];
        piezas = new Pieza[16];

        int filaOtros = 0;
        int filaPeon = 1;
        int colRey = 4;
        int colReina = 3;

        if (color == "Negro") {
            filaOtros = 7;
            filaPeon = 6;
        }

        for (i = 0; i < 8; i++) {
            piezas[i] = new Peon("Ladino", "Agresor", color, material, calcularPosicion(filaPeon, i));
        }

        indice = i;

        piezas[indice] = new Torre("Directa", "Homérica", color, material, calcularPosicion(filaOtros, 0));
        indice++;
        piezas[indice] = new Torre("Directa", "Homérica", color, material, calcularPosicion(filaOtros, 7));
        indice++;

        piezas[indice] = new Caballo("Ligero", null, color, material, calcularPosicion(filaOtros, 1));
        indice++;
        piezas[indice] = new Caballo("Ligero", null, color, material, calcularPosicion(filaOtros, 6));
        indice++;

        piezas[indice] = new Alfil("Oblicuo", "Sesgo", color, material, calcularPosicion(filaOtros, 2));
        indice++;
        piezas[indice] = new Alfil("Oblicuo", "Sesgo", color, material, calcularPosicion(filaOtros, 5));
        indice++;

        piezas[indice] = new Rey("Tenue", "Postrero", color, material, calcularPosicion(filaOtros, colRey));
        indice++;
        piezas[indice] = new Reina("Armada", "Encarnizada", color, material, calcularPosicion(filaOtros, colReina));

        return piezas;
    }

    public static ResultSet query(String query, Connection con){
        ResultSet resultado = null;
        Statement sentencia = null;
        try {
            sentencia = con.createStatement();
            resultado = sentencia.executeQuery(query);
        } catch (SQLException error) {
            System.err.println("ERROR: No se pudo hacer la consulta");
        } finally {
            try {
                if (resultado != null) resultado.close();
                if (sentencia != null) sentencia.close();
            } catch (SQLException error){
                System.err.println("ERROR: Fallo al cerrar el resultado de la consulta");
            }
        }
        return resultado;
    }

    public static String generarInsertPieza(Pieza pieza) {
        String tipo                 = pieza.getClass().getSimpleName();
        String material             = pieza.getMaterial();
        String color                = pieza.getColor();
        String posicion             = pieza.getPosicion();
        String capDesplazamiento    = pieza.getCapDesplazamiento();
        String conducta             = pieza.getConducta();
        
        int idTipoPieza = tipoPiezas.valueOf(tipo).ordinal(); 
        int idMaterial  = materiales.valueOf(material).ordinal(); 
        int idColor     = colores.valueOf(color).ordinal();
        
        SimpleDateFormat formatter  = new SimpleDateFormat("YYYY-MM-d");  
        Date fechaCreacionRaw       = new Date();  
        String fechaCreacion        = (formatter.format(fechaCreacionRaw));  

        String descripcion = tipo+" "+color+" de "+material+" "+pieza.getCapDesplazamiento();
        if (tipo != "Caballo") {
            descripcion = descripcion+" y "+pieza.getConducta();
        }


        String queryAttributes = "INSERT INTO `pieza` (`Descripcion`,`idColor`,`idTipoPieza`,`idTamanio`,`idMaterial`,`Posicion`,`Capacidad_Desplazamiento`,`Conducta`,`Velocidad`,`Capacidad_Ataque`,`Fecha_Creacion`) ";

        String queryValues = "VALUES ('"+descripcion+"',"+idColor+","+idTipoPieza+",1,"+idMaterial+",'"+posicion+"','"+capDesplazamiento+"','"+conducta+"',NULL,NULL,'"+fechaCreacion+"');";
        return queryAttributes+queryValues;
    }

    public static void insertarPieza(Pieza pieza, Connection con) {
        String queryStatement = generarInsertPieza(pieza);
        query(queryStatement, con);
    }

    public static void imprimirPiezas(ResultSet tablaPiezas){
        System.out.format("%-10s%-45s%-10s%-12s%-12s%-12s%-12s%-18s%-12s%-12s%-12s%-12s\n","idPieza","Descripcion","idColor","idTipoPieza","idTamanio","idMaterial","posicion","capDesplazamiento", "Conducta", "velocidad", "capAtaque","fechaCreacion");
        SimpleDateFormat formatter  = new SimpleDateFormat("YYYY-MM-d");  
        try {
            while(tablaPiezas.next()){
                int idPieza     = tablaPiezas.getInt("idPieza");
                int idColor     = tablaPiezas.getInt("idColor");
                int idTipoPieza = tablaPiezas.getInt("idTipoPieza");
                int idTamanio   = tablaPiezas.getInt("idTamanio");
                int idMaterial  = tablaPiezas.getInt("idMaterial");
                
                String descripcion       = tablaPiezas.getString("Descripcion");
                String posicion          = tablaPiezas.getString("Posicion");
                String capDesplazamiento = tablaPiezas.getString("Capacidad_Desplazamiento");
                String conducta          = tablaPiezas.getString("Conducta");
                String velocidad         = tablaPiezas.getString("Velocidad");
                String capAtaque         = tablaPiezas.getString("Capacidad_Ataque");
                
                Date fechaCreacionRaw       = tablaPiezas.getDate("Fecha_Creacion");
                String fechaCreacion = null;  
                if (fechaCreacionRaw != null){
                    fechaCreacion = (formatter.format(fechaCreacionRaw));  
                }
                
                System.out.format("%-10d%-45s%-10d%-12d%-12d%-12d%-12s%-18s%-12s%-12s%-12s%-12s\n",idPieza,descripcion,idColor,idTipoPieza,idTamanio,idMaterial,posicion,capDesplazamiento,conducta,velocidad,capAtaque,fechaCreacion);       	         		     
            }    
        } catch (SQLException error) {
            System.err.println("ERROR: No se pudo obtener la información");
        }
    }

    public static void main(String[] args) {
        AccesoDatos accesoBD = null;
        Connection con = null;
        accesoBD = new AccesoDatos("localhost","admin","admin",3306,"ajedrez");
        con = accesoBD.getConexion();
        Pieza[] piezasBlancas = instanciarPiezas("Blanco", "Madera");
        Pieza[] piezasNegras = instanciarPiezas("Negro", "Plastico");
        int input = -1;
        while (input != 0){
            System.out.println("MENU PRINCIPAL:");
            System.out.println("Seleccione una opción");
            System.out.println("1: Añadir piezas a la base de datos");
            System.out.println("2: Listar piezas de la base de datos");
            System.out.println("3: Quitar piezas de la base de datos");
            System.out.println("0: Salir");
            input = scanner.nextInt(); 
            switch(input){
                case 1:
                    int i;
                    for (i = 0; i < 16; i++){
                        insertarPieza(piezasBlancas[i], con);
                    }
                    for (i = 0; i < 16; i++){
                        insertarPieza(piezasNegras[i], con);
                    }
                    System.out.println("Las piezas fueron agregadas a la base de datos!");
                    break;
                case 2:
                    ResultSet tablaPiezas = query("SELECT * FROM pieza", con);
                    imprimirPiezas(tablaPiezas);
                    break;
                case 3: 
                    query("TRUNCATE TABLE pieza", con);
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
