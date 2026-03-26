import java.util.Scanner;
class Vetor {
    private double x,y;
    public Vetor(double x, double y){
        this.x=x;
        this.y=y;

        if (this.distancia()<= 0){
            System.out.print("Vetor:iv\n");
            System.exit(0);
        }
    }
    public Vetor (Ponto p){
        this.x=p.getX();
        this.y=p.getY();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double distancia(){// alterar nome
        return Math.sqrt(Math.pow(x,2)+Math.pow(y,2));

    }

    public double prodinterno (Vetor that){
        double prodinterno = (this.x*that.x + this.y*that.y);
        return prodinterno;

    }

    public double cossine (Vetor that){// mudar nome
        double cossine = (this.prodinterno(that))/(this.distancia() * that.distancia());
        return cossine;
    }
    public String toString(){
        return "("+this.x+","+this.y+")";
    }
}
