/**
 * Representa um triângulo definido por 3 vértices não colineares.
 * @author Francisco Neves
 * @version 23/03/2026
 * @inv Os 3 vértices não podem ser colineares (área != 0)
 */
public class Triangulo extends Poligno {
    /**
     * Verifica se os 3 vértices são colineares.
     * @param a primeiro vértice
     * @param b segundo vértice
     * @param c terceiro vértice
     */
    private void invariante(Ponto a, Ponto b, Ponto c) {
        double parte1 = (b.getX()-a.getX())*(c.getY()-a.getY());
        double parte2 = (c.getX()-a.getX())*(b.getY()-a.getY());
        double area = parte1 - parte2;

        if (Math.abs(area) <= 1e-9) {
            System.out.print("Triangulo:iv\n");
            System.exit(0);
        }
    }
    /**
     * Cria um triângulo a partir de 3 pontos não colineares.
     * @param a primeiro vértice
     * @param b segundo vértice
     * @param c terceiro vértice
     */
    public Triangulo(Ponto a, Ponto b, Ponto c) {
        super(new Ponto[]{a, b, c});
        invariante(a, b, c);
    }
}