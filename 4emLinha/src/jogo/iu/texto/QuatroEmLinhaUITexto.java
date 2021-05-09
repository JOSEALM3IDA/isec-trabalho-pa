package jogo.iu.texto;

import jogo.logica.QuatroEmLinhaMaquinaEstados;
import jogo.logica.dados.Humano;

public class QuatroEmLinhaUITexto {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_VERMELHO = "\u001B[31m";
    public static final String ANSI_VERDE = "\u001B[32m";
    public static final String ANSI_AZUL = "\u001B[34m";
    public static final String FICHA_BRANCA = "\uD83D\uDCBF";
    public static final String FICHA_AZUL = ANSI_AZUL + FICHA_BRANCA + ANSI_RESET;
    public static final String FICHA_VERMELHA = ANSI_VERMELHO + FICHA_BRANCA + ANSI_RESET;

    private final QuatroEmLinhaMaquinaEstados maquinaEstados;
    private boolean doSair;

    public QuatroEmLinhaUITexto(QuatroEmLinhaMaquinaEstados maquinaEstados) {
        this.maquinaEstados = maquinaEstados;
    }

    public void comecar() {
        System.out.println("***Bem vindo ao Quatro em Linha!***");

        doSair = false;
        while (!doSair) {
            switch (maquinaEstados.getSituacao()) {
                case PedeDecisaoInicio -> pedeDecisaoInicioUI();
                case PedeConfiguracao -> pedeConfiguracaoUI();
                case PedeDecisaoJogada -> pedeDecisaoJogadaUI();
                case JogaMinijogo -> jogaMinijogoUI();
                case FimJogada -> fimJogadaUI();
                case FimJogo -> fimJogoUI();
                case AssisteJogada -> assisteJogadaUI();
            }
        }
    }

    private void pedeDecisaoInicioUI() {
        switch (UtilUITexto.getOpcao("--- MENU INICIAL ---",
                "Iniciar jogo", "Continuar jogo", "Ver replay", "Sair")) {
            case 1 -> maquinaEstados.iniciarJogo();
            case 2 -> maquinaEstados.continuarJogo();
            case 3 -> maquinaEstados.verReplay();
            default -> doSair = true;
        }
    }

    private void pedeConfiguracaoUI() {

        switch (UtilUITexto.getOpcao("Jogador " + (maquinaEstados.getNumJogadores() + 1) + ":",
                "Humano", "Computador", "Sair")) {
            case 1 -> {
                String nome;
                while (true) {
                    nome = UtilUITexto.getResposta("Qual o nome do jogador?");

                    if (!maquinaEstados.existeJogador(nome))
                        break;

                    System.out.println("Esse nome ja esta a ser utilizado. Por favor insira outro nome.");
                }

                maquinaEstados.adicionarJogador(nome);
            }
            case 2 -> maquinaEstados.adicionarJogador();
            default -> doSair = true;
        }

        System.out.println("\n" + maquinaEstados.getConfigJogadores());
    }

    private void pedeDecisaoJogadaUI() {
        //System.out.println("PEDE DECISAO JOGADA - WIP");
    }

    private void jogaMinijogoUI() {
    }

    private void fimJogadaUI() {
    }

    private void fimJogoUI() {
    }

    private void assisteJogadaUI() {
    }
}
