/**
 * A classe Ponto é composta por duas coordenadas cartesianas x e y
 * @author Francisco Neves
 * @version 23/02/2026
 */


class Ponto{
    private double x,y;
    public Ponto (double x, double y){
        this.x=x;
        this.y=y;
        /**
         * vai Construir um Ponto a partir das duas coordenadas
         * @param x coordenada x do ponto
         * @param y coordenada y do ponto
         */
    }

    public double getX() {

        /**
         * Retorna a coordenada x
         * @return O valor do x
         */
        return x;}

    public double getY() {

        /**
         * Retorna a coordenada y
         * @return O valor do y
         */
        return y;}

    public String toString(){
        return String.format("(%.2f,%.2f)", this.x, this.y);
    }
}