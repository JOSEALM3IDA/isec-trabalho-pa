package jogo.logica.dados.jogadores;

import jogo.logica.dados.TipoFicha;

public class Humano extends Jogador {
    
    public Humano(String nome, TipoFicha ficha) { super(nome, ficha); }

    @Override
    public void setNumFichasEspeciais(int num) {
        if (num < 0) {
            numFichasEspeciais = 0;
            return;
        }

        numFichasEspeciais = num;
    }

}
