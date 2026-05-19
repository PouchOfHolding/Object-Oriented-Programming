/**
 * Representa um retângulo com lados opostos iguais e diagonais iguais.
 * @author Francisco Neves
 * @version 23/03/2026
 * @inv Lados opostos iguais e diagonais com o mesmo comprimento
 */
public class Retangulo extends Poligno {

    /**
     * Verifica se os 4 vértices formam um retângulo válido.
     * @param a primeiro vértice
     * @param b segundo vértice
     * @param c terceiro vértice
     * @param d quarto vértice
     */
    private void invariante(Ponto a, Ponto b, Ponto c, Ponto d) {
        double ab = a.distancia(b), bc = b.distancia(c);
        double cd = c.distancia(d), da = d.distancia(a);
        double diag1 = a.distancia(c), diag2 = b.distancia(d);

        if (!(Math.abs(ab - cd) < 1e-9 && Math.abs(bc - da) < 1e-9 && Math.abs(diag1 - diag2) < 1e-9)) {
            System.out.print("Retangulo:iv\n");
            System.exit(0);
        }
    }

    /**
     * Cria um retângulo a partir de 4 vértices.
     * @param a primeiro vértice
     * @param b segundo vértice
     * @param c terceiro vértice
     * @param d quarto vértice
     */
    public Retangulo(Ponto a, Ponto b, Ponto c, Ponto d) {
        super(new Ponto[]{a, b, c, d});
        invariante(a, b, c, d);
    }
}