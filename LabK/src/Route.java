/**
 * Representa uma rota definida por um conjunto ordenado de pontos
 * O percurso entre cada dois pontos consecutivos é retilíneo
 * @author Francisco Neves
 * @version 22/03/2026
 */
public class Route {
    private Ponto[] pontos;

    public Route(Ponto[] pontos) {
        this.pontos = pontos;
    }

    public Ponto[] getPontos() {
        Ponto[] copia = new Ponto[pontos.length];
        for (int i = 0; i < pontos.length; i++)
            copia[i] = pontos[i];
        return copia;
    }

    public SegmentoReta [] segmentos (){
        SegmentoReta[] segmentos = new SegmentoReta[pontos.length - 1];
        for (int i = 0; i < segmentos.length; i++)
            segmentos[i] = new SegmentoReta(pontos[i], pontos[i + 1]);
        return segmentos;
    }

    public double comprimento() {
        double total = 0;
        for (int i = 0; i < pontos.length - 1; i++)
            total += pontos[i].distancia(pontos[i + 1]);
        return total;
    }
    /**
     * Calcula os pontos de intersecção da rota com um segmento de reta
     * Corrigido para remover o separador "[]" e evitar duplicados em vértices.
     */
    public String intersect(SegmentoReta s) {
        String result = "";
        Ponto ultimo = null;

        for (int i = 0; i < pontos.length - 1; i++) {
            SegmentoReta parte = new SegmentoReta(pontos[i], pontos[i + 1]);
            Ponto p = parte.intersect(s);
            if (p != null) {
                if (ultimo == null || p.getX() != ultimo.getX() || p.getY() != ultimo.getY()) {
                    if (!result.isEmpty()) result += " ";
                    result += p.toString();
                    ultimo = p;
                }
            }
        }
        return result.isEmpty() ? "null" : result;
    }
}