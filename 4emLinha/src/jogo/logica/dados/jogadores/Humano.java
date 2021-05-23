package jogo.logica.dados.jogadores;

import jogo.logica.dados.TipoFicha;

public class Humano extends Jogador {

    private static final int NUM_CREDITOS_INICIAIS = 5;
    
    public Humano(String nome, TipoFicha ficha) {
        super(nome, ficha);
        numCreditos = NUM_CREDITOS_INICIAIS;
    }

    @Override
    public void setNumFichasEspeciais(int num) {
        if (num < 0) {
            numFichasEspeciais = 0;
            return;
        }

        numFichasEspeciais = num;
    }

    @Override
    public void resetEstado() {
        numJogadasDesdeMinijogo = 0;
        isVencedor = false;
        numFichasEspeciais = 0;
        numCreditos = NUM_CREDITOS_INICIAIS;
    }

}
