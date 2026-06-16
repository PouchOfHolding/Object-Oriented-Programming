package Interface.padraoState;
import Geometry.figuras.Circulo;
import navPort.*;
import Geometry.*;

import java.util.List;

/**
 * Respresenta o estado do Navio a deslocar-se pela Rota, verifica potenciais colisões com outros navios
 * e deteta a sua chegada ao destino final
 * @author Grupo 7 PL1
 * @version 10/05/2026
 */
public class EstadoNavegando implements EstadoNavio {


    /**
     * Executa a lógica de navegação do Navio
     * @param navio O Navio que está a ser atualizado
     * @param simulador
     */
    @Override
    public void atualizar(Navio navio, Simulador simulador){

        //recaucular rota (obstáculos móveis)
        boolean rotaBloqueada = false;
        for(Circulo obsMovel : simulador.getObstaculosMoveis()){
            if(!obsMovel.intersect(navio.getRota()).isEmpty()){
                rotaBloqueada = true;
                break;
            }
        }

        if(rotaBloqueada){
            Porto portoDestino = simulador.getPortoByNome(navio.getDestino());
            if(portoDestino != null){
                Route novaRota = simulador.calcularRotaParaNavio(navio.getPosicaoAtual(), portoDestino.getLocalPorto(), true);
                if(novaRota != null){
                    navio.setRota(novaRota);
                    navio.setTempoNavegando(0); //reinicia a viagem da nova rota
                }
            }
        }

        // anti-colisao
        for(Navio outro :  simulador.getNaviosNoMar()){

            //ignoramos o próprio Navio e os Navios que ainda não estão na água
            if(outro != navio && outro.getPosicaoAtual() != null && !(outro.getEstado() instanceof EstadoEspera)){

                //calculamos a distancia entre os dois navios
                double dx = navio.getPosicaoAtual().getX() - outro.getPosicaoAtual().getX();
                double dy = navio.getPosicaoAtual().getY() - outro.getPosicaoAtual().getY();
                double dist = Math.sqrt( (dx * dx) + (dy * dy) );

                //isto é a "area de interseção"
                if(dist <= navio.getAreaIntersecao().getRaio()){

                    //quando dois navios estão nesta "área" o navio com o numero de partida menor espera que o outro passe
                    if(navio.getTempoSaida() < outro.getTempoSaida()){

                        //muda para o EstadoAguardando o que faz com que não "atualize" o movimento desse Navio
                        navio.setEstado(new EstadoAguardando());
                        return;
                    }
                }
            }
        }

        //o caminho agora está livre


        //o "cronómetro" do Navio só avança se ele efetivamente andar
        navio.incrementarTempoNavegando();

        //pede á Rota as coordenadas exatas com base no tempo que já navegou e na sua velocidade
        Ponto novaPosicao = navio.getRota().position(navio.getTempoNavegando(), navio.getVelocidadeLinearPretendida());

        //Atualiza as coordenadas no mapa
        navio.setPosicaoAtual(novaPosicao);


        //verificação de chegada ao destino

        Ponto[] pontosDaRota = navio.getRota().getPontos();
        Ponto destinoFinal = pontosDaRota[pontosDaRota.length - 1]; //ultimo ponto do array é o Porto de destino

        //compara a posição atual com a posição final
        if( Math.abs(novaPosicao.getX() - destinoFinal.getX()) < 1e-9  && Math.abs(novaPosicao.getY() - destinoFinal.getY()) < 1e-9){

            //o Navio chegou ao destino, então para
            navio.setEstado(new EstadoEspera());

        }

    }
}
