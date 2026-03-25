import java.util.Scanner;/**
 * Lê os dados de entrada para instanciar AutoPilot e calcular
 * o tempo e a velocidade vetorial necessários para atingir o destino.
 * @author Francisco Neves
 * @version 09/03/2026
 */

import java.util.Scanner;

/**
 * Lê os dados de entrada para instanciar AutoPilot e calcular
 * o tempo e a velocidade vetorial necessários para atingir o destino.
 * @author Francisco Neves
 * @version 09/03/2026
 */
public class Client {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] parts = sc.nextLine().trim().split("\\s+");
        Ponto[] points = new Ponto[parts.length / 2];
        for (int i = 0; i < points.length; i++) {
            points[i] = new Ponto(Double.parseDouble(parts[2 * i]), Double.parseDouble(parts[2 * i + 1]));
        }
        Ponto a = new Ponto(sc.nextDouble(), sc.nextDouble());
        Ponto b = new Ponto(sc.nextDouble(), sc.nextDouble());
        sc.close();
        Route rota = new Route(points);
        System.out.println(String.format("%.2f", rota.comprimento()));

        SegmentoReta seg = new SegmentoReta(a, b);
        System.out.println(rota.intersect(seg));
    }
}