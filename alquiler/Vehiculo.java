public class Vehiculo {
    private String patente;
    public void setPatente(String patente_nueva) {
        this.patente = patente_nueva;
    }
    public String getPatente(){
        return this.patente;
    }
    public int precioAdicional(){
        return 0;
    }
}
class Transporte extends Vehiculo {
    protected int cantPlazas;
    public Transporte(){}
    public Transporte(int plazas){
        this.cantPlazas = plazas;
    }
}

class Carga extends Vehiculo {
    protected int precioMaximoAutorizado;
    public Carga(){}
    public Carga(int pma){
        this.precioMaximoAutorizado = pma;
    }
}

class Auto extends Transporte {
    private int diasAlquiler;
    public Auto() {}
    public Auto(int dias){
        this.diasAlquiler = dias;
    }
    public int precioAdicional() {
        return diasAlquiler * this.cantPlazas * 100;
    }
}

class Minibus extends Transporte {
    public Minibus(int plazas){
        this.cantPlazas = plazas;
    }
    public int precioAdicional() {
        return this.cantPlazas * 120;
    }
}

class Furgoneta extends Carga {
    public int precioAdicional() {
        return this.precioMaximoAutorizado * 800;
    }
}

class Camion extends Carga {
    public int precioAdicional() {
        return 1600 + this.precioMaximoAutorizado * 800;
    }
}

