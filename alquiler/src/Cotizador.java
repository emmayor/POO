public class Cotizador {
    public static void main(String[] args){
        Auto auto = new Auto(7, 4);
        Minibus minibus = new Minibus(2, 20);
        Furgoneta furgoneta = new Furgoneta(3, 5);
        Camion camion = new Camion(6, 20);

        System.out.println(auto.getPrecioAlquiler());
        System.out.println(minibus.getPrecioAlquiler());
        System.out.println(furgoneta.getPrecioAlquiler());
        System.out.println(camion.getPrecioAlquiler());
    }
}
