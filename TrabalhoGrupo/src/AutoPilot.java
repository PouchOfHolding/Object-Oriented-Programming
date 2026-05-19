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
    public Vetor speed(SegmentoReta s, Vetor w, double vl) {
        Vetor vSolo = s.getVetor().mult(vl / s.comprimento());
        return vSolo.sub(w);
    }
    /**
     * Calcula o tempo necessário para percorrer a distância entre start e finish
     * @param vl a velocidade linear do avião
     * @return o tempo mais ou menos de viagem
     */
    public double time(double vl) {
        return start.distancia(finish) / vl;
    }

}
