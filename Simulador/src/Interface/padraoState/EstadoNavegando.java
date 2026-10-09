package Interface.padraoState;
import navPort.*;
import Geometry.*;

import java.util.List;

/**
 * Respresenta o estado do Navio a deslocar-se pela Rota, verifica potenciais colisões com outros navios
 * e deteta a sua chegada ao destino final
 */
public class EstadoNavegando implements EstadoNavio {


    /**
     * Executa a lógica de navegação do Navio
     * @param navio O Navio que está a ser atualizado
     * @param frota A lista de todos os Navios que estão "no mar"
     */
    @Override
    public void atualizar(Navio navio, List<Navio> frota){

        // anti-colisao
        for(Navio outro :  frota){

            //ignoramos o próprio Navio e os Navios que ainda não estão na água
            if(outro != navio && outro.getPosicaoAtual() != null){

                //calculamos a distancia entre os dois navios
                double dx = navio.getPosicaoAtual().getX() - outro.getPosicaoAtual().getX();
                double dy = navio.getPosicaoAtual().getY() - outro.getPosicaoAtual().getY();
                double dist = Math.sqrt( (dx * dx) + (dy * dy) );

                //isto é a "area de interseção"
                if(dist <= 20.0){

                    //quando dois navios estão nesta "área" o navio com o numero de partida menor espera que o outro passe
                    if(navio.getCodigo().compareTo(outro.getCodigo()) < 0){

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
