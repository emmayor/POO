public class Caballo extends Pieza {
    public Caballo(){}
    public Caballo(String capDesplazamiento, String conducta, String color, String material, String posicion) {
        super(capDesplazamiento, conducta, color, material, posicion);
    }
    public void Mover(){
        System.out.println("Se ha movido el Caballo");
    }
}