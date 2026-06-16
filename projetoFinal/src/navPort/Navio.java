package navPort;

import Geometry.figuras.Circulo;
import Geometry.Ponto;
import Geometry.Route;
import Interface.padraoState.*;


/**
 *  Classe que representa um Navio na simulação
 *  Mantém o Estado atual do Navio, a sua rota, posição, destino,
 *  assim como a velocidade linear e o tempo de saída (o tempo, desde que começa o contador da simulação,
 *  em que o Navio sai do seu Porto de origem)
 * @auhtor Grupo 7 PL1
 * @version 14/05/2026
 */
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


    /**
     * O construtor da classe Navio
     * @param cod O código de identificação do Navio ("nome do Porto de Origem" + "tempo de saída")
     * @param dest O nome do Porto de destino
     * @param tmpSaida O instante de tempo no qual o navio vai iniciar a "viagem"
     * @param velcp A velocidade linear pretendida pelo Navio durante a Rota
     */
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


    /**
     * Atribui a atualização da lógica de movimento e colisões ao estado atual do Navio
     * @param simulador A instância global do Simulador
     */
    public void atualizar(Simulador simulador){
        this.estado.atualizar(this, simulador);
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


    /**
     * Retorna o tempo em que o navio já passou efetivamente a navegar
     * @return unidaes de tempo de navegação
     */
    public int getTempoNavegando(){
        return this.tempoNavegando;
    }


    /**
     * Incrementa o cronómetro interno de viagem do Navio em uma unidade
     */
    public void incrementarTempoNavegando(){
        this.tempoNavegando++;
    }


    /**
     * Define o tempo de navegação percorrido
     * É útil quando o Navio recalcula a rota
     * @param tempo O novo tempo de navegação
     */
    public void setTempoNavegando(int tempo){
        this.tempoNavegando = tempo;
    }


    /**
     * Atribuiu uma rota calculada ao Navio
     * Se a rota for válida, coloca o Navio na posição inicial e muda o seu estado para EstadoNavegando (navegar até ao Porto Destino)
     * @param rota A rota pela qual o Navio deverá viajar
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

    /**
     * Calcula e retorna a área de interseção/colisão em torno do Navio (a área de um Circulo)
     * @return Um Círculo que representa o "radar" de colisão do Navio, ou null se não tiver posição
     */
    public Circulo getAreaIntersecao(){
        if(this.posicaoAtual == null){
            return null;
        }

        return new Circulo(this.posicaoAtual, 50.0); //50 pixeis é equivalente a 1 do raio
    }


    /**
     * Retorna as coordenadas atuais do Navio
     * @return O Ponto onde o navio se encontra
     */
    public Ponto getPosicaoAtual(){

        return this.posicaoAtual;
    }

    /**
     * Define a posição atual do Navio
     * @param p O novo Geometry.Ponto onde o navio_porto.Navio se econtra
     */
    public void setPosicaoAtual(Ponto p){
        this.posicaoAtual = p;

    }


    /**
     * Retorna o código do navio ("nome do Porto de origem" + "tempo de saída"
     * @return o código do navio em String
     */
    public String getCodigo(){

        return this.codigo;
    }


    /**
     * Retorna o instante de tempo agendado para a partida do Navio
     * @return O tempo de saída
     */
    public int getTempoSaida(){
        return this.tempoSaida;
    }


    /**
     * Retorna o nome do Porto de destino em que o Navios se dirige
     * @return A String com o nome do Porto de destino
     */
    public String getDestino(){

        return this.destino;
    }


    /**
     * Retorna a rota atribuída ao navio
     * @return A instância Route que dita o caminho do Navio
     */
    public Route getRota(){
        return this.rota;
    }


    /**
     * Retorna a velocidade linear pela qual o Navio pretende navegar
     * @return O valor da velociade linear do Navio
     */
    public double getVelocidadeLinearPretendida(){
        return this.velocidadeLinearPretendida;
    }

}
