class Alquiler {
    private int cantDias;
    private Vehiculo tipoVehiculo;

    public Alquiler(int dias, Vehiculo tvehiculo) {
        this.cantDias = dias;
        this.tipoVehiculo = tvehiculo;
    }

    private int precioBase() {
        return cantDias * 2000;
    }
    public int precioTotal() {
        return precioBase() + tipoVehiculo.precioAdicional();
    }

    
}