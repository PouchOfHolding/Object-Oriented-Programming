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
        double distancia = Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
        return distancia;
    }

    public double prodinterno (Vetor that){
        double prodinterno = (this.x*that.x + this.y*that.y);
        return prodinterno;

    }

    public double cossine (Vetor that){
        double cossine = (this.prodinterno(that))/(this.distancia() * that.distancia());
        return cossine;
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

        double resultado = v1.cossine(v2);
        System.out.printf("%.2f\n", resultado);
    }
}
