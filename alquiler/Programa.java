public class Programa {
    public static void main(String[] args) {
        Auto autito = new Auto(3);
        Alquiler alquiler = new Alquiler(4, autito);
        System.out.println("El precio de alquiler del Auto es de"+alquiler.precioTotal());
    }
}