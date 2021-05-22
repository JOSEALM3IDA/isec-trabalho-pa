package jogo.logica.dados.jogadores;

import jogo.logica.dados.Tabuleiro;
import jogo.logica.dados.TipoFicha;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JogadorLista implements Serializable {
    private static final int MAX_PLAYERS = 2;

    private final ArrayList<Jogador> jogadores = new ArrayList<>(2);
    private int currJogadorIdx = 0;

    public boolean isFullJogadores() { return jogadores.size() == MAX_PLAYERS; }

    public void addJogador(TipoJogador tipoJogador, String nomeJogador) {
        TipoFicha novaFicha = getRandomFichaDisponivel();
        if (novaFicha == null || novaFicha == TipoFicha.NONE) return;

        if (!isFullJogadores() && !existeJogador(nomeJogador)) {
            switch (tipoJogador) {
                case HUMANO -> jogadores.add(new Humano(nomeJogador, novaFicha));
                case COMPUTADOR -> jogadores.add(new Computador(nomeJogador, novaFicha));
            }

            currJogadorIdx = 0;
        }
    }

    private TipoFicha getRandomFichaDisponivel() {
        List<TipoFicha> tipos = new ArrayList<>(Arrays.asList(TipoFicha.values()));

        for (var jogador : jogadores) {
            TipoFicha fichaJogador = jogador.getFicha();

            if (fichaJogador == null) continue;

            tipos.remove(fichaJogador);
        }

        if (tipos.size() <= 0) return null;
        return tipos.get(0);
    }

    public boolean existeJogador(String nomeJogador) {
        for (Jogador jogador : jogadores) if (nomeJogador.equals(jogador.getNome())) return true;

        return false;
    }

    public void proxJogador() {
        Jogador currJogador = jogadores.get(currJogadorIdx);
        currJogador.setNumJogadasDesdeMinijogo(currJogador.getNumJogadasDesdeMinijogo() + 1);

        currJogadorIdx++;

        if (currJogadorIdx == jogadores.size()) currJogadorIdx = 0;
    }

    public void setVencedorJogadorComFicha(TipoFicha ficha) {
        for (var jogador : jogadores) {
            if (jogador.getFicha() == ficha) {
                jogador.setVencedor(true);
                continue;
            }

            jogador.setVencedor(false);
        }
    }

    public void aceitarMinijogo() { jogadores.get(currJogadorIdx).setNumJogadasDesdeMinijogo(0); }

    public boolean isComputadorAJogar() { return jogadores.get(currJogadorIdx).isComputador(); }
    public int getNumJogadores() { return jogadores.size(); }
    public String getNomeJogadorAtual() { return jogadores.get(currJogadorIdx).getNome(); }
    public int getJogadaAutomatica(Tabuleiro tabuleiro) { return jogadores.get(currJogadorIdx).getJogadaAutomatica(tabuleiro); }
    public TipoFicha getFichaAtual() { return jogadores.get(currJogadorIdx).getFicha(); }
    public TipoFicha getFichaAnterior() { return currJogadorIdx == 0 ? jogadores.get(jogadores.size() - 1).getFicha() : jogadores.get(currJogadorIdx - 1).getFicha(); }
    public int getNumJogadasDesdeMinijogoCurrJogador() { return jogadores.get(currJogadorIdx).getNumJogadasDesdeMinijogo(); }


    public String getNomeVencedor() {
        for (var jogador : jogadores) if (jogador.isVencedor()) return jogador.getNome();

        return null;
    }

    public void limpar() { jogadores.clear(); }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < jogadores.size(); i++) {
            sb.append(i + 1).append(": ").append(jogadores.get(i).toString());
            if (i != jogadores.size() - 1)
                sb.append('\n');
        }

        return sb.toString();
    }

    public void adicionaFichaEspecialJogadorAtual() { jogadores.get(currJogadorIdx).setNumFichasEspeciais(getNumFichasEspeciaisJogadorAtual() + 1); }

    public int getNumFichasEspeciaisJogadorAtual() { return jogadores.get(currJogadorIdx).getNumFichasEspeciais(); }

    public void usarFichaEspecialJogadorAtual() { jogadores.get(currJogadorIdx).setNumFichasEspeciais(getNumFichasEspeciaisJogadorAtual() - 1); }
}
