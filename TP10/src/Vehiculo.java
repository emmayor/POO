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