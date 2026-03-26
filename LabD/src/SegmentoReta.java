class SegmentoReta{
    private Ponto p;
    private Vetor v;

    public SegmentoReta(Ponto p, Vetor v){
        if (v.distancia()<= 0){
            System.out.print("SegmentoReta:iv\n");
            System.exit(0);
        }
        this.p=p;
        this.v=v;
    }
    public double comprimento(){
        return v.distancia();
    }
    public String toString(){
        double ax =p.getX();
        double ay= p.getY();
        double bx= v.getX() + p.getX();
        double by= v.getY() + p.getY();

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