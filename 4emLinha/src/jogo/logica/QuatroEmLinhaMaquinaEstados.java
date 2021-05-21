package jogo.logica;

import jogo.logica.dados.QuatroEmLinha;
import jogo.logica.dados.TipoFicha;
import jogo.logica.dados.jogadores.TipoJogador;
import jogo.logica.estados.Estado;
import jogo.logica.estados.PedeDecisaoInicio;
import jogo.logica.estados.Situacao;

import java.io.Serializable;
import java.util.List;

public class QuatroEmLinhaMaquinaEstados implements Serializable {
    private Estado estadoAtual;
    protected QuatroEmLinha quatroEmLinha;

    public QuatroEmLinhaMaquinaEstados() {
        this.quatroEmLinha = new QuatroEmLinha();
        this.estadoAtual = new PedeDecisaoInicio(quatroEmLinha);
    }

    public void iniciarJogo() { estadoAtual = estadoAtual.iniciarJogo(); }
    public void verReplay() { estadoAtual = estadoAtual.verReplay(); }
    public void adicionarJogador(TipoJogador tipoJogador, String nomeJogador) { estadoAtual = estadoAtual.adicionarJogador(tipoJogador, nomeJogador); }
    public void jogarFicha(int col) { estadoAtual = estadoAtual.jogarFicha(col); }
    public void undoJogada() { estadoAtual = estadoAtual.undoJogada(); }
    public void desistir() { estadoAtual = estadoAtual.desistir(); }
    public void aceitarMinijogo() { estadoAtual = estadoAtual.aceitarMinijogo(); }
    public void enviarRespostaMinijogo(String resposta) { estadoAtual = estadoAtual.jogarMinijogo(resposta); }
    public void ganharMinijogo() { estadoAtual = estadoAtual.ganharMinijogo(); }
    public void perderMinijogo() { estadoAtual = estadoAtual.perderMinijogo(); }
    public void ganharJogo() { estadoAtual = estadoAtual.ganharJogo(); }
    public void avancar() { estadoAtual = estadoAtual.avancar(); }

    public boolean existeJogador(String nome) { return quatroEmLinha.existeJogador(nome); }

    public boolean isFullJogadores() { return quatroEmLinha.isFullJogadores(); }

    public int getNumJogadores() { return quatroEmLinha.getNumJogadores(); }
    public int getNumLinhas() { return quatroEmLinha.getNumLinhas(); }
    public int getNumColunas() { return quatroEmLinha.getNumColunas(); }
    public int getJogadaAutomatica() { return quatroEmLinha.getJogadaAutomatica(); }
    public String getNomeJogadorAtual() { return quatroEmLinha.getNomeJogadorAtual(); }
    public String getNomeVencedor() { return quatroEmLinha.getNomeVencedor(); }

    public Situacao getSituacao() { return estadoAtual.getSituacao(); }

    public String getConfigJogadores() { return quatroEmLinha.getConfigJogadores(); }

    public List<TipoFicha> getTabuleiro() { return quatroEmLinha.getTabuleiro(); }

    public boolean temMinijogoDisponivel() { return quatroEmLinha.temMinijogoDisponivel(); }

    public boolean isComputadorAJogar() { return quatroEmLinha.isComputadorAJogar(); }

    public boolean isComecadoMinijogo() { return quatroEmLinha.isComecadoMinijogo(); }

    public String getPerguntaMinijogo() { return quatroEmLinha.getPerguntaMinijogo(); }

    public boolean isValidaRespostaMinijogo(String resposta) { return quatroEmLinha.isValidaRespostaMinijogo(resposta); }

    public boolean ganhouUltimoMinijogo() { return quatroEmLinha.ganhouUltimoMinijogo(); }

    public boolean isAcabadoMinijogo() { return quatroEmLinha.isAcabadoMinijogo(); }

    public int getPontuacaoAtualMinijogo() { return quatroEmLinha.getPontuacaoAtualMinijogo(); }
}
