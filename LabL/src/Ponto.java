/**
 * A classe Ponto é composta por duas coordenadas cartesianas x e y
 * @author Francisco Neves
 * @version 23/02/2026
 */

class Ponto{
    private double x,y;
    /**
     * vai Construir um Ponto a partir das duas coordenadas
     * @param x coordenada x do ponto
     * @param y coordenada y do ponto
     */
    public Ponto (double x, double y){
        this.x=x;
        this.y=y;
    }
    /**
     * Retorna a coordenada x
     * @return O valor do x
     */
    public double getX() {return x;}
    /**
     * Retorna a coordenada y
     * @return O valor do y
     */
    public double getY() {return y;}

    public String toString(){
        return String.format("(%.2f,%.2f)", this.x, this.y);
    }

    public double distancia(Ponto other){// alterar nome
        return Math.sqrt(Math.pow(x - other.getX(),2)+Math.pow(y - other.getY(),2));
    }
}