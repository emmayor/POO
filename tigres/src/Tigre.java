public class Tigre {
    private String origen;
    private String destreza;
    private String peligrosidad;
    private String energia;
    private String actitud;
    private String aspecto;

    public Tigre() {
        System.out.println("Se ha creado un tigre!");
    }

    public Tigre(String nOrigen, String nDestreza, String nPeligrosidad, String nEnergia, String nActitud, String nAspecto) {
        this.setOrigen(nOrigen);
        this.setDestreza(nDestreza);
        this.setPeligrosidad(nPeligrosidad);
        this.setEnergia(nEnergia);
        this.setActitud(nActitud);
        this.setAspecto(nAspecto);
        System.out.println("Se ha creado un tigre con argumentos!");
    }

    String getOrigen() {
        return this.origen;
    }

    String getDestreza() {
        return this.destreza;
    }
    
    String getPeligrosidad() {
        return this.peligrosidad;
    }
    
    String getEnergia() {
        return this.energia;
    }
    
    String getActitud() {
        return this.actitud;
    }
    
    String getAspecto() {
        return this.aspecto;
    }
    
    void setOrigen(String nOrigen) {
        this.origen = nOrigen;
    }

    void setDestreza(String nDestreza) {
        this.destreza = nDestreza.toLowerCase();
    }

    void setPeligrosidad(String nPeligrosidad) {
        this.peligrosidad = nPeligrosidad.toLowerCase();
    }

    void setEnergia(String nEnergia) {
        this.energia = nEnergia.toLowerCase();
    }
    
    void setActitud(String nActitud) {
        this.actitud= nActitud.toLowerCase();
    }
    
    void setAspecto(String nAspecto) {
        this.aspecto = nAspecto.toLowerCase();
    }

    public void imprimirDatos() {
        System.out.println("DATOS DEL TIGRE:");
        System.out.println("Lugar: "+this.getOrigen());
        System.out.println("Destreza: "+this.getDestreza());
        System.out.println("Peligrosidad: "+this.getPeligrosidad());
        System.out.println("Energía: "+this.getEnergia());
        System.out.println("Actitud: "+this.getActitud());
        System.out.println("Aspecto: "+this.getAspecto()+"\n");
    }
}
