public abstract class Carga extends Vehiculo {
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