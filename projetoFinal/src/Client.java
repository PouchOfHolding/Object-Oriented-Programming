import Geometry.*;
import Geometry.figuras.*;
import Interface.padraoState.*;
import navPort.*;
import java.util.Scanner;
import Geometry.*;
import Geometry.figuras.Circulo;

public class Client {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Route rota1 = Leitor.lerRota(sc);
        Route rota2 = Leitor.lerRota(sc);
        Route rota3 = Leitor.lerRota(sc);
        Circulo obstaculo = Leitor.lerCirculo(sc);
        double velocidadeLinear = Double.parseDouble(sc.next());

        double correnteX = Double.parseDouble(sc.next());
        double correnteY = Double.parseDouble(sc.next());
        Vetor corrente = new Vetor(correnteX, correnteY);

        Route rotaEscolhida = Route.obterRotaMaisCurta(obstaculo, rota1, rota2, rota3);

        if (rotaEscolhida != null) {
            Vetor[] velocidades = rotaEscolhida.speeds(corrente, velocidadeLinear);

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < velocidades.length; i++) {
                sb.append(String.format("[%.2f,%.2f]", velocidades[i].getX(), velocidades[i].getY()));
                if (i < velocidades.length - 1) {
                    sb.append(" ");
                }
            }
            System.out.println(sb.toString());
        }

        sc.close();
    }
}