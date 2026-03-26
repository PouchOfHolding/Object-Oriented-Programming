import java.util.Scanner;
class Vetor {
    private double x,y;
    public Vetor(double x, double y){
        this.x=x;
        this.y=y;

        if (this.distancia()<= 0){
            System.out.print("iv\n");
            System.exit(0);
        }
    }
    public double distancia(){
        double distancia = Math.sqrt((x-0)*(x-0)+(y-0)*(y-0));
        return distancia;
    }

    public double prodinterno (Vetor that){
        double prodinterno = (this.x*that.x + this.y*that.y);
        return prodinterno;

    }
    public String toString(){
        return "("+this.x+","+this.y+")";
    }
}

public class Client{
    public static void main (String [] args){
        Scanner sc = new Scanner(System.in);// da inicio ao scanner
        double xscan = sc.nextDouble();
        double yscan = sc.nextDouble();
        Vetor v1 = new Vetor(xscan, yscan);

        double x1scan = sc.nextDouble();
        double y1scan = sc.nextDouble();
        Vetor v2 = new Vetor(x1scan, y1scan);

        double resultado = v1.prodinterno(v2);
        System.out.printf("%.2f\n", resultado);
    }
}
