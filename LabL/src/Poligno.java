/**
 * Representa um polígono definido por uma lista ordenada de vértices
 * no sentido dos ponteiros do relógio, com pelo menos 3 vértices.
 * @author Francisco Neves
 * @version 23/03/2026
 * @inv Mínimo 3 vértices não nulos
 */
public class Poligno {
    private Ponto[] vertices;

    /**
     * Cria um polígono a partir de um array de vértices.
     * @param vertices array com os vértices no sentido dos ponteiros do relógio
     */
    public Poligno(Ponto[] vertices) {
        if (vertices == null || vertices.length < 3) {
            System.out.print("Poligono:iv\n");
            System.exit(0);
        }
        this.vertices = vertices;
    }

    /**
     * Devolve os vértices do polígono.
     * @return array de Ponto com os vértices
     */
    public Ponto[] getVertices() {
        return vertices;
    }

    /**
     * Constrói as arestas do polígono como SegmentoReta.
     * @return array de SegmentoReta com todas as arestas
     */
    private SegmentoReta[] arestas() {
        SegmentoReta[] arestas = new SegmentoReta[vertices.length];
        for (int i = 0; i < vertices.length; i++) {
            Ponto p1 = vertices[i];
            Ponto p2 = vertices[(i + 1) % vertices.length];
            arestas[i] = new SegmentoReta(p1, p2);
        }
        return arestas;
    }

    /**
     * Calcula os pontos de intersecção da rota com este polígono.
     * @param rota a rota a testar
     * @return String com os pontos de intersecção ou "null" se não houver
     */
    public String intersect(Route rota) {
        String resultado = "";
        Ponto ultimo = null;

        for (SegmentoReta segmento : rota.segmentos()) {
            for (SegmentoReta aresta : arestas()) {
                Ponto p = segmento.intersect(aresta);
                if (p != null) {
                    if (ultimo == null || p.getX() != ultimo.getX() || p.getY() != ultimo.getY()) {
                        if (!resultado.isEmpty()) resultado += " ";
                        resultado += p.toString();
                        ultimo = p;
                    }
                }
            }
        }
        return resultado.isEmpty() ? "null" : resultado;
    }
}