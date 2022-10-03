public class Tablero {
    private String material;
    private Casillero[][] casilleros = new Casillero[8][8];

    public static String calcularPosicion(int i, int j){
        // Toma valores usados como indices de matrices y devuelve coordenadas de ajedrez
        return String.valueOf(i+1)+(char)(65+j);
    }

    public Tablero(String material){
        this.material = material;
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                Casillero nuevoCasillero = new Casillero();
                nuevoCasillero.setPosicion(calcularPosicion(i,j));
                if ((i % 2) == (j % 2)) {
                    nuevoCasillero.setColor("Negro");
                } else {
                    nuevoCasillero.setColor("Blanco");
                }
                this.casilleros[i][j] = nuevoCasillero;
            }
        }
    }

    public String getMaterial(){
        return this.material;
    }

    public Casillero[][] getCasilleros() {
        return this.casilleros;
    }
}
