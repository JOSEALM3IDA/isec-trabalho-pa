package jogo.logica;

import jogo.logica.dados.QuatroEmLinha;
import jogo.logica.estados.Estado;
import jogo.logica.estados.PedeDecisaoInicio;
import jogo.logica.estados.Situacao;

public class QuatroEmLinhaMaquinaEstados {
    Estado estadoAtual;
    QuatroEmLinha quatroEmLinha;

    public QuatroEmLinhaMaquinaEstados() {
        this.quatroEmLinha = new QuatroEmLinha();
        this.estadoAtual = new PedeDecisaoInicio(quatroEmLinha);
    }

    public void iniciarJogo() { estadoAtual = estadoAtual.iniciarJogo(); }
    public void continuarJogo() { estadoAtual = estadoAtual.continuarJogo(); }
    public void verReplay() { estadoAtual = estadoAtual.verReplay(); }
    public void adicionarJogador() { estadoAtual = estadoAtual.adicionarJogador(); }
    public void adicionarJogador(String nome) { estadoAtual = estadoAtual.adicionarJogador(nome); }
    public void jogar() { estadoAtual = estadoAtual.jogar(); }
    public void undoJogada() { estadoAtual = estadoAtual.undoJogada(); }
    public void gravarJogo() { estadoAtual = estadoAtual.gravarJogo(); }
    public void desistir() { estadoAtual = estadoAtual.desistir(); }
    public void aceitarMinijogo() { estadoAtual = estadoAtual.aceitarMinijogo(); }
    public void ganharMinijogo() { estadoAtual = estadoAtual.ganharMinijogo(); }
    public void perderMinijogo() { estadoAtual = estadoAtual.perderMinijogo(); }
    public void ganharJogo() { estadoAtual = estadoAtual.ganharJogo(); }
    public void avancar() { estadoAtual = estadoAtual.avancar(); }

    public boolean existeJogador(String nome) { return quatroEmLinha.existeJogador(nome); }

    public boolean isFullJogadores() { return quatroEmLinha.isFullJogadores(); }

    public int getNumJogadores() { return quatroEmLinha.getNumJogadores(); }

    public Situacao getSituacao() { return estadoAtual.getSituacao(); }

    public String getConfigJogadores() { return quatroEmLinha.getConfigJogadores(); }
}
