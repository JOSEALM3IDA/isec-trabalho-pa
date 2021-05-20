package jogo.logica.dados;

import jogo.logica.dados.jogadores.*;

import java.io.Serializable;
import java.util.List;

public class QuatroEmLinha implements Serializable {

    JogadorLista jogadorLista = new JogadorLista();
    Tabuleiro tabuleiro = new Tabuleiro();

    public QuatroEmLinha() {}

    public void addJogador(TipoJogador tipoJogador, String nomeJogador) { jogadorLista.addJogador(tipoJogador, nomeJogador); }

    public void jogarFicha(int col) {
        tabuleiro.addFicha(col, jogadorLista.getFichaAtual());
        proxJogador();
    }

    public int getJogadaAutomatica() { return jogadorLista.getJogadaAutomatica(tabuleiro); }

    public boolean existeJogador(String nomeJogador) { return jogadorLista.existeJogador(nomeJogador); }

    private void proxJogador() { jogadorLista.proxJogador(); }

    public boolean isFullJogadores() { return jogadorLista.isFullJogadores(); }

    public int getNumJogadores() { return jogadorLista.getNumJogadores(); }
    public int getNumLinhas() { return tabuleiro.getNumLinhas(); }
    public int getNumColunas() { return tabuleiro.getNumColunas(); }
    public String getNomeJogadorAtual() { return jogadorLista.getNomeJogadorAtual(); }
    public String getNomeVencedor() { return jogadorLista.getNomeVencedor(); }

    public String getConfigJogadores() { return "Lista de jogadores:\n" + jogadorLista.toString(); }

    public List<TipoFicha> getTabuleiro() { return tabuleiro.getFichas(); }
    public boolean temMinijogoDisponivel() { return false; }

    public boolean isComputadorAJogar() { return jogadorLista.isComputadorAJogar(); }

    public boolean checkFimJogo() {
        TipoFicha fichaAnterior = jogadorLista.getFichaAnterior();

        if (!tabuleiro.checkQuatroEmLinha(fichaAnterior)) return false;

        jogadorLista.setVencedorJogadorComFicha(fichaAnterior);
        return true;
    }

    public void limparTudo() {
        jogadorLista.limpar();
        tabuleiro.limpar();
    }
}
