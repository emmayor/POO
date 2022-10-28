import java.sql.Connection;
import java.sql.Statement;
import java.util.Dictionary;
import java.sql.ResultSet;
import java.sql.SQLException;



public class AjedrezPersist {


    enum tipopiezas {
        Reina,
        Rey,
        Torre,
        Alfil,
        Caballo,
        Peon,
    }

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
            colRey = 3;
            colReina = 4;
        }

        for (i = 0; i < 8; i++) {
            piezas[i] = new Peon("Ladino", "Agresor", color, material, calcularPosicion(filaPeon, i));
        }

        indice = i;

        piezas[indice] = new Torre("Directa", "Homérica", color, material, calcularPosicion(filaOtros, 0));
        indice++;
        piezas[indice] = new Torre("Directa", "Homérica", color, material, calcularPosicion(filaOtros, 7));
        indice++;

        piezas[indice] = new Caballo("Ligero", "???", color, material, calcularPosicion(filaOtros, 1));
        indice++;
        piezas[indice] = new Caballo("Ligero", "???", color, material, calcularPosicion(filaOtros, 6));
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

    public static ResultSet consulta(String query, Statement sentencia){
        ResultSet rs = null;
        try {
            rs = sentencia.executeQuery(query);
        } catch (SQLException error) {
            System.err.println("ERROR: No se pudo hacer la consulta");
        }
        return rs;
    }

    public static String generarInsertPieza(Pieza pieza) {
        String tipoPiezaString = pieza.getClass().getSimpleName();
        tipopiezas tipo = tipopiezas.tipoPiezaString
        String query = "INSERT INTO `pieza` (`idPieza`,`Descripcion`,`idColor`,`idTipoPieza`,`idMaterial`) VALUES (30,"+generarDescripcion(pieza)+","+pieza.getColor()+","+pieza.tipo()+",1,1);";
        return query;
    }

    public static void insertarPiezas(Piezas[] piezas, Statement sentencia) {
        for (int i = 0; i<16;i++){
            ResultSet resultado = consulta(generarInsertPieza(piezas[i]), sentencia);
        }
    }

    

    public static void main(String[] args) {
        try {
            AccesoDatos accesoBD = new AccesoDatos("localhost","admin","admin",3306,"ajedrez");
            Connection con = accesoBD.getConexion();
            Statement sentencia = con.createStatement();

            // Busca los datos de las piezas
            ResultSet resultado = consulta("SELECT * FROM piezas", sentencia);

            // 


        } catch (SQLException error) {
            System.err.println("ERROR: No se pudieron conectar a la base de datos.");
        }

        Pieza[] piezasBlancas = instanciarPiezas("Blanco", "Madera");
        Pieza[] piezasNegras = instanciarPiezas("Negro", "Madera");


        System.out.println("\nMovemos alguna piezas:");
        piezasNegras[4].Mover();
        piezasBlancas[12].Mover();
        piezasNegras[15].Mover();

    }
}