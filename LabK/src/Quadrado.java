/**
 * Representa um quadrado com todos os lados iguais e diagonais iguais.
 * @author Francisco Neves
 * @version 23/03/2026
 * @inv Todos os 4 lados iguais e diagonais com o mesmo comprimento
 */
public class Quadrado extends Poligno {

    /**
     * Verifica se os 4 vértices formam um quadrado válido.
     * @param a primeiro vértice
     * @param b segundo vértice
     * @param c terceiro vértice
     * @param d quarto vértice
     */
    private void invariante(Ponto a, Ponto b, Ponto c, Ponto d) {
        double ab = a.distancia(b), bc = b.distancia(c);
        double cd = c.distancia(d), da = d.distancia(a);

        if (!(Math.abs(ab - bc) < 1e-9 && Math.abs(bc - cd) < 1e-9 && Math.abs(cd - da) < 1e-9)) {
            System.out.print("Quadrado:iv\n");
            System.exit(0);
        }
    }
    /**
     * Cria um quadrado a partir de 4 vértices.
     * @param a primeiro vértice
     * @param b segundo vértice
     * @param c terceiro vértice
     * @param d quarto vértice
     */
    public Quadrado(Ponto a, Ponto b, Ponto c, Ponto d) {
        super(new Ponto[]{a, b, c, d});
        invariante(a, b, c, d);
    }
}