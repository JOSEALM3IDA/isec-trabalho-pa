package jogo.logica;

import jogo.logica.command.CommandManager;
import jogo.logica.dados.QuatroEmLinha;
import jogo.logica.dados.TipoFicha;
import jogo.logica.dados.jogadores.TipoJogador;

import java.io.Serializable;
import java.util.List;

public class QuatroEmLinhaGestor implements Serializable {

    protected QuatroEmLinha quatroEmLinha;
    CommandManager commandManager;

    public QuatroEmLinhaGestor() {
        this.quatroEmLinha = new QuatroEmLinha();
        this.commandManager = new CommandManager();
    }

    public void addJogador(TipoJogador tipoJogador, String nomeJogador) { quatroEmLinha.addJogador(tipoJogador, nomeJogador); }
    public void jogarFicha(int col) { quatroEmLinha.jogarFicha(col); }
    public int getJogadaAutomatica() { return quatroEmLinha.getJogadaAutomatica(); }
    public boolean existeJogador(String nomeJogador) { return quatroEmLinha.existeJogador(nomeJogador); }
    public boolean isFullJogadores() { return quatroEmLinha.isFullJogadores(); }
    public int getNumJogadores() { return quatroEmLinha.getNumJogadores(); }
    public int getNumLinhas() { return quatroEmLinha.getNumLinhas(); }
    public int getNumColunas() { return quatroEmLinha.getNumColunas(); }
    public String getNomeJogadorAtual() { return quatroEmLinha.getNomeJogadorAtual(); }
    public String getNomeVencedor() { return quatroEmLinha.getNomeVencedor(); }
    public String getConfigJogadores() { return quatroEmLinha.getConfigJogadores(); }
    public List<TipoFicha> getTabuleiro() { return quatroEmLinha.getTabuleiro(); }
    public boolean temMinijogoDisponivel() { return quatroEmLinha.temMinijogoDisponivel(); }
    public boolean isComputadorAJogar() { return quatroEmLinha.isComputadorAJogar(); }
    public boolean checkFimJogo() { return quatroEmLinha.checkFimJogo(); }
    public void limparTudo() { quatroEmLinha.limparTudo(); }
    public void comecarMinijogo() { quatroEmLinha.comecarMinijogo(); }
    public String getPerguntaMinijogo() { return quatroEmLinha.getPerguntaMinijogo(); }
    public boolean isValidaRespostaMinijogo(String resposta) { return quatroEmLinha.isValidaRespostaMinijogo(resposta); }
    public void enviarRespostaMinijogo(String resposta) { quatroEmLinha.enviarRespostaMinijogo(resposta); }
    public boolean ganhouUltimoMinijogo() { return quatroEmLinha.ganhouUltimoMinijogo(); }
    public boolean isAcabadoMinijogo() { return quatroEmLinha.isAcabadoMinijogo(); }
    public void adicionaFichaEspecialJogadorAtual() { quatroEmLinha.adicionaFichaEspecialJogadorAtual(); }
    public int getPontuacaoAtualMinijogo() { return quatroEmLinha.getPontuacaoAtualMinijogo(); }
    public void jogarFichaEspecial(int col) { quatroEmLinha.jogarFichaEspecial(col); }
    public int getNumFichasEspeciaisJogadorAtual() { return quatroEmLinha.getNumFichasEspeciaisJogadorAtual(); }
}
