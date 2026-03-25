import java.util.Scanner;
class Vetor {
    private double x,y;
    public Vetor(double x, double y){
        this.x=x;
        this.y=y;
    }
    public double distancia(){
        double distancia = Math.sqrt((x-0)*(x-0)+(y-0)*(y-0));
        return distancia;
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
        double resultado = v1.distancia();
        System.out.printf("%.2f%n", resultado);
    }
}
