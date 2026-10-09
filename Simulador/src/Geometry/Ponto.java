package Geometry;

/**
 * A classe Geometry.Ponto é composta por duas coordenadas cartesianas x e y
 * @author Luís Moreira
 * @version 22/02/2026
 */
public class Ponto {
    private double x;
    private double y;

    /**
     * (Construtor) Constroi um Geometry.Ponto a partir das duas coordenadas cartesianas
     * @param x Representa a coordenada x do ponto
     * @param y Representa a coordenada y do ponto
     */
    public Ponto(double x, double y){
        this.x = x;
        this.y = y;
    }

    /**
     * Retorna a coordenada x do ponto
     * @return O valor da coordenada x
     */
    public double getX() {
        return x;
    }

    /**
     * Retorna a coordenada y do ponto
     * @return O valor da coordenada y
     */
    public double getY() {
        return y;
    }

    /**
     * Retorna a represenção do Geometry.Ponto
     * @return String no formato "(x,y)" com 2 casas decimais
     */
    public String toString(){ return String.format("(%.2f,%.2f)", this.x, this.y); }
}



