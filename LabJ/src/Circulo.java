/**
 * Representa um círculo definido por um centro e um raio positivo.
 * @author Francisco Neves
 * @version 23/03/2026
 * @inv O raio deve ser maior que zero
 */
public class Circulo {
    private Ponto centro;
    private double raio;

    /**
     * Verifica se o raio é válido.
     * @param raio o raio a verificar
     */
    private void invariante(double raio) {
        if (raio <= 1e-9) {
            System.out.print("Circulo:iv\n");
            System.exit(0);
        }
    }

    /**
     * Cria um círculo com centro e raio.
     * @param centro ponto central do círculo
     * @param raio raio do círculo
     */
    public Circulo(Ponto centro, double raio) {
        invariante(raio);
        this.centro = centro;
        this.raio = raio;
    }

    /**
     * Devolve o centro do círculo.
     * @return o ponto central
     */
    public Ponto getCentro() { return centro; }

    /**
     * Devolve o raio do círculo.
     * @return o raio
     */
    public double getRaio() { return raio; }

    /**
     * Calcula os pontos de intersecção da rota com este círculo.
     * @param rota a rota a testar
     * @return String com os pontos de intersecção ou "null" se não houver
     */
    public String intersect(Route rota) {
        String result = "";
        Ponto lastAdded = null;

        for (SegmentoReta segmento : rota.segmentos()) {
            Ponto p1 = segmento.getPonto();
            double dx = segmento.getVetor().getX();
            double dy = segmento.getVetor().getY();
            double fx = p1.getX() - centro.getX();
            double fy = p1.getY() - centro.getY();

            double a = dx*dx + dy*dy;
            double b = 2*(fx*dx + fy*dy);
            double c = fx*fx + fy*fy - raio*raio;
            double disc = b*b - 4*a*c;

            if (disc < -1e-9) continue;
            if (disc < 0) disc = 0;
            double sqrtDisc = Math.sqrt(disc);

            double t1 = (-b - sqrtDisc) / (2*a);
            double t2 = (-b + sqrtDisc) / (2*a);

            if (t1 >= -1e-9 && t1 <= 1+1e-9) {
                Ponto p = new Ponto(p1.getX() + t1*dx, p1.getY() + t1*dy);
                if (lastAdded == null || Math.abs(p.getX() - lastAdded.getX()) > 1e-9 || Math.abs(p.getY() - lastAdded.getY()) > 1e-9) {
                    if (!result.isEmpty()) result += " ";
                    result += p.toString();
                    lastAdded = p;
                }
            }

            if (t2 >= -1e-9 && t2 <= 1+1e-9 && Math.abs(t2 - t1) > 1e-9) {
                Ponto p = new Ponto(p1.getX() + t2*dx, p1.getY() + t2*dy);
                if (lastAdded == null || Math.abs(p.getX() - lastAdded.getX()) > 1e-9 || Math.abs(p.getY() - lastAdded.getY()) > 1e-9) {
                    if (!result.isEmpty()) result += " ";
                    result += p.toString();
                    lastAdded = p;
                }
            }
        }
        return result.isEmpty() ? "null" : result;
    }
}