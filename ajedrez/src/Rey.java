public class Rey extends Pieza {
    public Rey(){}
    public Rey(String nombre, String capDesplazamiento, String conducta, String color, String material, String posicion) {
        this.setCapDesplazamiento(capDesplazamiento);
        this.setConducta(conducta);
        this.setColor(color);
        this.setMaterial(material);
        this.setPosicion(posicion);
    }
    public void Mover(){
        System.out.println("Se ha movido el Rey");
    }
}