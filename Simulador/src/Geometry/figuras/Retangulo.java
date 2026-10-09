package Geometry.figuras;

import Geometry.Ponto;

/**
 * Representa um retângulo definido por 4 vértices no sentido dos ponteiros do relógio.
 * @author Luís Moreira
 * @version 23/03/2026
 * @inv Lados opostos iguais e diagonais com o mesmo comprimento
 */
public class Retangulo extends Poligono {

    /**
     * Constrói um Retângulo a partir de um array de 4 vértices
     * @param vertices Array de 4 pontos que definem os vértices do retângulo
     */
    public Retangulo(Ponto[] vertices) {
        super(vertices);
        invar();
    }

    /**
     * Calcula a distância entre dois pontos
     * @param a Primeiro ponto
     * @param b Segundo ponto
     * @return A distância euclidiana entre a e b
     */
    protected double dist(Ponto a, Ponto b) {
        double dx = b.getX() - a.getX();
        double dy = b.getY() - a.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Verifica a invariante de instância — termina o programa se os vértices
     * não formarem um retângulo válido
     */
    @Override
    protected void invar() {
        Ponto[] v = getVertices();

        if (v == null || v.length != 4) {
            System.out.println("Geometry.Figuras.Retangulo:iv");
            System.exit(0);
        }

        double lado01 = dist(v[0], v[1]);
        double lado12 = dist(v[1], v[2]);
        double lado23 = dist(v[2], v[3]);
        double lado30 = dist(v[3], v[0]);

        double diag02 = dist(v[0], v[2]);
        double diag13 = dist(v[1], v[3]);

        boolean ladosOpostosIguais = Math.abs(lado01 - lado23) < 1e-9 && Math.abs(lado12 - lado30) < 1e-9;
        boolean diagonaisIguais    = Math.abs(diag02 - diag13) < 1e-9;

        if (!ladosOpostosIguais || !diagonaisIguais) {
            System.out.println("Geometry.Figuras.Retangulo:iv");
            System.exit(0);
        }
    }
}