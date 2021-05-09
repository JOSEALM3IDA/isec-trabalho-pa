package jogo.logica.dados;

import java.util.ArrayList;

public class QuatroEmLinha {
    private static final int MAX_PLAYERS = 2;

    ArrayList<Jogador> jogadores = new ArrayList<>(2);

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
}
