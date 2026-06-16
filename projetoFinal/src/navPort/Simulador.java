package navPort;

import Geometry.*;
import Geometry.figuras.Circulo;
import Geometry.figuras.Figura;
import Interface.padraoState.EstadoEspera;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections; //precisamos disto para o método shuffle para "baralhar" os indices das rotas para colocar os obst moveis

//Inclui o Padrao Singleton -> Garante que é impossível alguém criar dois simuladores a correr ao mesmo tempo. Só pode existir um


/**
 * Classe princiapl do "Modelo" que gere todo o ecossistema da simulação (assim como a matemática)
 * Implementa o Pdrão de Projeto Singletorn para garantir que existe apenas uma única instância global do Simulador
 * a correr em simultâneo
 * @author Grupo 7 PL1
 * @version 13/05/2026
 * @see
 */
public class Simulador { //É o nosso "Model", é aqui que vamos buscar toda a lógica do que queremos (guardar dados e fazer a matemática)

    private static Simulador instanciaUnica; //variável que guarda a úncia instância do mundo

    private List<Porto> portos;
    private List<Navio> naviosNoMar;
    private List<Route> rotas;
    private List<Figura> obstaculosFixos;
    private int tempoAtual;
    private Vetor correnteAtual;
    private List<Circulo> obstaculosMoveis;

    // O contrutor neste caso vais ser privado, pois assim ninguém pode fazer um "new Simulador" de fora

    /**
     * Construtoor privado para garantir a aplicação do Padrão de Projeto Singleton
     * Previne a instaciação direta a apartir de classes externas
     * @param corrente A corrente global inicial
     */
    private Simulador(Vetor corrente){
        this.correnteAtual =corrente;
        this.tempoAtual = 0;
        this.portos = new ArrayList<>();
        this.naviosNoMar = new ArrayList<>();
        this.rotas = new ArrayList<>();
        this.obstaculosFixos = new ArrayList<>();
        this.obstaculosMoveis = new ArrayList<>();

    }


    /**
     * Retorna a instância Única do Simulador (Padrão Singleton)
     * Se a instancia ainda não exisitir, cria-a utilziando o vetor de corrente fornecido
     * @param corrente O vetor que define a velocidade e direção da corrente global
     * @return A instância Única e global do Simulador
     */
    public static Simulador getInstance(Vetor corrente){
        if( instanciaUnica == null){
            instanciaUnica = new Simulador(corrente);
        }

        return instanciaUnica;
    }


    /**
     * Procura um Porto pelo seu nome
     * @param nome O nome que vamos comparar
     * @return
     */
    public Porto getPortoByNome(String nome){
        for( Porto p : portos){
            if( p.getNomePorto().equals(nome)){
                return p;
            }
        }

        return null;
    }


    /**
     * Gera obstáculos móveis (Círculos) aleatórios e coloca-os de forma distribuída ao longo das rotas
     * @param quantidade O número de obstáculos móveis a gerar (foi gerado 2 obstáculos móveis)
     */
    public void gerarObstaculosMoveisAleatorios(int quantidade){
        if(this.rotas.isEmpty()) return;

        //limita a quantidade para não criar mais obstaculos do que as rotas que já existem
        int qutseg = Math.min(quantidade, this.rotas.size());

        //cria uma lista com os "index" de todas a rotas e "baralha"
        List<Integer> rotasDisponiveis = new ArrayList<>();
        for(int i = 0; i < this.rotas.size(); i++){
            rotasDisponiveis.add(i);
        }
        Collections.shuffle(rotasDisponiveis); //"baralha" os numeros das rtas

        for(int i = 0; i < qutseg; i++){

            //escolhe uma rota aleatória da lista
            int indexRota = rotasDisponiveis.get(i);
            Route rotaMovel = this.rotas.get(indexRota);


            //escolhemos um distanica aleatóra
            double percentagem = 0.1 + (Math.random() * 0.8); //para evitar que o obst movel não esteja em cima de um porto evitamos os priemiros e ultimos 10% da rota
            double distanciaAleatoria = percentagem * rotaMovel.length();

            //para obter o ponto exato nessa distancia
            Ponto centroAleatorio = rotaMovel.position(distanciaAleatoria, 1.0);

            //criamos o circulo com um raio aleatorio enre 30 e 50
            double raioAleatorio = 30.0 + (Math.random() * 20.0);
            Circulo obstaculoMovelRandom = new Circulo(centroAleatorio, raioAleatorio);

            this.addObstaculoMovel(obstaculoMovelRandom);
        }
    }


    /**
     * Procura a posição de um ponto específico numa lista de pontos
     * Utiliza a margem de tolerância (1e-9) para comparar as coordenadas X e Y
     * @param lista A lista de pontos (nós únicos do grafo) onde a pesquisa será efetuada
     * @param p O ponto cujas coordenadas queremos encontrar na lista
     * @return O índice do ponto na lista, ou -1 caso o ponto ainda não exista
     */
    private int encontrarIndicePonto(List<Ponto> lista, Ponto p){
        for(int i =0; i<lista.size(); i ++){
            if(Math.abs(lista.get(i).getX() - p.getX()) < 1e-9  && Math.abs(lista.get(i).getY() - p.getY() ) < 1e-9){
                return i;
            }
        }

        return -1;
    }


    /**
     * Controi dinamicamente um grafo de navegação e calcula o caminho mais curto entre dois Pontos
     * Este método permite os navios mudarem de rota em cruzamento
     * @param localOrigem Coordenadas de partida (O Porto onde sai)
     * @param localDestino Coordenadas de destino (O Porto de destino)
     * @return Um objeto Route com o caminho mais curto, ou null se não houver ligação
     */
    public Route calcularRotaParaNavio(Ponto localOrigem, Ponto localDestino, boolean evitarMoveis){

        List<Ponto> todosOsNos = new ArrayList<>();

        //Percorremos todos os pontos de todas as rotas do simulador
        //Usamos o método encontrarIndicePonto para garantir que cruzamentos partilhados por
        // várias rotas sejam contados como um único nó no grafo
        for(Route r : this.rotas){

            for(Ponto p : r.getPontos()){
                if(encontrarIndicePonto(todosOsNos, p) == -1){
                    todosOsNos.add(p);
                }

            }
        }

        //contrução da matriz de pesos
        //criamos uma matrix quadrada onde o tamanho é o número total de nós únicos encontrados
        Ponto[] arrayPontos = todosOsNos.toArray(new Ponto[0]);
        int n = arrayPontos.length;
        int[][] matrizPesos = new int[n][n];

        //vemos novamente as rotas para definir as ligações
        //de 2 pontos estão seguidos num array de uma Route, existe um caminho entre eles
        for(Route r : this.rotas){
            Ponto[] pontosDaRota = r.getPontos();

            for(int i= 0; i < pontosDaRota.length -1; i++ ){
                int idx1 = encontrarIndicePonto(todosOsNos, pontosDaRota[i]);
                int idx2 = encontrarIndicePonto(todosOsNos, pontosDaRota[i+1]);

                Route aresta = new Route(new double[]{pontosDaRota[i].getX(), pontosDaRota[i].getY(), pontosDaRota[i+1].getX(), pontosDaRota[i+1].getY()});
                boolean arestaBloqueada = false;

                if(evitarMoveis && !arestaBloqueada){
                    for(Circulo movel : this.obstaculosMoveis){
                        if(!movel.intersect(aresta).isEmpty()){
                            arestaBloqueada = true;
                            break;
                        }
                    }
                }
                if(!arestaBloqueada){
                    //Calculamos a distância real entre os pontos para servir de "peso"
                    double dx = pontosDaRota[i+1].getX() - pontosDaRota[i].getX();
                    double dy = pontosDaRota[i+1].getY() - pontosDaRota[i].getY();
                    int dist = (int)Math.sqrt( (dx * dx) + (dy * dy) );

                    //no "mar" os navios podem navegar nos dois sentidos de uma rota
                    matrizPesos[idx1][idx2] = dist;
                    matrizPesos[idx2][idx1] = dist;

                }

            }

        }

        //identificação dos índices de partida e chegada
        //localizamos a posição numérica da origem e do destino da nossa lista de nós
        int idxOrigem = encontrarIndicePonto(todosOsNos, localOrigem);
        int idxDestino = encontrarIndicePonto(todosOsNos, localDestino);


        //se o porto de destino não estiver ligado a nenhuma rota, o calculo falha
        if(idxOrigem == -1 || idxDestino == -1){
            return null;
        }

        //é aqui que usamos o algoritmo para o caminho mais curto (dijkstra)
        return Route.calcularRotaMaisCurta(arrayPontos, matrizPesos, idxOrigem, idxDestino);
    }



    /**
     * Método para adicionar um navio_porto.Porto á lista
     * @param p o navio_porto.Porto a adicionar
     */
    public void addPorto(Porto p){
        this.portos.add(p);
    }

    /**
     * Método para retornar os Portos da lista
     * @return Os Portos da lista
     */
    public List<Porto> getPortos(){
        return this.portos;
    }

    /**
     * Método para adicionarmos um "obstáculo" á lista
     * @param f o obstáculo a adicionar á lista
     */
    public void addObstaculoFixo(Figura f){
        this.obstaculosFixos.add(f);
    }

    /**
     * Método para retornar os "obstáculos" da lista
     * @return os "obstáculos" da lista
     */
    public List<Figura> getObstaculoFixo(){
        return this.obstaculosFixos;
    }


    /**
     * Adicionar um obstáculo móvel
     * @param c O obstáculo móvel a adicionar no Simulador
     */
    public void addObstaculoMovel(Circulo c){
        this.obstaculosMoveis.add(c);
    }

    /**
     * Retornar os obstáculos móveis
     * @return Os obstáulos móveis
     */
    public List<Circulo> getObstaculosMoveis(){
        return this.obstaculosMoveis;
    }

    /**
     * Método para adicionar uma Rota á lista
     * @param rota a Rota a adiconar á lista
     */
    public void addRota(Route rota){
        this.rotas.add(rota);
    }

    /**
     * Método retornar as Rotas da lista
     * @return As Rotas da lista
     */
    public List<Route> getRotas(){
        return this.rotas;
    }

    /**
     * Metodo para retornar os Navios que sairam do navio_porto.Porto (que "estão no mar")
     * @return Navaios que "estão no mar"
     */
    public List<Navio> getNaviosNoMar(){
        return this.naviosNoMar;
    }


    /**
     * Faz avançar o relógio do Simulador, despacha navios dos Portos e atualiza
     * o movimento de todos os navios no mar através do Padrão State
     * @return O novo valor do tempo atual
     */
    public int avancarTempo(){

        //avança o cronómetro global
        this.tempoAtual++;

        //verifica em cada Porto se há navios com partida agendada para "Agora"
        for(Porto p : this.portos){ //Percorremos todos os portos

            //Pedimos ao Porto os navios que têm hora marcada para AGORA
            List<Navio> naviosQuePartiram = p.removeNavio(this.tempoAtual);

            for(Navio n : naviosQuePartiram){ //Colocamos estes navios no "mar"

                //A posição inicinal é a localização exata do Porto onde esse Navio saiu
                n.setPosicaoAtual(p.getLocalPorto());

                Porto portoDestino = getPortoByNome(n.getDestino());

                if(portoDestino != null){
                    Route rotaDijkstra = calcularRotaParaNavio(p.getLocalPorto(), portoDestino.getLocalPorto(), false);
                    n.setRota(rotaDijkstra);
                }

                //adicionamos aos navios que "estão no mar"
                this.naviosNoMar.add(n);
            }
        }

        //Padrão State: dá ordem de atualização a todos os navio no mar
        //Cada Navio irá comportar-se de acordo com o seu estado (NAVEGANDO ou ESPERANDO)
        for( Navio n : this.naviosNoMar){
            n.atualizar(this);
        }

        return this.tempoAtual;
    }


    /**
     * Verifica se o cenário da simulação chegou ao fim
     * A simulação termina quando todos os Navios chegaram aos Portos de Destino correspondentes
     * @return true se a simuação terminou, false caso contrário
     */
    public boolean isSimulacaoTerminada(){
        for(Porto p : this.portos){
            if(!p.getNaviosEmEspera().isEmpty()){
                return false;
            }
        }
        if(this.naviosNoMar.isEmpty()){
            return false;
        }

        for(Navio n : this.naviosNoMar){
            if(!(n.getEstado() instanceof EstadoEspera)) return false;
        }

        return true;
    }

    /**
     * Retorna o vetor da corrente
     * @return O vetor da corrente atual
     */
    public Vetor getCorrenteAtual(){
        return this.correnteAtual;
    }

    
}
