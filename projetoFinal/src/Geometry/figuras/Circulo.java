package Geometry.figuras;

import Geometry.Ponto;
import Geometry.Route;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa um círculo definido por um centro e um raio
 * @author Grupo 7 PL1
 * @version 23/03/2026
 * @inv O raio tem de ser maior que zero
 */
public class Circulo extends Figura {

    private Ponto centro;
    private double raio;

    /**
     * Constrói um Círculo a partir de um centro e um raio
     * @param centro O ponto central do círculo
     * @param raio   O raio do círculo
     */
    public Circulo(Ponto centro, double raio) {
        this.centro = centro;
        this.raio = raio;
        invar();
    }

    /**
     * Verifica a invariante de instância — termina o programa se o raio for <= 0
     */
    private void invar() {
        if (raio <= 1e-9) {
            System.out.println("Geometry.Figuras.Circulo:iv");
            System.exit(0);
        }
    }

    /**
     * Calcula os pontos de interseção do círculo com uma rota.
     * Resolve a equação de 2º grau resultante da interseção do segmento com o círculo, encontrando os pontos de entrada e saída.
     * @param r A rota a intersetar com o círculo
     * @return Lista de pontos de interseção ordenados desde o início da rota
     * @see "https://en.wikipedia.org/wiki/Line%E2%80%93sphere_intersection"
     */
    @Override
    public List<Ponto> intersect(Route r) {
        List<Ponto> intersecoes = new ArrayList<>();
        Ponto[] pontos = r.getPontos();

        for (int i = 0; i < pontos.length - 1; i++) {
            Ponto p1 = pontos[i];
            Ponto p2 = pontos[i + 1];

            // Geometry.Vetor direção do segmento
            double dx = p2.getX() - p1.getX();
            double dy = p2.getY() - p1.getY();

            // Geometry.Vetor de p1 ao centro
            double fx = p1.getX() - centro.getX();
            double fy = p1.getY() - centro.getY();

            // Coeficientes da equação at² + bt + c = 0
            double a = dx * dx + dy * dy;
            double b = 2 * (fx * dx + fy * dy);
            double c = fx * fx + fy * fy - raio * raio;

            double discriminante = b * b - 4 * a * c;

            if (discriminante < -1e-9) continue; // sem interseção

            if (discriminante < 0) discriminante = 0; // tangente

            double sqrtDisc = Math.sqrt(discriminante);

            double t1 = (-b - sqrtDisc) / (2 * a);
            double t2 = (-b + sqrtDisc) / (2 * a);

            // Só adiciona se t estiver dentro do segmento [0,1]
            if (t1 >= -1e-9 && t1 <= 1 + 1e-9)
                intersecoes.add(new Ponto(p1.getX() + t1 * dx, p1.getY() + t1 * dy));

            if (t2 >= -1e-9 && t2 <= 1 + 1e-9 && Math.abs(t2 - t1) > 1e-9)
                intersecoes.add(new Ponto(p1.getX() + t2 * dx, p1.getY() + t2 * dy));
        }

        return intersecoes;
    }

    /**
     * Método para retornar o centro do Círculo
     * @return o centro do Círculo
     */
    public Ponto getCentro(){
        return this.centro;
    }


    /**
     * Método para retornar o valor do raio do Círculo
     * @return o raio do Círculo
     */
    public double getRaio(){
        return this.raio;
    }
}