package Geometry;

/**
 * Representa um vetor com coordenadas X e Y
 * @author Luís Moreira
 * @version 22/02/2026
 * @inv O módulo do vetor não pode ser igual a zero
 */
public class Vetor {
    private double x,y;

    /**
     * Constroi um Geometry.Vetor a partir de coordenadas cartesianas
     * @param x Representa o valor x do Geometry.Vetor
     * @param y Representa o valor y do Geometry.Vetor
     */
    public Vetor(double x, double y) {    //Construtor
        //this.invar(); //é boa prática fazer primeiro a verificação para ser mais eficiente
        this.x = x;
        this.y = y;
        this.invar();

    }

    /**
     * Constroi um Geometry.Vetor posição a aprtir de um Geometry.Ponto
     * @param p Resprenta o Geometry.Ponto cujas coordenadas definem o vetor
     */
    public Vetor(Ponto p){

        this.x = p.getX();
        this.y = p.getY();
        this.invar();
    }

    /**
     * Interseta este vetor com um segmento de reta, dando a lógica para o segmento
     * @param v Respresenta o segmento de reta a verificar.
     * @return O Geometry.Ponto de interseção ou null se não se cruzarem
     */
    public Ponto intersect(SegmentoReta v){

        return v.intersect(this);
    }

    /**
     * Verifica a condição invariante de instância, termina o programa se for violada
     */
    private void invar(){
        if ((this.dist() < 1e-9) ) {

            System.out.println("Geometry.Vetor:iv");
            System.exit(0);
        }
    }

    /**
     * Retorna o componente x do Geometry.Vetor
     * @return O valor x do Geometry.Vetor
     */
    public double getX() {
        return x;
    }

    /**
     * Retorna o componente y do Geometry.Vetor
     * @return O valor y do Geometry.Vetor
     */
    public double getY() {
        return y;
    }

    /**
     * Calcula o módulo (distancia) do Geometry.Vetor
     * @return A distÂncia do Geometry.Vetor
     */
    public double dist(){
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
    }


    /**
     * Calcula o produto interno entre um Geometry.Vetor e outro
     * @param that O outro Geometry.Vetor
     * @return O resultaado do produto interno
     */
    public double prodint(Vetor that){
        return ((this.x * that.x) +  (this.y * that.y));
    }

    /**
     * Calcula o cosseno da similaridade entre um Geometry.Vetor e outro
     * @param that O outro Geometry.Vetor
     * @return O valor do cosseno do ângulo entre os dois vetores
     */
    public double cossimi(Vetor that){
        return (prodint(that) / (this.dist() * that.dist()));
    }


    /**
     * Novo Geometry.Vetor com X e Y multiplicador pelo escalar d
     * @param d O valor escalar pelo qual o vetor vai ser multiplicado
     * @return Um novo Geometry.Vetor correspondente ao resultado da multiplicação por d
     */
    public Vetor mult(double d){
        double novoX = this.x * d;
        double novoY = this.y * d;
        return new Vetor(novoX, novoY);
    }

    /**
     * Faz a soma de um vetor com outro
     * @param v O vetor a ser adicionado ao atual
     * @return Um novo vetor que representa a soma deste vetor com o vetor dado
     */
    public Vetor add(Vetor v){
        return new Vetor(this.x + v.getX(), this.y + v.getY());
    }

    /**
     * Representa o vetor numa string
     * @return o vetor em forma de "[x,y]"
     */
    public String toString(){
        return String.format("[%.2f,%.2f]", x, y);
    }

}
