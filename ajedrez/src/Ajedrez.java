public class Ajedrez {
    private static Pieza[] instanciarPiezas(String color, String material) {
        int i = 0;
        int indice = 0;
        Pieza piezas[];
        piezas = new Pieza[16];

        int filaOtros = 1;
        int filaPeon = 2;
        int colRey = 4;
        int colReina = 3;

        if (color == "Negro") {
            filaOtros = 8; 
            filaPeon = 7; 
            colRey = 3;
            colReina = 4;
        }

        for (i = 0;i<8;i++){
            Peon nuevoPeon = new Peon();
            nuevoPeon.setColor(color);
            nuevoPeon.setMaterial(material);
            nuevoPeon.setCapDesplazamiento("Ladino");
            nuevoPeon.setConducta("Agresor");
            nuevoPeon.setPosicion(calcularPosicion(filaPeon,i));
            piezas[i]=nuevoPeon;
        }

        indice = i;

        for (i = 0;i<2;i++){
            Torre nuevaTorre = new Torre();
            nuevaTorre.setColor(color);
            nuevaTorre.setMaterial(material);
            nuevaTorre.setCapDesplazamiento("Directa");
            nuevaTorre.setConducta("Homerica");
            if (i == 0) {
                nuevaTorre.setPosicion(calcularPosicion(filaOtros, 0));
            } else {
                nuevaTorre.setPosicion(calcularPosicion(filaOtros, 7));
            }
            piezas[i+indice]=nuevaTorre;
        }

        indice += i;

        for (i = 0;i<2;i++){
            Caballo nuevoCaballo = new Caballo();
            nuevoCaballo.setColor(color);
            nuevoCaballo.setMaterial(material);
            nuevoCaballo.setCapDesplazamiento("Ligero");
            nuevoCaballo.setConducta("No especificada");
            piezas[i+indice]=nuevoCaballo;
            if (i == 0) {
                nuevoCaballo.setPosicion(calcularPosicion(filaOtros, 1));
            } else {
                nuevoCaballo.setPosicion(calcularPosicion(filaOtros, 6));
            }
        }

        indice += i;

        for (i = 0;i<2;i++){
            Alfil nuevoAlfil = new Alfil();
            nuevoAlfil.setColor(color);
            nuevoAlfil.setMaterial(material);
            nuevoAlfil.setCapDesplazamiento("Oblicuo");
            nuevoAlfil.setConducta("Sesgo");
            if (i == 0) {
                nuevoAlfil.setPosicion(calcularPosicion(filaOtros, 2));
            } else {
                nuevoAlfil.setPosicion(calcularPosicion(filaOtros, 5));
            }
            piezas[i+indice]=nuevoAlfil;

        }
        
        indice += i;

        Rey nuevoRey = new Rey();
        nuevoRey.setColor(color);
        nuevoRey.setMaterial(material);
        nuevoRey.setCapDesplazamiento("Tenue");
        nuevoRey.setConducta("Postrero");
        nuevoRey.setPosicion(calcularPosicion(filaOtros, colRey));
        piezas[indice] = nuevoRey;

        indice++;

        Reina nuevaReina = new Reina();
        nuevaReina.setColor(color);
        nuevaReina.setMaterial(material);
        nuevaReina.setCapDesplazamiento("Armada");
        nuevaReina.setConducta("Encarnizada");
        nuevaReina.setPosicion(calcularPosicion(filaOtros, colReina));
        piezas[indice] = nuevaReina;

        return piezas;
    }

    public static String calcularPosicion(int m, int n){
        return String.valueOf(m)+(char)(65+n);
    }

    public static void imprimirCasilleros(Tablero tablero){
        for (int i = 7; i>=0; i--){
            for (int j = 0; j<8; j++){
                Casillero casilleroActual = tablero.getCasilleros()[i][j];
                System.out.print("("+casilleroActual.getPosicion()+", "+casilleroActual.getColor()+")");
            }
            System.out.println();
        }
    }

    public static void imprimirPieza(Pieza pieza){
        System.out.println("Nombre: "+pieza.getClass().getSimpleName()+" | Material: "+pieza.getMaterial()+" | Color: "+pieza.getColor()+" | Desplazamiento: "+pieza.getCapDesplazamiento()+" | Conducta: "+pieza.getConducta()+" | Posición: "+pieza.getPosicion());
    }

    public static void main (String[] args) {
        Tablero tablero = new Tablero("Madera");
        System.out.println("Se ha creado un tablero de 8 x 8, cuyos casilleros se presentan de la siguiente manera:");
        imprimirCasilleros(tablero);

        Pieza[] piezasBlancas = instanciarPiezas("Blanco", "Madera");
        System.out.println("Se han instanciado las piezas blancas:");
        for(int i = 0; i < 16; i++){
            imprimirPieza(piezasBlancas[i]);
        }

        Pieza[] piezasNegras = instanciarPiezas("Negro", "Madera");
        System.out.println("Se han instanciado las piezas negras:");
        for(int i = 0; i < 16; i++){
            imprimirPieza(piezasNegras[i]);
        }


    }
}   