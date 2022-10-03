public class Peon extends Pieza {
    public Peon(){}
    public Peon(String capDesplazamiento, String conducta, String color, String material, String posicion) {
        this.setCapDesplazamiento(capDesplazamiento);
        this.setConducta(conducta);
        this.setColor(color);
        this.setMaterial(material);
        this.setPosicion(posicion);
    }
    public void Mover(){
        System.out.println("Se ha movido el Peon");
    }
}