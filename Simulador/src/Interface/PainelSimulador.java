package Interface;

import navPort.Porto;
import navPort.Navio;

import Geometry.*;
import Geometry.figuras.Circulo;
import Geometry.figuras.Figura;
import Geometry.figuras.Poligono;
import navPort.Simulador;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

//  Esta classe estende a class já pré definida do Swing, a JPanel, neste caso é um JPanel que adicionamos a capacidade de desenho
//Ou seja a uníca função desta classe é desenhar no ecrã
public class PainelSimulador extends JPanel{

    private Simulador simulador;

    public PainelSimulador(Simulador simu){

        this.simulador = simu;
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);

        Graphics2D g2D = (Graphics2D) g;

        if(simulador == null) return; //se o simulador ainda não estiver pronto, não desenha nada

        //DESENHAR AS ROTAS

        Color[] paletaDeCores ={Color.RED, Color.GREEN, Color.BLUE};
        int indiceCor = 0;


        for(Route rota : simulador.getRotas()){

            //escolhe a cor atual e avança para a próxima
            g2D.setColor(paletaDeCores[indiceCor % paletaDeCores.length]);
            indiceCor++;

            //vau os pontos desta rota
            Ponto[] pontos = rota.getPontos();

            for(int i =0; i < pontos.length -1; i++){
                int x1 = (int)pontos[i].getX();
                int y1 = (int)pontos[i].getY();
                int x2 = (int)pontos[i+1].getX();
                int y2 = (int)pontos[i+1].getY();

                //Desenha a linha reta entre os dois pontos
                g2D.drawLine(x1,y1,x2,y2);
            }
        }


        //DESENHAR OS OBSTÁCULOS

        g2D.setColor(Color.YELLOW); // estamos a usar a cor amarela para os obstáculos

        for(Figura fig : simulador.getObstaculo()){

            if (fig instanceof Circulo){ //"se a figura for um Círculo"
                Circulo c = (Circulo) fig; //agora fig é do tipo Geometry.Figuras.Circulo e não Geometry.Figuras.Figura por causa do cast
                int raio = (int) c.getRaio(); // vamos bsucar o raio do Ciruclo

                int x = (int) c.getCentro().getX() - raio;
                int y = (int) c.getCentro().getY() - raio ;

                g2D.fillOval(x , y, raio*2 , raio*2);

            }

            else if(fig instanceof Poligono){ // "se a figura for um Geometry.Figuras.Poligono
                Poligono pol = (Poligono)fig; //agora fig é do tipo Geometry.Figuras.Poligono e não Geometry.Figuras.Figura por causa do cast

                Ponto[] vertices = pol.getVertices(); //todos os vertices

                int[] xPontos = new int[vertices.length]; //onde vamos guardar os valores X dos Pontos
                int[] yPontos = new int[vertices.length]; //onde vamos guardar os valores Y dos Pontos

                for(int i = 0; i < vertices.length; i++){
                    xPontos[i] = (int) vertices[i].getX();
                    yPontos[i] = (int) vertices[i].getY();
                }

                g2D.fillPolygon(xPontos, yPontos, vertices.length);
            }
        }


        g2D.setColor(Color.BLACK); //pusemos a cor como preto

        for (Porto p : simulador.getPortos()){ //percorre cada navio_porto.Porto
            int x = (int)p.getLocalPorto().getX(); //devolve o ponto X da coordenada do navio_porto.Porto
            int y = (int)p.getLocalPorto().getY(); //devolve o ponto Y da coordenada do navio_porto.Porto

            g2D.fillRect(x,y,20,20); //Agora desenhamos o Geometry.Figuras.Retangulo com as coordenads que obtivemos


            g2D.drawString("navio_porto.Porto " + p.getNomePorto(), x - 5, y -5); //Escrevemos o nome desse navio_porto.Porto em cima do retangulo que desenhamos


            //DESENHAR A LISTA DE NAVIOS EM ESPERA
            g2D.setColor(Color.BLUE); //vamos por os navios com cor azul
            int darEspacoY = 20; //É um espaçamento de 20 pixeis

            for(Navio n : p.getNaviosEmEspera()){ //vamos percorrer os Navios que ainda não sairam do navio_porto.Porto

                //É o formato de texto que navio_porto.Navio obrigatóriamente tem de ter
                String textoNavio = "T=" + n.getTempoSaida() + ", " + n.getDestino() + ", " + (int)n.getVelocidadeLinearPretendida();

                g2D.drawString(textoNavio, x-5, y-5-darEspacoY); //"Desenhamos" o texto
                darEspacoY += 15; //sobe mais 15 pixeis para o próximo navion não ficar sobreposto
            }
            g2D.setColor(Color.BLACK); //Voltamos a repor a cor preta para o próximo navio_porto.Porto

        }


        //DESENHAR OS NAVIOS NO MAR
        
        g2D.setColor(Color.RED); //Vamos usar a cor Vermelho para os navios

        for(Navio n : simulador.getNaviosNoMar()){
            if(n.getPosicaoAtual() != null){
                int x = (int) n.getPosicaoAtual().getX();
                int y = (int) n.getPosicaoAtual().getY();

                //desenhar o navio
                int raioNavio = 8;
                g2D.fillOval(x -raioNavio, y - raioNavio, raioNavio+2, raioNavio+15 );

                g2D.drawString(n.getCodigo(), x+10, y+5);




            }
        }

    }

}

