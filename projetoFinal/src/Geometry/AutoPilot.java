package Geometry;

/**
 * A classe Geometry.AutoPilot calcula a velocidade vetorial e o tempo necessários
 * para um avião se deslocar em linha reta entre dois pontos
 * @author Grupo 7 PL1
 * @version 06/04/2026
 */
public class AutoPilot {
    private Ponto pA;
    private Ponto pB;

    /**
     * Construtor de Geometry.AutoPilot com as as coordenadas do início e do destino
     * @param pA coordenada do início
     * @param pB coordenada do destino
     */
    public AutoPilot (Ponto pA, Ponto pB) {
        this.pA = pA;
        this.pB = pB;
    }


    /**
     * Calcula a velocidade vetorial necessária para ir de pA a pB num dado tempo,
     * compensando a velocidade do vento
     * @param windSpeed O vetor velocidade do vento
     * @param time O tempo disponível para a viagem
     * @return O vetor velocidade necessário para chegar ao destino
     */
    public Vetor speed(Vetor windSpeed, double time){
        double rx = pB.getX() - pA.getX();
        double ry = pB.getY() - pA.getY();

        Vetor r = new Vetor(rx,ry);

        Vetor vSolo = r.mult(1.0 / time);

        return vSolo.add(windSpeed.mult(-1.0));

    }


    /**
     * Calcula o tempo necessário para viajar de pA a pB a uma velocidade linear constante
     * @param vl A velocidade linear do avião
     * @return O tempo necessário para efetuar a viagem
     */
    public double time(double vl) {
        //Calcular o vetor posição r (de A para B)
        double rx = pB.getX() - pA.getX();
        double ry = pB.getY() - pA.getY();
        Vetor r = new Vetor(rx, ry);

        // Aplicar a fórmula: t = |r - windVector| / linearSpeed
        // windSpeed é tratado como o deslocamento provocado pelo vento (wt)
        return r.dist()/vl;
    }
}
