package jogo.logica;

import jogo.logica.dados.jogadores.TipoJogador;
import jogo.logica.dados.tabuleiro.TipoFicha;
import jogo.logica.estados.Situacao;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class QuatroEmLinhaObservable {
    private final QuatroEmLinhaMaquinaEstados maquinaEstados;
    private final PropertyChangeSupport propertyChangeSupport;

    public QuatroEmLinhaObservable(QuatroEmLinhaMaquinaEstados maquinaEstados) {
        this.maquinaEstados = maquinaEstados;
        this.propertyChangeSupport = new PropertyChangeSupport(maquinaEstados);
    }

    public void addPropertyChangeListener(Propriedades property, PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(property.name(), listener);
    }

    public boolean continuarJogo(String pathFicheiro) {
        boolean rtn = maquinaEstados.continuarJogo(pathFicheiro);
        propertyChangeSupport.firePropertyChange(String.valueOf(Propriedades.ATUALIZAR_LISTA_JOGADORES), null, null);
        propertyChangeSupport.firePropertyChange(String.valueOf(Propriedades.ATUALIZAR_JOGADOR_ATUAL), null, null);
        propertyChangeSupport.firePropertyChange(String.valueOf(Propriedades.ATUALIZAR_ESTADO), null, null);
        return rtn;
    }

    public boolean gravarJogo(String pathFicheiro) { return maquinaEstados.gravarJogo(pathFicheiro); }

    public void verReplay(String pathFicheiro) {
        maquinaEstados.verReplay(pathFicheiro);
        propertyChangeSupport.firePropertyChange(String.valueOf(Propriedades.ATUALIZAR_LISTA_JOGADORES), null, null);
        propertyChangeSupport.firePropertyChange(String.valueOf(Propriedades.ATUALIZAR_JOGADOR_ATUAL), null, null);
        propertyChangeSupport.firePropertyChange(String.valueOf(Propriedades.ATUALIZAR_TABULEIRO), null, null);
        propertyChangeSupport.firePropertyChange(String.valueOf(Propriedades.ATUALIZAR_ESTADO), null, null);
    }

    public void iniciarJogo() {
        maquinaEstados.iniciarJogo();
        propertyChangeSupport.firePropertyChange(String.valueOf(Propriedades.ATUALIZAR_LISTA_JOGADORES), null, null);
        propertyChangeSupport.firePropertyChange(String.valueOf(Propriedades.ATUALIZAR_ESTADO), null, null);
    }

    public void adicionarJogador(TipoJogador tipoJogador, String nomeJogador) {
        maquinaEstados.adicionarJogador(tipoJogador, nomeJogador);
        if (maquinaEstados.jogoComecou()) {
            propertyChangeSupport.firePropertyChange(String.valueOf(Propriedades.ATUALIZAR_JOGADOR_ATUAL), null, null);
            propertyChangeSupport.firePropertyChange(String.valueOf(Propriedades.ATUALIZAR_TABULEIRO), null, null);
        }

        propertyChangeSupport.firePropertyChange(String.valueOf(Propriedades.ATUALIZAR_LISTA_JOGADORES), null, null);
        propertyChangeSupport.firePropertyChange(String.valueOf(Propriedades.ATUALIZAR_ESTADO), null, null);
    }

    public void jogarFicha(int col) {
        maquinaEstados.jogarFicha(col);

        if (maquinaEstados.jogoAcabou()) propertyChangeSupport.firePropertyChange(String.valueOf(Propriedades.ATUALIZAR_VENCEDOR), null, null);

        propertyChangeSupport.firePropertyChange(String.valueOf(Propriedades.ATUALIZAR_TABULEIRO), null, null);
        propertyChangeSupport.firePropertyChange(String.valueOf(Propriedades.ATUALIZAR_JOGADOR_ATUAL), null, null);
        propertyChangeSupport.firePropertyChange(String.valueOf(Propriedades.ATUALIZAR_ESTADO), null, null);
    }

    public void undoJogada(int numVezes) {
        maquinaEstados.undoJogada(numVezes);
        propertyChangeSupport.firePropertyChange(String.valueOf(Propriedades.ATUALIZAR_JOGADOR_ATUAL), null, null);
        propertyChangeSupport.firePropertyChange(String.valueOf(Propriedades.ATUALIZAR_TABULEIRO), null, null);
    }

    public void desistir() {
        maquinaEstados.desistir();
        propertyChangeSupport.firePropertyChange(String.valueOf(Propriedades.ATUALIZAR_VENCEDOR), null, null);
        propertyChangeSupport.firePropertyChange(String.valueOf(Propriedades.ATUALIZAR_ESTADO), null, null);
    }

    public void aceitarMinijogo() {
        maquinaEstados.aceitarMinijogo();
        propertyChangeSupport.firePropertyChange(String.valueOf(Propriedades.ATUALIZAR_PERGUNTA_MINIJOGO), null, null);
        propertyChangeSupport.firePropertyChange(String.valueOf(Propriedades.ATUALIZAR_ESTADO), null, null);
    }

    public void jogarFichaEspecial(int col) {
        maquinaEstados.jogarFichaEspecial(col);
        propertyChangeSupport.firePropertyChange(String.valueOf(Propriedades.ATUALIZAR_TABULEIRO), null, null);
        propertyChangeSupport.firePropertyChange(String.valueOf(Propriedades.ATUALIZAR_JOGADOR_ATUAL), null, null);
        propertyChangeSupport.firePropertyChange(String.valueOf(Propriedades.ATUALIZAR_ESTADO), null, null);
    }

    public void enviarRespostaMinijogo(String resposta) {
        maquinaEstados.enviarRespostaMinijogo(resposta);

        propertyChangeSupport.firePropertyChange(String.valueOf(Propriedades.ATUALIZAR_ESTADO), null, null);

        if (isAcabadoMinijogo()) {
            propertyChangeSupport.firePropertyChange(String.valueOf(Propriedades.ATUALIZAR_JOGADOR_ATUAL), null, null);
            return;
        }

        propertyChangeSupport.firePropertyChange(String.valueOf(Propriedades.ATUALIZAR_PERGUNTA_MINIJOGO), null, null);
    }

    public void avancar() {
        boolean jogoAcabou = jogoAcabou();

        maquinaEstados.avancar();

        if (!jogoAcabou) {
            propertyChangeSupport.firePropertyChange(String.valueOf(Propriedades.ATUALIZAR_JOGADOR_ATUAL), null, null);
            propertyChangeSupport.firePropertyChange(String.valueOf(Propriedades.ATUALIZAR_TABULEIRO), null, null);
        }

        propertyChangeSupport.firePropertyChange(String.valueOf(Propriedades.ATUALIZAR_ESTADO), null, null);
    }

    public boolean existeJogador(String nome) { return maquinaEstados.existeJogador(nome); }
    public int getNumJogadores() { return maquinaEstados.getNumJogadores(); }
    public int getNumLinhas() { return maquinaEstados.getNumLinhas(); }
    public int getNumColunas() { return maquinaEstados.getNumColunas(); }
    public int getJogadaAutomatica() { return maquinaEstados.getJogadaAutomatica(); }
    public String getNomeJogadorAtual() { return maquinaEstados.getNomeJogadorAtual(); }
    public String getNomeVencedor() { return maquinaEstados.getNomeVencedor(); }
    public TipoFicha getFichaVencedor() { return maquinaEstados.getFichaVencedor(); }
    public String getConfigJogadores() { return maquinaEstados.getConfigJogadores(); }
    public List<TipoFicha> getTabuleiro() { return maquinaEstados.getTabuleiro(); }
    public boolean temMinijogoDisponivel() { return maquinaEstados.temMinijogoDisponivel(); }
    public boolean isComputadorAJogar() { return maquinaEstados.isComputadorAJogar(); }
    public String getPerguntaMinijogo() { return maquinaEstados.getPerguntaMinijogo(); }
    public boolean isValidaRespostaMinijogo(String resposta) { return maquinaEstados.isValidaRespostaMinijogo(resposta); }
    public boolean ganhouUltimoMinijogo() { return maquinaEstados.ganhouUltimoMinijogo(); }
    public boolean isAcabadoMinijogo() { return maquinaEstados.isAcabadoMinijogo(); }
    public int getPontuacaoAtualMinijogo() { return maquinaEstados.getPontuacaoAtualMinijogo(); }
    public int getNumFichasEspeciaisJogadorAtual() { return maquinaEstados.getNumFichasEspeciaisJogadorAtual(); }
    public boolean jogoAcabou() { return maquinaEstados.jogoAcabou(); }
    public int getNumCreditos() { return maquinaEstados.getNumCreditos(); }
    public boolean podeVoltarAtras() { return maquinaEstados.podeVoltarAtras(); }
    public int getNumCreditosJogaveis() { return maquinaEstados.getNumCreditosJogaveis(); }
    public boolean isJogavelColuna(int coluna) { return maquinaEstados.isJogavelColuna(coluna); }
    public boolean temProximo() { return maquinaEstados.temProximo(); }
    public boolean isEmpatado() { return maquinaEstados.isEmpatado(); }
    public String getDescricaoComandoAtual() { return maquinaEstados.getDescricaoComandoAtual(); }
    public TipoFicha getFichaAtual() { return maquinaEstados.getFichaAtual(); }
    public boolean isReplayAtivo() { return maquinaEstados.isReplayAtivo(); }

    public Situacao getSituacao() { return maquinaEstados.getSituacao(); }


}
