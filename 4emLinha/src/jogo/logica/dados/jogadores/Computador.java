package jogo.logica.dados.jogadores;

import jogo.logica.dados.Tabuleiro;
import jogo.logica.dados.TipoFicha;

import java.util.Random;

public class Computador extends Jogador {

    public Computador(String nome, TipoFicha ficha) { super(nome, ficha); }

    @Override
    public int getJogadaAutomatica(Tabuleiro tabuleiro) { return (new Random()).nextInt(tabuleiro.getNumColunas()); }

    @Override
    public boolean isComputador() { return true; }

    @Override
    public void setNumFichasEspeciais(int num) { }

    @Override
    public void resetEstado() {
        numJogadasDesdeMinijogo = 0;
        isVencedor = false;
        numFichasEspeciais = 0;
        numCreditos = 0;
    }

    @Override
    public String toString() { return getNome() + " (CPU)"; }

}
