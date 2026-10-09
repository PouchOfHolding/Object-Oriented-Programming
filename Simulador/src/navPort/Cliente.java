package navPort;

import Geometry.Route;
import Geometry.Vetor;

import java.util.Scanner;

/**
 * Lê os dados de entrada e calcula o comprimento, tempo, posição
 * e velocidades vetoriais para uma rota com múltiplos segmentos
 * @author Luís Moreira
 * @version 06/04/2026
 */
public abstract class Cliente {

    public static void main() {
        Scanner sc = new Scanner(System.in);

        String[] partes = sc.nextLine().trim().split("\\s+");
        double[] coords = new double[partes.length];
        for (int i = 0; i < partes.length; i++)
            coords[i] = Double.parseDouble(partes[i]);

        Vetor w   = new Vetor(sc.nextDouble(), sc.nextDouble());
        double vl = sc.nextDouble();
        double t  = sc.nextDouble();
        sc.close();

        Route rota = new Route(coords);

        IO.println(String.format("%.2f", rota.length()));
        IO.println(String.format("%.2f", rota.length() / vl));
        IO.println(rota.position(t, vl));

        Vetor[] speeds = rota.speeds(w, vl);
        for (int i = 0; i < speeds.length; i++) {
            if (i > 0) IO.print(" ");
            IO.print(speeds[i]);
        }
        IO.println();
    }
}