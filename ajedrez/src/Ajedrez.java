public class Ajedrez {

    private static Pieza[] instanciarPiezas(String color) {
        int i = 0;
        Pieza piezas[];
        piezas = new Pieza[16];
        for (i = 0;i<8;i++){
           Peon nuevoPeon = new Peon();
           nuevoPeon.setColor(color);
           nuevoPeon.setCapDesplazamiento(color);
           piezas[i]=nuevoPeon;
        }
        System.out.println(i); // Debug
        for (true;i<10;i++){
            Alfil nuevoAlfil = new Alfil();
            nuevoAlfil.setColor(color);
            piezas[i]=nuevoAlfil;
         }



        return piezas;
    }

    public static void main (String[] args) {
        Pieza[] piezasBlancas = instanciarPiezas("Blanco");
        for (int i = 0; i<8; i++){
            System.out.println(piezasBlancas[i].getColor());
        }

    }
}   