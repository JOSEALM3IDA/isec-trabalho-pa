package jogo.logica.dados;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuatroEmLinha implements Serializable {
    private static final int MAX_PLAYERS = 2;
    private static final int NUM_LINHAS = 6;
    private static final int NUM_COLUNAS = 7;

    ArrayList<Jogador> jogadores = new ArrayList<>(2);
    ArrayList<TipoFicha> tabuleiro = new ArrayList<>(NUM_LINHAS * NUM_COLUNAS);

    public QuatroEmLinha() { for (int i = 0; i < NUM_LINHAS * NUM_COLUNAS; i++) tabuleiro.add(TipoFicha.FICHA_VAZIA); }

    public void adicionarJogador() {
        if (!isFullJogadores())
            jogadores.add(new Computador());
    }

    public void adicionarJogador(String nome) {
        if (!isFullJogadores() && !existeJogador(nome))
            jogadores.add(new Humano(nome));
    }

    public boolean existeJogador(String nome) {
        for (Jogador jogador : jogadores) if (nome.equals(jogador.getNome())) return true;

        return false;
    }

    public boolean isFullJogadores() { return jogadores.size() == MAX_PLAYERS; }

    public int getNumJogadores() { return jogadores.size(); }
    public int getNumLinhas() { return NUM_LINHAS; }
    public int getNumColunas() { return NUM_COLUNAS; }

    public String getConfigJogadores() {
        StringBuilder sb = new StringBuilder();

        sb.append("Lista de jogadores:\n");
        for (int i = 0; i < jogadores.size(); i++) {
            sb.append(i + 1).append(": ").append(jogadores.get(i).toString());
            if (i != jogadores.size() - 1)
                sb.append('\n');
        }

        return sb.toString();
    }

    public List<TipoFicha> getTabuleiro() {
        return Collections.unmodifiableList(tabuleiro);
    }

    public boolean temMinijogoDisponivel() { return false; }
}
