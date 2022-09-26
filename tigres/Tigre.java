public class Tigre {
    private String destreza;
    private String peligrosidad;
    private String energia;
    private String actitud;
    private String aspecto;

    public Tigre() {
        System.out.println("Se ha creado un tigre!");
    }

    public Tigre(String nDestreza, String nPeligrosidad, String nEnergia, String nActitud, String nAspecto) {
        this.setDestreza(nDestreza);
        this.setPeligrosidad(nPeligrosidad);
        this.setEnergia(nEnergia);
        this.setActitud(nActitud);
        this.setAspecto(nAspecto);
        System.out.println("Se ha creado un tigre con argumentos!");
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

    void setDestreza(String nuevaDestreza) {
        this.destreza = nuevaDestreza;
    }

    void setPeligrosidad(String nuevaPeligrosidad) {
        this.peligrosidad = nuevaPeligrosidad;
    }

    void setEnergia(String nuevaEnergia) {
        this.energia = nuevaEnergia;
    }
    
    void setActitud(String nuevaActitud) {
        this.actitud= nuevaActitud;
    }
    
    void setAspecto(String nuevoAspecto) {
        this.aspecto = nuevoAspecto;
    }

    void printDatos() {
        System.
    }
    
}