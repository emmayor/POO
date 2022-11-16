public class Reina extends Pieza {
    public Reina(){}
    public Reina(String capDesplazamiento, String conducta, String color, String material, String posicion) {
        super(capDesplazamiento, conducta, color, material, posicion);
    }
    public void Mover(){
        System.out.println("Se ha movido la Reina");
    }
}