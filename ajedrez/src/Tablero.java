public class Tablero {
    private String material;
    private Casillero[][] casilleros = new Casillero[8][8];

    public Tablero(String material){
        this.material = material;
        for (int i = 0; i<8; i++){
            for (int j = 0; j<8; j++){
                this.casilleros[i][j] = new Casillero();
                Casillero casilleroActual = this.casilleros[i][j];
                casilleroActual.setPosicion(Ajedrez.calcularPosicion(i+1,j));
                if ((i % 2) == (j % 2)) {
                    casilleroActual.setColor("Negro");
                } else {
                    casilleroActual.setColor("Blanco");
                }
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
