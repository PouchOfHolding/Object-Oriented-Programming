import java.util.Scanner;
/**
 * Lê os dados de entrada para instanciar AutoPilot e calcular
 * o tempo e a velocidade vetorial necessários para atingir o destino.
 * @author Francisco Neves
 * @version 05/04/2026
 */
public class Client {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        if (!sc.hasNextLine()) return;
        String[] s = sc.nextLine().split(" ");
        if (s.length % 2 != 0) {
            System.out.println("Rota:iv");
            return;
        }

        Ponto[] pts = new Ponto[s.length / 2];
        for (int i = 0; i < pts.length; i++)
            pts[i] = new Ponto(Double.parseDouble(s[2*i]), Double.parseDouble(s[2*i+1]));

        Route rota = new Route(pts);
        Vetor w = new Vetor(sc.nextDouble(), sc.nextDouble());
        double vl = sc.nextDouble(), t = sc.nextDouble();

        System.out.printf("%.2f\n", rota.comprimento());
        System.out.printf("%.2f\n", rota.comprimento() / vl);
        System.out.println(rota.ondeesta(vl, t));


        AutoPilot ap = new AutoPilot(pts[0], pts[pts.length-1]);
        SegmentoReta[] segs = rota.segmentos();
        for (int i = 0; i < segs.length; i++) {
            System.out.print(ap.speed(segs[i], w, vl) + (i < segs.length - 1 ? " " : ""));
        }
        System.out.println();
        sc.close();
    }
}