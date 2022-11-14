public class Minibus extends Transporte {

    public Minibus(){};

    public Minibus(int cantDias, int cantPlazas){
        super(cantDias, cantPlazas);
    }

    public double getPrecioAlquiler(){
        return super.getPrecioAlquiler() + this.getCantPlazas() * 120f;
    }
}