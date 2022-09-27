public class TigreApp {
    public static void main(String[] args){
        Tigre tigrePalermo = new Tigre("Palermo","Delicado","Fatal","Infinita","Sanguinario","Hermoso");
        Tigre miTigre = new Tigre("Villa del Parque","Atolondrado","Inofensivo","Poca","Tranquilo","Feo");
        tigrePalermo.imprimirDatos();
        miTigre.imprimirDatos();
        String textoMiTigre = Texto.generarTexto(miTigre);
        System.out.println(textoMiTigre);
    }
}