package navPort;

import Geometry.*;

import java.util.List;
import java.util.ArrayList;


public class Porto {
    private String namePorto;
    private Ponto localizacaoPorto; //falta colocar no UML
    private List<Navio> listNavios;


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

    public String getNomePorto(){
        return this.namePorto;
    }

    public Ponto getLocalPorto(){
        return this.localizacaoPorto;
    }
 }
