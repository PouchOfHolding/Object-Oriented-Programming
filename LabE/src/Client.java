import java.util.Scanner;
/**
 * Lê os dados de entrada para instanciar Ponto, Vetor e SegmentoReta.
 * @author Francisco Neves
 * @version 23/02/2026
 */

public class Client{
    public static void main (String [] args){
        Scanner sc = new Scanner(System.in);// da inicio ao scanner
        /**
         * Método principal arranca o programa
         * @param args Argumentos da linha de comandos
         */

        double p1x= sc.nextDouble();
        double p1y = sc.nextDouble();
        Ponto p = new Ponto(p1x, p1y);

        double p2x = sc.nextDouble();
        double p2y = sc.nextDouble();
        Ponto p2 = new Ponto(p2x, p2y);

        SegmentoReta reta= new SegmentoReta(p,p2);

        double x1scan = sc.nextDouble();
        double y1scan = sc.nextDouble();
        Vetor v = new Vetor(x1scan, y1scan);

        Ponto fim= reta.intersect(v);
        if (fim == null) {
            System.out.println("null");
        } else {
            System.out.println(fim);
        }
        System.exit(0);
    }
}
