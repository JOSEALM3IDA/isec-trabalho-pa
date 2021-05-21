package jogo.logica.dados.jogadores;

import jogo.logica.dados.Tabuleiro;
import jogo.logica.dados.TipoFicha;

import java.io.Serializable;

public abstract class Jogador implements Serializable {

    private int numJogadasDesdeMinijogo = 0;
    private final String nome;
    private final TipoFicha ficha;
    private boolean isVencedor = false;
    protected int numFichasEspeciais = 0;

    public Jogador(String nome, TipoFicha ficha) {

        System.out.println("Nome: " + nome + "\n" + ficha);

        this.nome = nome;
        this.ficha = ficha;
    }

    public int getJogadaAutomatica(Tabuleiro tabuleiro) { return -1; }

    public TipoFicha getFicha() { return ficha; }
    public String getNome() { return nome; }


    public boolean isComputador() { return false; };

    public void setVencedor(boolean valor) { isVencedor = valor; }
    public boolean isVencedor() { return isVencedor; }

    public int getNumJogadasDesdeMinijogo() { return numJogadasDesdeMinijogo; }
    public void setNumJogadasDesdeMinijogo(int numJogadasDesdeMinijogo) { this.numJogadasDesdeMinijogo = numJogadasDesdeMinijogo; }

    @Override
    public String toString() { return nome; }

    public void adicionaFichaEspecial() {}
    public int getNumFichasEspeciais() { return numFichasEspeciais; }
}