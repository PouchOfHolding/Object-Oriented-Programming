package Geometry.figuras;

import Geometry.Ponto;

/**
 * Representa um quadrado definido por 4 vértices no sentido dos ponteiros do relógio.
 * @author Grupo 7 PL1
 * @version 23/03/2026
 * @inv Todos os lados iguais e diagonais com o mesmo comprimento
 */
public class Quadrado extends Retangulo {

    /**
     * Constrói um Geometry.Figuras.Quadrado a partir de um array de 4 vértices
     * @param vertices Array de 4 pontos que definem os vértices do quadrado
     */
    public Quadrado(Ponto[] vertices) {
        super(vertices);
        invar();
    }

    /**
     * Verifica a invariante de instância — termina o programa se os vértices
     * não formarem um quadrado válido
     */
    @Override
    protected void invar() {
        Ponto[] v = getVertices();

        if (v == null || v.length != 4) {
            System.out.println("Geometry.Figuras.Quadrado:iv");
            System.exit(0);
        }

        double lado01 = dist(v[0], v[1]);
        double lado12 = dist(v[1], v[2]);
        double lado23 = dist(v[2], v[3]);
        double lado30 = dist(v[3], v[0]);

        boolean todosIguais = Math.abs(lado01 - lado12) < 1e-9 && Math.abs(lado12 - lado23) < 1e-9 && Math.abs(lado23 - lado30) < 1e-9;

        if (!todosIguais) {
            System.out.println("Geometry.Figuras.Quadrado:iv");
            System.exit(0);
        }
    }
}