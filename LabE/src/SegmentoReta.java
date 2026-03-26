/**
 * Representa o Segmento de Reta defenido por um Ponto e um Vetor
 * @author Francisco Neves
 * @version 23/02/2026
 * @inv O Segmento de reta não pode ter comprimento nulo
 * @see "https://mundoeducacao.uol.com.br/matematica/distancia-entre-dois-pontos.htm"
 */

class SegmentoReta{
    private Ponto p;
    private Vetor v;

    /**
     * vai Construir um Segmento de Reta a partir de um Ponto e um Vetor
     * @param p Represente o extremo inicial
     * @param v Representa o vetor que vai definir o comprimento e direção do segmento
     */
    public SegmentoReta(Ponto p, Vetor v){
        if (v.distancia()<= 1e-10){
            System.out.print("SegmentoReta:iv\n");
            System.exit(0);
            /**
             * verifica a condição invariante de instÂncia, termine o programa se for violada
             */
        }
        this.p=p;
        this.v=v;
    }

    public SegmentoReta(Ponto p1, Ponto p2){
        this.p=p1;
        double vx = p2.getX() - p1.getX();
        double vy = p2.getY() - p1.getY();

        if (Math.sqrt(vx * vx + vy * vy) <= 1e-10) {
            System.out.print("SegmentoReta:iv\n");
            System.exit(0);
        }
        this.v = new Vetor(vx, vy);
    }

    public double comprimento(){
        return v.distancia();
        /**
         * Calcula o comprimento total baseado na distância do vetor.
         * * @return O valor numérico do comprimento.
         * @see <a href="https://www.datacamp.com/pt/tutorial/euclidean-distance"
         */
    }

    public Ponto intersect(Vetor v2) {
        double x1 = this.p.getX();
        double y1 = this.p.getY();
        double vx = this.v.getX();
        double vy = this.v.getY();
        double v2x = v2.getX();
        double v2y = v2.getY();

        double det = vx * v2y - vy * v2x;

        if (Math.abs(det) < 1e-10) return null; // Paralelos


        double t = (y1 * v2x - x1 * v2y) / det;

        if (t < 0 || t > 1) return null;

        return new Ponto(x1 + t * vx, y1 + t * vy);
/**
 * Calcula o ponto de interseção entre o vetor atual e um segundo vetor.
 * @inv O resultado deve ser um objeto Ponto válido ou null se não houver interseção.
 * @param v2 O segundo vetor com o qual se pretende calcular a interseção.
 * @return Um novo objeto Ponto contendo as coordenadas da interseção,
 * ou null se os vetores forem paralelos ou não se cruzarem no intervalo.
 */
    }
    public String toString(){
        double ax =p.getX();
        double ay= p.getY();
        double bx= v.getX() + p.getX();
        double by= v.getY() + p.getY();
        /**
         * Devolve uma representação textual do segmento de reta.
         * O segmento é formatado como "sr((x1,y1); (x2,y2))", garantindo que
         * o ponto mais próximo da origem (0,0) apareça primeiro.
         */

        double distanciaA = Math.sqrt(Math.pow(ax,2)+Math.pow(ay,2));
        double distanciaB = Math.sqrt(Math.pow(bx,2)+Math.pow(by,2));

        if (distanciaA < distanciaB){
            return String.format("sr((%.2f,%.2f); (%.2f,%.2f))",ax,ay, bx, by);
        }
        else {
            return String.format("sr((%.2f,%.2f); (%.2f,%.2f))",bx,by, ax, ay);
        }
    }
}


// https://www.youtube.com/watch?v=u3BGdOunOTI