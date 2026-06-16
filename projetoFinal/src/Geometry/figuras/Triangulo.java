package Geometry.figuras;

import Geometry.Ponto;

/**
 * Representa um triângulo definido por 3 vértices não colineares no sentido dos ponteiros do relógio
 * @author Grupo 7 PL1
 * @version 23/03/2026
 * @inv Os 3 vértices não podem ser colineares
 */
public class Triangulo extends Poligono {

    /**
     * Constrói um Triângulo a partir de um array de 3 vértices
     * @param vertices Array de 3 pontos que definem os vértices do triângulo
     */
    public Triangulo(Ponto[] vertices) {
        super(vertices);
        invar();
    }

    /**
     * Verifica a invariante de instância — termina o programa se os vértices
     * forem colineares ou se não forem exatamente 3
     */
    @Override
    protected void invar() {
        Ponto[] v = getVertices();

        if (v == null || v.length != 3) {
            System.out.println("Geometry.Figuras.Triangulo:iv");
            System.exit(0);
        }

        double area = (v[1].getX() - v[0].getX()) * (v[2].getY() - v[0].getY()) - (v[2].getX() - v[0].getX()) * (v[1].getY() - v[0].getY());

        if (Math.abs(area) < 1e-9) {
            System.out.println("Geometry.Figuras.Triangulo:iv");
            System.exit(0);
        }
    }
}