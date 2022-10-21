public abstract class Vehiculo {
    private int cantDias;

    public Vehiculo(){};

    public Vehiculo(int cantDias){
        this.setCantDias(cantDias);
    }

    public int getCantDias() {
        return cantDias;
    }

    public void setCantDias(int cantDias) {
        this.cantDias = cantDias;
    }

    public double getPrecioAlquiler(){
        return cantDias * 2000f;
    }
}

abstract class Transporte extends Vehiculo {
    private int cantPlazas;

    public Transporte(){};

    public Transporte(int cantDias, int cantPlazas){
        super(cantDias);
        this.setCantPlazas(cantPlazas);
    }

    public int getCantPlazas() {
        return cantPlazas;
    }

    public void setCantPlazas(int cantPlazas) {
        this.cantPlazas = cantPlazas;
    }

    public double getPrecioAlquiler(){
        return super.getPrecioAlquiler() + this.getCantPlazas() * this.getCantDias() * 100f;   
    }
}

abstract class Carga extends Vehiculo {
    private double pesoMaximoAutorizado;

    public Carga(){};

    public Carga(int cantDias, double pesoMaximoAutorizado){
        super(cantDias);
        this.setPesoMaximoAutorizado(pesoMaximoAutorizado);
    }

    public double getPesoMaximoAutorizado() {
        return pesoMaximoAutorizado;
    }

    public void setPesoMaximoAutorizado(double pesoMaximoAutorizado) {
        this.pesoMaximoAutorizado = pesoMaximoAutorizado;
    }  

    public double getPrecioAlquiler(){
        return super.getPrecioAlquiler() + this.getPesoMaximoAutorizado() * 800f;
    }
}

class Auto extends Transporte {

    public Auto(){};
    
    public Auto(int cantDias, int cantPlazas){
        super(cantDias, cantPlazas);
    }
}

class Minibus extends Transporte {

    public Minibus(){};

    public Minibus(int cantDias, int cantPlazas){
        super(cantDias, cantPlazas);
    }

    public double getPrecioAlquiler(){
        return super.getPrecioAlquiler() + this.getCantPlazas() * 120f;
    }
}

class Furgoneta extends Carga {
    public Furgoneta(){};

    public Furgoneta(int cantDias, double pesoMaximoAutorizado){
        super(cantDias, pesoMaximoAutorizado);
    }
}

class Camion extends Carga {

    public Camion(){};

    public Camion(int cantDias, double pesoMaximoAutorizado){
        super(cantDias, pesoMaximoAutorizado);
    }

    public double getPrecioAlquiler(){
        return super.getPrecioAlquiler() + 1600f;
    }
}