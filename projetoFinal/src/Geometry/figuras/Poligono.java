package Geometry.figuras;

import Geometry.Ponto;
import Geometry.Route;
import Geometry.SegmentoReta;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa um polígono definido por uma lista ordenada de vértices no sentido dos ponteiros do relógio
 * @author Grupo 7 PL1
 * @version 23/03/2026
 * @inv O polígono tem pelo menos 3 vértices
 */
public class Poligono extends Figura {

    private Ponto[] vertices;

    /**
     * Constrói um Polígono a partir de um array de vértices
     * @param vertices Array de pontos que definem os vértices do polígono
     */
    public Poligono(Ponto[] vertices) {
        this.vertices = vertices;
        invar();
    }

    /**
     * Verifica a invariante de instância, termina o programa se for violada
     */
    protected void invar() {
        if (vertices == null || vertices.length < 3) {
            System.out.println("Geometry.Figuras.Poligono:iv");
            System.exit(0);
        }
    }

    /**
     * Retorna os vértices do polígono
     * @return Array de Geometry.Ponto com os vértices
     */
    public Ponto[] getVertices() {
        return vertices;
    }

    /**
     * Verifica se um ponto já existe na lista (evita repetições)
     * @param p     O ponto a verificar
     * @param lista A lista de pontos já encontrados
     * @return true se o ponto já existe na lista, false caso contrário
     */
    protected boolean jaExiste(Ponto p, List<Ponto> lista) {
        for (Ponto existente : lista)
            if (Math.abs(existente.getX() - p.getX()) < 1e-9 && Math.abs(existente.getY() - p.getY()) < 1e-9)
                return true;
        return false;
    }

    /**
     * Calcula os pontos de interseção do polígono com uma rota.
     * Itera pelos segmentos da rota para garantir a ordem correta desde o início da rota.
     * @param r A rota a intersetar com o polígono
     * @return Lista de pontos de interseção ordenados desde o início da rota
     */
    @Override
    public List<Ponto> intersect(Route r) {
        List<Ponto> intersecoes = new ArrayList<>();
        Ponto[] pts = r.getPontos();

        for (int i = 0; i < pts.length - 1; i++) {
            SegmentoReta segRota = new SegmentoReta(pts[i], pts[i + 1]);

            for (int j = 0; j < vertices.length; j++) {
                Ponto p1 = vertices[j];
                Ponto p2 = vertices[(j + 1) % vertices.length];
                SegmentoReta aresta = new SegmentoReta(p1, p2);

                Ponto intersecao = segRota.intersect(aresta);
                if (intersecao != null && !jaExiste(intersecao, intersecoes))
                    intersecoes.add(intersecao);
            }
        }

        return intersecoes;
    }
}