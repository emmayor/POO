public abstract class Vehiculo {
    private int cantDias;

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
        return cantDias * 2000;
    }
}

abstract class Transporte extends Vehiculo {
    private int cantPlazas;

    public Transporte(int cantDias, int cantPlazas){
        super(cantDias);
        this.setCantDias(cantDias);
    }

    public int getCantPlazas() {
        return cantPlazas;
    }

    public void setCantPlazas(int cantPlazas) {
        this.cantPlazas = cantPlazas;
    }

    public double getPrecioAlquiler(){
        return super.getPrecioAlquiler() + this.getCantPlazas() * this.getCantDias() * 100;   
    }
}

abstract class Carga extends Vehiculo {
    private int pesoMaximoAutorizado;

    public Carga(int cantDias, int pesoMaximoAutorizado){
        super(cantDias);
        this.setPesoMaximoAutorizado(pesoMaximoAutorizado);
    }

    public int getPesoMaximoAutorizado() {
        return pesoMaximoAutorizado;
    }

    public void setPesoMaximoAutorizado(int pesoMaximoAutorizado) {
        this.pesoMaximoAutorizado = pesoMaximoAutorizado;
    }  

    public double getPrecioAlquiler(){
        return super.getPrecioAlquiler() + this.getPesoMaximoAutorizado() * 800;
    }
}

final class Auto extends Transporte {
    public Auto(int cantDias, int cantPlazas){
        super(cantDias, cantPlazas);
    }

    public double getPrecioAlquiler(){
        return super.getPrecioAlquiler();
    }
}

final class Minibus extends Transporte {
    public Minibus(int cantDias, int cantPlazas){
        super(cantDias, cantPlazas);
    }

    public double getPrecioAlquiler(){
        return super.getPrecioAlquiler() + this.getCantPlazas() * 120;
    }
}

final class Furgoneta extends Carga {
    public Furgoneta(int cantDias, int pesoMaximoAutorizado){
        super(cantDias, pesoMaximoAutorizado);
    }

    public double getPrecioAlquiler(){
        return super.getPrecioAlquiler();
    }
}

final class Camion extends Carga {
    public Camion(int cantDias, int pesoMaximoAutorizado){
        super(cantDias, pesoMaximoAutorizado);
    }

    public double getPrecioAlquiler(){
        return super.getPrecioAlquiler() + 1600;
    }
}