package Geometry;

/**
 * Representa um Segmento de Reta definido por um Geometry.Ponto e um Geometry.Vetor posição
 * @author Luís Moreira
 * @version 22/03/2026
 * @inv O comprimento do Segmento de Reta não pode ser zero
 */
public class SegmentoReta {
    private Ponto p;
    private Vetor v;

    /**
     * Constrói um Segmento de Reta a partir de um Geometry.Ponto e um Geometry.Vetor
     * @param p Representa o extremo inicial
     * @param v Representa o vetor que define o comprimento e direção do segmento
     */
    public SegmentoReta(Ponto p, Vetor v) {
        this.p = p;
        this.v = v;
        this.invar();
    }

    /**
     * Constrói um Segmento a partir de dois Pontos
     * @param p1 Representa o Geometry.Ponto de origem
     * @param p2 Representa o Geometry.Ponto Final
     */
    public SegmentoReta(Ponto p1, Ponto p2) {
        this.p = p1;

        double vx = p2.getX() - p1.getX();
        double vy = p2.getY() - p1.getY();

        if (Math.abs(vx) < 1e-9 && Math.abs(vy) < 1e-9) {
            System.out.println("Geometry.SegmentoReta:iv");
            System.exit(0);
        }

        this.v = new Vetor(vx, vy);
        this.invar();
    }

    /**
     * Verifica a condição invariante de instância, termina o programa se for violada
     */
    private void invar() {
        if (v.dist() < 1e-9) {
            System.out.println("Geometry.SegmentoReta:iv");
            System.exit(0);
        }
    }

    /**
     * Retorna o ponto de origem do segmento
     * @return O Geometry.Ponto inicial do segmento
     */
    public Ponto getP() {
        return p;
    }

    /**
     * Retorna o vetor direção do segmento
     * @return O Geometry.Vetor que define a direção e comprimento do segmento
     */
    public Vetor getV() {
        return v;
    }

    /**
     * Calcula a interseção deste Segmento com outro Segmento de Reta
     * @param other O outro segmento de reta
     * @return O Geometry.Ponto de interseção ou null se não existir interseção válida
     */
    public Ponto intersect(SegmentoReta other) {
        double p1x = this.p.getX(), p1y = this.p.getY();
        double v1x = this.v.getX(), v1y = this.v.getY();
        double p2x = other.getP().getX(), p2y = other.getP().getY();
        double v2x = other.getV().getX(), v2y = other.getV().getY();

        double denom = v1x * v2y - v1y * v2x;
        if (Math.abs(denom) < 1e-9) return null;

        double dx = p2x - p1x;
        double dy = p2y - p1y;

        double t = (dx * v2y - dy * v2x) / denom;
        double u = (dx * v1y - dy * v1x) / denom;

        if (t >= -1e-9 && t <= 1 + 1e-9 && u >= -1e-9 && u <= 1 + 1e-9)
            return new Ponto(p1x + t * v1x, p1y + t * v1y);

        return null;
    }

    /**
     * Calcula a interseção deste Segmento com um Geometry.Vetor Posição
     * @param v O vetor posição a intersetar
     * @return O Geometry.Ponto de interseção ou null se não existir interseção válida
     * @see "https://math.stackexchange.com/questions/25171/intersection-of-two-lines-in-2d"
     */
    public Ponto intersect(Vetor v) {
        double p1x = this.p.getX();
        double p1y = this.p.getY();
        double v1x = this.v.getX();
        double v1y = this.v.getY();

        double v2x = v.getX();
        double v2y = v.getY();

        double denom = (v1x * v2y) - (v1y * v2x);

        if (Math.abs(denom) < 1e-9) return null;

        double deltaX = -p1x;
        double deltaY = -p1y;

        double t = ((deltaX * v2y) - (deltaY * v2x)) / denom;
        double u = ((deltaX * v1y) - (deltaY * v1x)) / denom;

        if (t >= -1e-9 && t <= 1 + 1e-9 && u >= -1e-9 && u <= 1 + 1e-9) {
            double interX = p1x + (t * v1x);
            double interY = p1y + (t * v1y);
            return new Ponto(interX, interY);
        }

        return null;
    }

    /**
     * Retorna a representação em formato de String
     * @return Os extremos do segmento ordenados pela distância à origem
     */
    public String toString() {
        double x1 = p.getX();
        double y1 = p.getY();
        double x2 = x1 + v.getX();
        double y2 = y1 + v.getY();

        double dist1 = Math.sqrt(x1 * x1 + y1 * y1);
        double dist2 = Math.sqrt(x2 * x2 + y2 * y2);

        if (dist1 < dist2)
            return String.format("sr((%.2f,%.2f); (%.2f,%.2f))", x1, y1, x2, y2);
        else
            return String.format("sr((%.2f,%.2f); (%.2f,%.2f))", x2, y2, x1, y1);
    }
}