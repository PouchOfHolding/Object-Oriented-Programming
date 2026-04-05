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

    /**
     * Determina a posição exata (x, y) do avião após um tempo 't' de voo.
     * @param vl Velocidade linear constante (km/h, m/s, etc.)
     * @param t Tempo decorrido desde o início da viagem
     * @return O Ponto onde o avião se encontra no instante t
     */
    public Ponto ondeesta(double vl, double t) {
        double tempoGasto = 0;
        for (SegmentoReta s : segmentos()) {
            // tempo que vai ser passado num determinado segmento
            double tempoSeg = s.comprimento() / vl;
            if (tempoGasto + tempoSeg >= t - 1e-9) {
                double tempoNesteSeg = t - tempoGasto;
                // Posição = PontoInicial + (VetorVelocidadeSolo * tempoNesteSeg)
                Vetor vSolo = s.getVetor().mult(vl / s.comprimento());
                return new Ponto(s.getPonto().getX() + vSolo.getX() * tempoNesteSeg,
                        s.getPonto().getY() + vSolo.getY() * tempoNesteSeg);
            }
            tempoGasto += tempoSeg;
        }
        return pontos[pontos.length - 1];
    }


}