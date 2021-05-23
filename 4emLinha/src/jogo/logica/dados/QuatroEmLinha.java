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

    public void jogarFicha(int col) { tabuleiro.addFicha(col, jogadorLista.getFichaAtual()); }

    public boolean removeFicha(int col) { return tabuleiro.removeFicha(col); }

    public int getJogadaAutomatica() { return jogadorLista.getJogadaAutomatica(tabuleiro); }

    public boolean existeJogador(String nomeJogador) { return jogadorLista.existeJogador(nomeJogador); }

    public void proxJogador() { jogadorLista.proxJogador(); }
    private void anteriorJogador(int numVezes) { jogadorLista.undoJogador(numVezes); }

    public boolean isFullJogadores() { return jogadorLista.isFullJogadores(); }

    public int getNumJogadores() { return jogadorLista.getNumJogadores(); }
    public int getNumLinhas() { return tabuleiro.getNumLinhas(); }
    public int getNumColunas() { return tabuleiro.getNumColunas(); }
    public String getNomeJogadorAtual() { return jogadorLista.getNomeJogadorAtual(); }
    public String getNomeVencedor() { return jogadorLista.getNomeVencedor(); }

    public String getConfigJogadores() { return "Lista de jogadores:\n" + jogadorLista; }

    public List<TipoFicha> getTabuleiro() { return tabuleiro.getFichas(); }
    public boolean temMinijogoDisponivel() { return jogadorLista.getNumJogadasDesdeMinijogoCurrJogador() >= 4; }

    public boolean isComputadorAJogar() { return jogadorLista.isComputadorAJogar(); }

    public boolean checkFimJogo() {
        TipoFicha fichaAnterior = jogadorLista.getFichaAnterior();

        if (!tabuleiro.checkQuatroEmLinha(fichaAnterior)) return false;

        jogadorLista.setVencedorJogadorComFicha(fichaAnterior);
        return true;
    }

    public void resetJogo() {
        jogadorLista.limpar();
        tabuleiro.limpar();
    }

    public void desistirJogadorAtual() {
        TipoFicha fichaAnterior = jogadorLista.getFichaAnterior();
        jogadorLista.setVencedorJogadorComFicha(fichaAnterior);
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
    }

    public int getNumFichasEspeciaisJogadorAtual() { return jogadorLista.getNumFichasEspeciaisJogadorAtual(); }

    public void processaUndoJogadorAtual(int numVezes) {
        jogadorLista.removeCreditoJogadorAtual(numVezes);
        anteriorJogador(numVezes);
    }

    public boolean jogoAcabou() { return jogadorLista.haVencedor(); }

    public int getNumCreditosJogadorAtual() { return jogadorLista.getNumCreditosJogadorAtual(); }

    public boolean temCreditosJogadorAtual() { return jogadorLista.getNumCreditosJogadorAtual() > 0; }

    public List<TipoFicha> getColuna(int col) { return tabuleiro.getColuna(col); }

    public boolean setColuna(int col, List<TipoFicha> novaColuna) { return tabuleiro.setColuna(col, novaColuna); }

    public void resetTabuleiro() { tabuleiro.limpar(); }

    public void resetEstadoJogadores() { jogadorLista.resetEstadoJogadores(); }
}
