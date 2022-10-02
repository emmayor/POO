public class Pieza {
    private String nombre;
    private String capDesplazamiento;
    private String conducta;
    private String color;
    private String material;
    private String posicion;

    public Pieza(){}
    public void Mover(){}

    public Pieza(String nombre, String capDesplazamiento, String conducta, String color, String material, String posicion) {
        this.nombre = nombre;
        this.capDesplazamiento = capDesplazamiento;
        this.conducta = conducta;
        this.color = color;
        this.material = material;
        this.posicion = posicion;
    }

    public String getNombre(){
        return this.nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

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

