public class TigreApp {
    public static void main(String[] args){
        // Instanciación
        Tigre tigrePalermo = new Tigre("Palermo","Delicado","Fatal","Infinita","Sanguinario","Hermoso");
        Tigre miTigre = new Tigre("Villa del Parque","Atolondrado","Inofensivo","Poca","Tranquilo","Feo");

        // Impresión de datos
        tigrePalermo.imprimirDatos();
        miTigre.imprimirDatos();

        // Generación de texto
        String textoMiTigre = Texto.generarTexto(miTigre);
        String textoTigrePalermo = Texto.generarTexto(tigrePalermo);

        //Impresión de texto
        System.out.println(textoMiTigre);
        System.out.println(textoTigrePalermo);
    }
}