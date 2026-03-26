import java.util.Scanner;

/**
 * Representa um vetor com  X e Y como coordenadas
 * @author Francisco Neves
 * @version 23/02/2026
 * @inv O módulo do vetor não pode ser igual a zero
 */

class Vetor {
    private double x,y;
    /**
     * Constroi um Vetor a partir de coordenadas cartesianas
     * @param x Representa o x do Vetor
     * @param y Representa o y do Vetor
     */
    public Vetor(double x, double y){
        this.x=x;
        this.y=y;

        if (this.distancia()<= 1e-10){
            System.out.print("Vetor:iv\n");
            System.exit(0);
        }
        /**
         * Constroi um Vetor a partir das coordenadas de um Ponto
         * @param p Respenta o Ponto que define o Vetor posição
         */
    }
    public Vetor (Ponto p){
        this.x=p.getX();
        this.y=p.getY();

        /**
         * * Verifica a condição invariante de instânica, termina o programa se for violada
         */
    }

    public double getX() {return x;
        /**
         * Retorna o x do Vetor
         * @return O valor do Vetor
         */}
    public double getY() {return y;
        /**
         * Retorna o componente y do Vetor
         * @return O valor y do Vetor
         */}

    public double distancia(){// alterar nome
        return Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
        /**
         * Calcula o produto interno entre um Vetor e outro
         * @param that O outro Vetor
         * @return O resultaado do produto interno
         */
    }
    public double prodinterno (Vetor that){
        return (this.x*that.x + this.y*that.y);
        /**
         * Calcula o cosseno da similaridade entre um Vetor e outro
         * @param that O outro Vetor
         * @return O valor do cosseno do ângulo entre os dois vetores
         */
    }
    public double cossine (Vetor that){// mudar nome
        double cossine = (this.prodinterno(that))/(this.distancia() * that.distancia());
        return cossine;
    }
    public Ponto intersect(SegmentoReta s){
        return s.intersect(this);
    }
    public String toString(){
        return "("+this.x+","+this.y+")";
    }
}
