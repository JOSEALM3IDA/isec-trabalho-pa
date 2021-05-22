package jogo.logica.dados;

import jogo.logica.dados.jogadores.*;
import jogo.logica.dados.minijogos.Minijogo;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class QuatroEmLinha implements Serializable {

    private final JogadorLista jogadorLista = new JogadorLista();
    private final Tabuleiro tabuleiro = new Tabuleiro();
    private transient Minijogo minijogo = null;

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
    public boolean temMinijogoDisponivel() { return jogadorLista.getNumJogadasDesdeMinijogoCurrJogador() >= 4; }

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

    public void comecarMinijogo() {
        if (!temMinijogoDisponivel()) return;

        minijogo = MinijogoFactory.getMinijogo();
        minijogo.comecar();
        jogadorLista.aceitarMinijogo();
    }

    public boolean isComecadoMinijogo() { return minijogo != null && minijogo.isComecado(); }

    public String getPerguntaMinijogo() { return minijogo != null ? minijogo.getPerguntaAtual() : ""; }

    public boolean isValidaRespostaMinijogo(String resposta) { return minijogo != null && minijogo.isValidaResposta(resposta); }

    public void enviarRespostaMinijogo(String resposta) { if (minijogo != null) minijogo.receberResposta(resposta); }

    public boolean ganhouUltimoMinijogo() { return minijogo != null && minijogo.isGanho(); }

    public boolean isAcabadoMinijogo() { return minijogo != null && minijogo.isAcabado(); }

    public void adicionaFichaEspecialJogadorAtual() { jogadorLista.adicionaFichaEspecialJogadorAtual(); }

    public int getPontuacaoAtualMinijogo() { return minijogo.getPontuacaoAtual(); }

    public void jogarFichaEspecial(int col) {
        List<TipoFicha> colunaVazia = new LinkedList<>();
        for (int lin = 0; lin < tabuleiro.getNumLinhas(); lin++) colunaVazia.add(TipoFicha.NONE);

        jogadorLista.usarFichaEspecialJogadorAtual();
        tabuleiro.setColuna(col, colunaVazia);
        proxJogador();
    }

    public int getNumFichasEspeciaisJogadorAtual() { return jogadorLista.getNumFichasEspeciaisJogadorAtual(); }
}
