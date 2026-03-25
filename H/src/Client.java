import java.util.Scanner;/**
 * Lê os dados de entrada para instanciar AutoPilot e calcular
 * o tempo e a velocidade vetorial necessários para atingir o destino.
 * @author Francisco Neves
 * @version 09/03/2026
 */

public class Client {
    public static void main() {
        Scanner sc = new Scanner(System.in);
        //Get start and finish points
        Ponto start = new Ponto(sc.nextDouble(), sc.nextDouble());
        Ponto finish = new Ponto(sc.nextDouble(), sc.nextDouble());
        //Get wind speed and direction
        Vetor w = new Vetor(sc.nextDouble(), sc.nextDouble());
        //Get linear speed
        double s = sc.nextDouble();
        sc.close();
        //Setup auto pilot and compute:
        // i) desired time to reach the finish point
        // ii) vectorial speed required
        AutoPilot ap = new AutoPilot(start, finish);
        double t = ap.time(w, s);
        System.out.println(String.format(java.util.Locale.US, "%.2f", t));
        System.out.println(ap.speed(w, t));
    }
}