public class Casillero {
    private String color;
    private String posicion;
    public Casillero(){};
    public Casillero(String color, String posicion){
        this.color = color;
        this.posicion = posicion;
    }

    public String getColor(){
        return this.color;
    }

    public void setColor(String color){
        this.color = color;
    }

    public String getPosicion(){
        return this.posicion;
    }
    
    public void setPosicion(String posicion){
        this.posicion = posicion;
    }
}