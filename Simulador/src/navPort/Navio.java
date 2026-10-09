package navPort;

import Geometry.figuras.Circulo;
import Geometry.Ponto;
import Geometry.Route;
import Interface.padraoState.*;

import java.util.List;

public class Navio {
    private String codigo;//
    private Ponto posicaoAtual;
    private String destino;//
    private Route rota;
    private double velocidadeLinearPretendida;//
    private int tempoSaida;//

    private int indiceProximoPonto;

    private int tempoNavegando;

    private EstadoNavio estado;



    public Navio(String cod, String dest, int tmpSaida, double velcp){
        this.codigo = cod;
        this.destino = dest;
        this.tempoSaida = tmpSaida;
        this.velocidadeLinearPretendida = velcp;

        this.rota = null; // como não saiu ainda não tem rota
        this.posicaoAtual = null; //ainda não saiu não tem posição

        this.estado = new EstadoEspera(); //quando um navio "nasce" ele fica sempre "á espera"

        this.tempoNavegando = 0;

    }


    public void atualizar(List<Navio> frota){
        this.estado.atualizar(this, frota);
    }

    /**
     * Altera o Estado atual do Navio
     * @param novoEstado O novo Estado que o Navio vai assumir
     */
    public void setEstado(EstadoNavio novoEstado){
        this.estado = novoEstado;
    }


    /**
     * Retorna o Estado em que o Navio se econtra neste momento
     * @return O Estado atual do Navio
     */
    public EstadoNavio getEstado(){

        return this.estado;
    }


    public int getTempoNavegando(){
        return this.tempoNavegando;
    }

    /**
     *
     */
    public void incrementarTempoNavegando(){
        this.tempoNavegando++;
    }

    public void atualizarNavio(List<Navio> frota){
        this.estado.atualizar(this, frota);
    }



    /**
     * Dá a a rota/cmainho mais curto(a) calculada pelo navPort.Simulador quando sair do navio_porto.Porto
     * @param rota
     */
    public void setRota(Route rota){
        this.rota = rota;

        if(this.rota != null && rota.getPontos().length >0){
            this.posicaoAtual = this.rota.getPontos()[0];
            this.indiceProximoPonto = 1;

            //Agora que deram a rota, o Navio começa a navegar
            this.setEstado(new EstadoNavegando());
        }

    }

    public Circulo getAreaIntersecao(){
        if(this.posicaoAtual == null){
            return null;
        }

        return new Circulo(this.posicaoAtual, 1.0);
    }


    /*public void atualizarPosicao(double deltaT, Geometry.Vetor corrente){
        if(this.emEspera==true || this.rota == null){
            return;
        }

        Geometry.Ponto[] pontosRota = this.rota.getPontos();


    }

     */
    
    public Ponto getPosicaoAtual(){

        return this.posicaoAtual;
    }

    /**
     * Define a opição atual do navio_porto.Navio
     * @param p O novo Geometry.Ponto onde o navio_porto.Navio se econtra
     */
    public void setPosicaoAtual(Ponto p){
        this.posicaoAtual = p;

    }

    public String getCodigo(){

        return this.codigo;
    }

    public int getTempoSaida(){
        return this.tempoSaida;
    }

    public String getDestino(){

        return this.destino;
    }

    public Route getRota(){
        return this.rota;
    }

    public double getVelocidadeLinearPretendida(){
        return this.velocidadeLinearPretendida;
    }

}
