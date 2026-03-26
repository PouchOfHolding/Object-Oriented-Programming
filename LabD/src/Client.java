import java.util.Scanner;

public class Client{
    public static void main (String [] args){
        Scanner sc = new Scanner(System.in);// da inicio ao scanner
        double px= sc.nextDouble();
        double py = sc.nextDouble();
        Ponto p = new Ponto(px, py);

        double x1scan = sc.nextDouble();
        double y1scan = sc.nextDouble();
        Vetor v2 = new Vetor(x1scan, y1scan);

        SegmentoReta reta= new SegmentoReta(p,v2);

        System.out.println(reta);

        System.exit(0);
    }
}
