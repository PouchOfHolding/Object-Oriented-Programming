package navPort;

import Geometry.*;

import java.util.List;
import java.util.ArrayList;

/**
 * Classe que representa um Porto na simulação
 * Atua como ponto de partida e destino para os Navios
 * Guarda um Lista de Navios
 * @author Grupo 7 PL1
 * @version 13/05/2026
 */
public class Porto {
    private String namePorto;
    private Ponto localizacaoPorto; //falta colocar no UML
    private List<Navio> listNavios;


    /**
     * Construtor da classe Porto
     * @param nomePorto O nome de cada Porto
     * @param locaPorto As coordenadas de cada Porto
     */
    public Porto(String nomePorto, Ponto locaPorto){
        this.namePorto =nomePorto;
        this.localizacaoPorto = locaPorto;
        this.listNavios = new ArrayList<>();
        
    }


    /**
     * Adicionamos um navio á Lista de Navios
     * @param navio
     */
    public void addNavio(Navio navio){
        this.listNavios.add(navio);
    }


    /**
     * Verifica a lista de Navios que estão á espera para sair do navio_porto.Porto e remove aqueles
     * cujo o "horário de saida" coincide com o tempo atual
     * @param tempoAtual O tempo atual do relógio do navPort.Simulador,
     * @return Uma lista com os navios que iniciaram a vigagem e sairam do navio_porto.Porto
     */
    public List<Navio> removeNavio(int tempoAtual){

        List<Navio> naviosQueVaoPartir = new ArrayList<>(); //Lista dos Navios que têm de sair agora

        for(Navio navio : this.listNavios){  //para cada navio que está na listNavios vamos

            if(navio.getTempoSaida() == tempoAtual){ //por exemplo o navio A13, quer dizer que ele tem de sair no segundo 13

                naviosQueVaoPartir.add(navio); //pomos o navio na tal lista que têm de sair

            }

        }

        this.listNavios.removeAll(naviosQueVaoPartir); //remove todos os navios da listNavios que sairam (os que tão na Lista naviosQueVaoPartir

        return naviosQueVaoPartir; //retorna os que têm de sair

    }

    /**
     * Método que retorna a lista de navios que estão á espera para sair do determinado navio_porto.Porto
     * @return A lista de navios que ainda não sairam
     */
    public List<Navio> getNaviosEmEspera(){
        return this.listNavios;
    }


    /**
     * Método que retorna o nome do Porto
     * @return O nome do Porto
     */
    public String getNomePorto(){
        return this.namePorto;
    }


    /**
     * Método que retorna a localização (coordenadas) do Porto
     * @return As coordenadas do Porto
     */
    public Ponto getLocalPorto(){
        return this.localizacaoPorto;
    }
 }
