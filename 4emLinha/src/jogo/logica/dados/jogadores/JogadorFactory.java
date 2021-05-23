package jogo.logica.dados.jogadores;

import jogo.logica.dados.tabuleiro.TipoFicha;

public class JogadorFactory {

    public static Jogador getJogador(TipoJogador tipoJogador, String nomeJogador, TipoFicha novaFicha) {
        switch (tipoJogador) {
            case HUMANO -> { return new Humano(nomeJogador, novaFicha); }
            case COMPUTADOR -> { return new Computador(nomeJogador, novaFicha); }
            default -> { return null; }
        }
    }
}
