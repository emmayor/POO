public class Torre extends Pieza {
    public Torre(){}
    public Torre(String capDesplazamiento, String conducta, String color, String material, String posicion) {
        super(capDesplazamiento, conducta, color, material, posicion);
    }
    public void Mover(){
        System.out.println("Se ha movido la Torre");
    }
}