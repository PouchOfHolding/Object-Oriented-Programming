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
        Ponto[] pontos = new Ponto[parts.length / 2];
        for (int i = 0; i < pontos.length; i++)
            pontos[i] = new Ponto(Double.parseDouble(parts[2*i]), Double.parseDouble(parts[2*i+1]));

        Route rota = new Route(pontos);

        String[] obs = sc.nextLine().trim().split("\\s+");
        sc.close();

        char tipo = obs[0].charAt(0);
        double[] coords = new double[obs.length - 1];
        for (int i = 0; i < coords.length; i++)
            coords[i] = Double.parseDouble(obs[i + 1]);

        String resultado = "null";

        if (tipo == 'T') {
            Triangulo t = new Triangulo(new Ponto(coords[0],coords[1]), new Ponto(coords[2],coords[3]), new Ponto(coords[4],coords[5]));
            resultado = t.intersect(rota);
        } else if (tipo == 'R') {
            Retangulo r = new Retangulo(new Ponto(coords[0],coords[1]), new Ponto(coords[2],coords[3]), new Ponto(coords[4],coords[5]), new Ponto(coords[6],coords[7]));
            resultado = r.intersect(rota);
        } else if (tipo == 'S') {
            Quadrado q = new Quadrado(new Ponto(coords[0],coords[1]), new Ponto(coords[2],coords[3]), new Ponto(coords[4],coords[5]), new Ponto(coords[6],coords[7]));
            resultado = q.intersect(rota);
        } else if (tipo == 'C') {
            Circulo c = new Circulo(new Ponto(coords[0],coords[1]), coords[2]);
            resultado = c.intersect(rota);
        } else if (tipo == 'P') {
            Poligno p = new Poligno(parsePontos(coords));
            resultado = p.intersect(rota);
        }

        System.out.println(resultado);
    }

    private static Ponto[] parsePontos(double[] coords) {
        Ponto[] p = new Ponto[coords.length / 2];
        for (int i = 0; i < p.length; i++)
            p[i] = new Ponto(coords[2*i], coords[2*i+1]);
        return p;
    }
}