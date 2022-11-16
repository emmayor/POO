public class Camion extends Carga {

    public Camion(){};

    public Camion(int cantDias, double pesoMaximoAutorizado){
        super(cantDias, pesoMaximoAutorizado);
    }

    public double getPrecioAlquiler(){
        return super.getPrecioAlquiler() + 1600f;
    }
}
