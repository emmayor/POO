public class Pieza {
    private String capDesplazamiento;
    private String conducta;
    private String color;
    private String material;
    private String posicion;

    public Pieza(){}
    public void Mover(){}

    public String getCapDesplazamiento(){
        return this.capDesplazamiento;
    }

    public void setCapDesplazamiento(String capDesplazamiento){
        this.capDesplazamiento = capDesplazamiento;
    }

    public String getConducta(){
        return this.conducta;
    }

    public void setConducta(String conducta){
        this.conducta= conducta;
    }

    public String getColor(){
        return this.color;
    }

    public void setColor(String color){
        this.color = color;
    }

    public String getMaterial(){
        return this.material;
    }

    public void setMaterial(String material){
        this.material = material;
    }

    public String getPosicion(){
        return this.posicion;
    }

    public void setPosicion(String posicion){
        this.posicion = posicion;
    }
    
}

