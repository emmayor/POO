public class Texto {
    public static String generarTexto(Tigre nTigre) {
        return "EL TIGRE DE "+nTigre.getOrigen().toUpperCase()+"\nIba y venía, "+nTigre.getDestreza().toLowerCase()+" y "+nTigre.getPeligrosidad().toLowerCase()+", cargado de "+nTigre.getEnergia().toLowerCase()+" energía, del otro lado de los firmes barrotes y todos lo mirábamos. Era el tigre de esa mañana, en "+nTigre.getOrigen().toLowerCase()+", y el tigre del Oriente y el tigre de Blake y de Hugo y Shere Khan, y los tigres que fueron y que serán y asimismo el tigre arquetipo, ya que el individuo, en su caso, es toda la especie. Pensamos que era "+nTigre.getActitud().toLowerCase()+" y "+nTigre.getAspecto().toLowerCase()+". Nora, una niña, dijo: Esta hecho para el amor\n";
    }
}
