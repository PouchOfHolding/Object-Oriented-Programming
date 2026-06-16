import Geometry.*;
import Geometry.figuras.Circulo;
import java.util.Scanner;

/**boa tarde professor, criei esta class, pois eu nao sabia se colocar isto dentro da main iria contra
 * os requesitos pre definidos para a main, pois minha idea era colocar no client antes de main
 * depois de falar com o prof fiquei na duvida ent fiz assim
 */
public class Leitor{
    public static Route lerRota(Scanner sc) {
        String[] partes = sc.nextLine().trim().split("\\s+");
        double[] coords = new double[partes.length];
        for (int i = 0; i < partes.length; i++) {
            coords[i] = Double.parseDouble(partes[i]);
        }
        return new Route(coords);
    }

    public static Circulo lerCirculo(Scanner sc) {
        double x = Double.parseDouble(sc.next());
        double y = Double.parseDouble(sc.next());
        double raio = Double.parseDouble(sc.next());
        return new Circulo(new Ponto(x, y), raio);
        }
}