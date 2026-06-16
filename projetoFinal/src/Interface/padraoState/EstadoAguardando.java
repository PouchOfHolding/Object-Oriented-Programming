package Interface.padraoState;
import navPort.*;

import java.util.List;

/**
 * Representa o estado de paragem "temporária" do Navio
 * Ou seja, é o caso quando depois de o Navio ter saído do Porto e chega um momento
 * em que tem de "ceder passagem" a outro Navio
 * @author Grupo 7 PL1
 * @version 10/05/2026
 */
public class EstadoAguardando implements EstadoNavio{

    /**
     * Verifica se o outro Navio já se afastou da "area de interseção"
     * Nao atualiza a posição do navio, mantendo-o "parado" no mapa
     * @param navio O navio que está "parado" a aguardar que o outro Navio já não estja dentro da "área de interseção"
     * @param simulador A instância global do Simulador para aceder á lista de Navios que estão no EstadoNavegando
     */
    @Override
    public void atualizar(Navio navio, Simulador simulador){
        boolean caminhoLivre = true;

        //verifica se o Navio tem exatamente 0 Navios com prioridade na sua "are de interceção"
        for(Navio outro : simulador.getNaviosNoMar()){
            if(outro != navio && outro.getPosicaoAtual() != null && !(outro.getEstado() instanceof EstadoEspera)){
                double dx = navio.getPosicaoAtual().getX() - outro.getPosicaoAtual().getX();
                double dy = navio.getPosicaoAtual().getY() - outro.getPosicaoAtual().getY();
                double dist = Math.sqrt( (dx * dx) + (dy * dy) );

                //a mesma coisa que no EstadoNavegando
                if( dist <= navio.getAreaIntersecao().getRaio()){
                    if(navio.getTempoSaida() < outro.getTempoSaida()){
                        caminhoLivre = false;
                        break;
                    }
                }
            }
        }

        //se depois disso tudo não encontrou ninguem dentro da "area de interceção", ele avança
        if(caminhoLivre){

            //muda outravez para o EstadoNavegando e continua pelo "mar"
            navio.setEstado(new EstadoNavegando());
        }
    }
}
