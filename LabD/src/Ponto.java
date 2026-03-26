class Ponto{
    private double x,y;
    public Ponto (double x, double y){
        this.x=x;
        this.y=y;

    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String toString(){
        return "("+this.x+","+this.y+")";
    }
}