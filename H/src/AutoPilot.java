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
     * @param windSpeed o vetor de velocidade sobre o avião
     * @param time o tempo de viagem
     * @return a velocidade que o veiculo deve manter
     */
    public Vetor speed(Vetor windSpeed, double time){
        Vetor r = new Vetor(finish).sub(new Vetor(start));
        return r.sub(windSpeed).mult(1.0 / time);
    }

    /**
     * calcula o tempo de viagem ate ao destino tendo em considereção o vento
     * @param windSpeed e velocidade do vento sobre o avião
     * @param linearSpeed velocidade do aviãp
     * @return o tempo mais ou menos de viagem
     */
    public double time(Vetor windSpeed, double linearSpeed){
        Vetor r = new Vetor(finish).sub(new Vetor(start));
        return r.sub(windSpeed).distancia() / linearSpeed;
    }

}
