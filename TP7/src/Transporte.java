public abstract class Transporte extends Vehiculo {
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