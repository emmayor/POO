public class Ajedrez {
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

        for (i = 0; i < 8 ;i++){
            piezas[i]= new Peon("Ladino", "Agresor",color, material, calcularPosicion(filaPeon,i));
        }

        indice = i;

        piezas[indice] = new Torre("Directa", "Homérica", color, material, calcularPosicion(filaOtros,0));
        indice++;
        piezas[indice] = new Torre("Directa", "Homérica", color, material, calcularPosicion(filaOtros,7));
        indice++;

        piezas[indice] = new Caballo("Ligero", "???", color, material, calcularPosicion(filaOtros,1));
        indice++;
        piezas[indice] = new Caballo("Ligero", "???", color, material, calcularPosicion(filaOtros,6));
        indice++;

        piezas[indice] = new Alfil("Oblicuo", "Sesgo", color, material, calcularPosicion(filaOtros,2));
        indice++;
        piezas[indice] = new Alfil("Oblicuo", "Sesgo", color, material, calcularPosicion(filaOtros,5));
        indice++;

        piezas[indice] = new Rey("Tenue", "Postrero", color, material, calcularPosicion(filaOtros,colRey));
        indice++;
        piezas[indice] = new Reina("Armada", "Encarnizada", color, material, calcularPosicion(filaOtros,colReina));

        return piezas;
    }

    public static String calcularPosicion(int m, int n){
        // Toma valores usados como indices de matrices y devuelve coordenadas de ajedrez
        return String.valueOf(m+1)+(char)(65+n);
    }

    public static void imprimirCasilleros(Tablero tablero){
        // Imprime el sistema de coordenadas y la disposición de colores de un tablero de 8 x 8
        System.out.println("Tabla de posiciones:\n");
        for (int i = 7; i >= 0; i--){
            for (int j = 0; j < 8; j++){
                System.out.print("|"+tablero.getCasilleros()[i][j].getPosicion());
            }
            System.out.println("\n------------------------");
        }
        
        System.out.println("Disposición de los colores:\n");
        for (int i = 7; i>=0; i--){
            for (int j = 0; j<8; j++){
                if (tablero.getCasilleros()[i][j].getColor() == "Blanco") {
                    System.out.print("██");
                } else {
                    System.out.print("░░");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void imprimirPieza(Pieza pieza){
        // Imprime los atributos de una pieza
        String tipo = pieza.getClass().getSimpleName();
        String color = pieza.getColor();
        String material = pieza.getMaterial();
        String desplazamiento = pieza.getCapDesplazamiento();
        String conducta= pieza.getConducta();
        String posicion= pieza.getPosicion();
        System.out.format("%-12s%-12s%-12s%-16s%-12s%-12s\n",tipo,color,material,desplazamiento,conducta,posicion);
    }
    
    public static void imprimirLado(Pieza[] piezas){
        // Imprime 16 piezas correspondientes a un lado del tablero
        System.out.format("%-12s%-12s%-12s%-16s%-12s%-12s\n","TIPO","COLOR","MATERIAL","DESPLAZAMIENTO","CONDUCTA","POSICIÓN");
        for(int i = 0; i < 16; i++){
            imprimirPieza(piezas[i]);
        }

    }

    public static void main (String[] args) {
        Tablero tablero = new Tablero("Madera");
        System.out.println("Se ha creado un tablero de 8 x 8, cuyos casilleros se presentan de la siguiente manera:");
        imprimirCasilleros(tablero);
        
        Pieza[] piezasBlancas = instanciarPiezas("Blanco", "Madera");
        System.out.println("\nSe han instanciado las piezas blancas:");
        imprimirLado(piezasBlancas);

        Pieza[] piezasNegras = instanciarPiezas("Negro", "Madera");
        System.out.println("\nSe han instanciado las piezas negras:");
        imprimirLado(piezasNegras);
        
    }
}   