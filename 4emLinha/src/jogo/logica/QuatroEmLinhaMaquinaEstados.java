package jogo.logica;

import jogo.logica.dados.QuatroEmLinhaGestor;
import jogo.logica.dados.tabuleiro.TipoFicha;
import jogo.logica.dados.jogadores.TipoJogador;
import jogo.logica.estados.Estado;
import jogo.logica.estados.PedeDecisaoInicio;
import jogo.logica.estados.Situacao;
import jogo.utils.Constantes;
import jogo.utils.Utils;

import java.io.Serializable;
import java.util.List;

public class QuatroEmLinhaMaquinaEstados implements Serializable {

    private Estado estadoAtual;
    QuatroEmLinhaGestor quatroEmLinhaGestor;

    public QuatroEmLinhaMaquinaEstados() {
        this.quatroEmLinhaGestor = new QuatroEmLinhaGestor();
        this.estadoAtual = new PedeDecisaoInicio(quatroEmLinhaGestor);
    }

    public void verReplay(String nomeFicheiro) {
        if (!carregaGestorDeFicheiro(nomeFicheiro)) return;
        estadoAtual = estadoAtual.verReplay(quatroEmLinhaGestor);
    }

    public void iniciarJogo() { estadoAtual = estadoAtual.iniciarJogo(); }
    public void adicionarJogador(TipoJogador tipoJogador, String nomeJogador) { estadoAtual = estadoAtual.adicionarJogador(tipoJogador, nomeJogador); }
    public void jogarFicha(int col) { estadoAtual = estadoAtual.jogarFicha(col); }
    public void undoJogada(int numVezes) { estadoAtual = estadoAtual.undoJogada(numVezes); }
    public void desistir() { estadoAtual = estadoAtual.desistir(); }
    public void aceitarMinijogo() { estadoAtual = estadoAtual.aceitarMinijogo(); }
    public void jogarFichaEspecial(int col) { estadoAtual = estadoAtual.jogarFichaEspecial(col); }
    public void enviarRespostaMinijogo(String resposta) { estadoAtual = estadoAtual.jogarMinijogo(resposta); }
    public void avancar() { estadoAtual = estadoAtual.avancar(); }

    public boolean existeJogador(String nome) { return quatroEmLinhaGestor.existeJogador(nome); }
    public int getNumJogadores() { return quatroEmLinhaGestor.getNumJogadores(); }
    public int getNumLinhas() { return quatroEmLinhaGestor.getNumLinhas(); }
    public int getNumColunas() { return quatroEmLinhaGestor.getNumColunas(); }
    public int getJogadaAutomatica() { return quatroEmLinhaGestor.getJogadaAutomatica(); }
    public String getNomeJogadorAtual() { return quatroEmLinhaGestor.getNomeJogadorAtual(); }
    public String getNomeVencedor() { return quatroEmLinhaGestor.getNomeVencedor(); }
    public Situacao getSituacao() { return estadoAtual.getSituacao(); }
    public String getConfigJogadores() { return quatroEmLinhaGestor.getConfigJogadores(); }
    public List<TipoFicha> getTabuleiro() { return quatroEmLinhaGestor.getTabuleiro(); }
    public boolean temMinijogoDisponivel() { return quatroEmLinhaGestor.temMinijogoDisponivel(); }
    public boolean isComputadorAJogar() { return quatroEmLinhaGestor.isComputadorAJogar(); }
    public String getPerguntaMinijogo() { return quatroEmLinhaGestor.getPerguntaMinijogo(); }
    public boolean isValidaRespostaMinijogo(String resposta) { return quatroEmLinhaGestor.isValidaRespostaMinijogo(resposta); }
    public boolean ganhouUltimoMinijogo() { return quatroEmLinhaGestor.ganhouUltimoMinijogo(); }
    public boolean isAcabadoMinijogo() { return quatroEmLinhaGestor.isAcabadoMinijogo(); }
    public int getPontuacaoAtualMinijogo() { return quatroEmLinhaGestor.getPontuacaoAtualMinijogo(); }
    public int getNumFichasEspeciaisJogadorAtual() { return quatroEmLinhaGestor.getNumFichasEspeciaisJogadorAtual(); }
    public int getNumCreditos() { return quatroEmLinhaGestor.getNumCreditosJogadorAtual(); }
    public boolean podeVoltarAtras() { return quatroEmLinhaGestor.podeVoltarAtrasJogadorAtual(); }
    public int getNumCreditosJogaveis() { return quatroEmLinhaGestor.getNumCreditosJogaveisJogadorAtual(); }
    public boolean isJogavelColuna(int coluna) { return quatroEmLinhaGestor.isJogavelColuna(coluna); }
    public boolean temProximo() { return quatroEmLinhaGestor.temProximo(); }
    public boolean isEmpatado() { return quatroEmLinhaGestor.isEmpatado(); }
    public String getDescricaoComandoAtual() { return quatroEmLinhaGestor.getDescricaoComandoAtual(); }
    public TipoFicha getFichaAtual() { return quatroEmLinhaGestor.getFichaAtual(); }

    private boolean carregaGestorDeFicheiro(String nomeFicheiro) {
        QuatroEmLinhaGestor novoGestor;

        novoGestor = Utils.lerObjeto(Constantes.REPLAY_PATH + nomeFicheiro, QuatroEmLinhaGestor.class);

        if (novoGestor == null) return false;

        quatroEmLinhaGestor = novoGestor;

        quatroEmLinhaGestor.resetTabuleiro();
        quatroEmLinhaGestor.resetEstadoJogadores();
        quatroEmLinhaGestor.resetMinijogos();

        return true;
    }
}
