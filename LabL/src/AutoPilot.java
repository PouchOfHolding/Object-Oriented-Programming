/**
 * Representa um sistema autopilot que vai calcular a velocidade e tempo
 * precisa de um ponto de partida e uma chegada, designnado de start e finish
 * vai considerar a influencia do vento
 */
public class AutoPilot {
    private Ponto start;
    private Ponto finish;

    /**
     * cria um autopilot com o start e o finish
     * @param a ponto start
     * @param b ponto finish
     */
    public AutoPilot(Ponto a, Ponto b){
        this.start= a;
        this.finish= b;
    }

    /**
     * calcula a velocidade do vetor vento
     * @return a velocidade que o veiculo deve manter
     */
    public Vetor speed(Vetor w, double t) {
        Vetor r = new Vetor(finish.getX() - start.getX(), finish.getY() - start.getY());
        // Se a entrada for apenas 'w', temos de calcular 'wt'
        Vetor wt = w.mult(t);
        return r.sub(wt).mult(1.0 / t);
    }

    /**
     * calcula o tempo de viagem ate ao destino tendo em considereção o vento
     * @return o tempo mais ou menos de viagem
     */
    public double time(double vl) {
        double r = start.distancia(finish); // Distância entre A e B [cite: 17, 39]
        return r / vl; // t = r / vl
    }

}
