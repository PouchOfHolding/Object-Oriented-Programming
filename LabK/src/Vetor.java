import java.util.Scanner;

/**
 * Representa um vetor com  X e Y como coordenadas
 * @author Francisco Neves
 * @version 23/02/2026
 * @inv O módulo do vetor não pode ser igual a zero
 */

class Vetor {
    /**
     * Constroi um Vetor a partir de coordenadas cartesianas
     * @param x Representa o x do Vetor
     * @param y Representa o y do Vetor
     */
    private double x,y;
    public Vetor(double x, double y){
        this.x=x;
        this.y=y;

        if (this.distancia()<= 1e-10){
            System.out.print("Vetor:iv\n");
            System.exit(0);
        }

    }
    /**
     * * Verifica a condição invariante de instânica, termina o programa se for violada
     */
    public Vetor (Ponto p){
        this.x=p.getX();
        this.y=p.getY();
        if (this.distancia()<= 1e-10){
            System.out.print("Vetor:iv\n");
            System.exit(0);
        }


    }
    /**
     * Retorna o x do Vetor
     * @return O valor do Vetor
     */
    public double getX() {return x;}
    /**
     * Retorna o componente y do Vetor
     * @return O valor y do Vetor
     */
    public double getY() {return y;}

    public double distancia(){// alterar nome
        return new Ponto(x,y).distancia(new Ponto(0,0));
    }
    /**
     * Calcula o cosseno da similaridade entre um Vetor e outro
     * @param that O outro Vetor
     * @return O valor do cosseno do ângulo entre os dois vetores
     */
    public double prodinterno (Vetor that){
        return (this.x*that.x + this.y*that.y);
    }
    /**
     * Calcula o cosseno do ângulo entre este vetor e o vetor dado.
     *
     * @param that o vetor com o qual calcular o ângulo
     * @return o cosseno do ângulo entre os dois vetores
     */
    public double cossine (Vetor that){// mudar nome
        double cossine = (this.prodinterno(that))/(this.distancia() * that.distancia());
        return cossine;
    }
    /**
     * Multiplica este vetor por um escalar.
     *
     * @param d o valor escalar a multiplicar
     * @return um novo vetor resultante da multiplicação
     */
    public Vetor mult(double d){
        return new Vetor (d*x,d*y);
    }
    /**
     * Soma este vetor com o vetor dado.
     *
     * @param v o vetor a somar
     * @return um novo vetor resultante da soma
     */
    public Vetor add(Vetor v){

        return new Vetor (this.x+v.x,this.y+v.y);
    }
    /**
     * Subtrai o vetor dado a este vetor.
     *
     * @param v o vetor a subtrair
     * @return um novo vetor resultante da subtração
     */
    public Vetor sub(Vetor v){

        return new Vetor (this.x-v.x,this.y-v.y);
    }
    /**
     * Calcula o ponto de interseção entre este vetor (como reta) e o segmento de reta dado.
     *
     * @param s o segmento de reta com o qual calcular a interseção
     * @return o ponto de interseção, ou null caso não exista
     */
    public Ponto intersect(SegmentoReta s){
        return s.intersect(this);
    }
    public String toString(){
        return String.format("[%.2f,%.2f]", this.x, this.y);
    }
}
