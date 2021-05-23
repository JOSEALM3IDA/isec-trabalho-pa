package jogo.iu.texto;

import jogo.logica.QuatroEmLinhaMaquinaEstados;
import jogo.logica.dados.TipoFicha;
import jogo.logica.dados.jogadores.TipoJogador;
import jogo.utils.Constantes;
import jogo.utils.UtilUITexto;
import jogo.utils.Utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class QuatroEmLinhaUITexto {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_VERMELHO = "\u001B[31m";
    private static final String ANSI_AZUL = "\u001B[34m";
    private static final String ANSI_AMARELO = "\u001B[33m";
    private static final String FICHA_AMARELA_PRINT = ANSI_AMARELO + 'A' + ANSI_RESET;
    private static final String FICHA_VERMELHA_PRINT = ANSI_VERMELHO + 'V' + ANSI_RESET;
    private static final String DIVISORIA_TABULEIRO_PRINT = ANSI_AZUL + '|' + ANSI_RESET;

    private static final int MILISEGUNDOS_SLEEP_JOGADA_COMPUTADOR = 1200;

    private QuatroEmLinhaMaquinaEstados maquinaEstados;
    private boolean doSair;

    public QuatroEmLinhaUITexto(QuatroEmLinhaMaquinaEstados maquinaEstados) { this.maquinaEstados = maquinaEstados; }

    public void comecar() {
        System.out.println("***Bem vindo ao Quatro em Linha!***");

        doSair = false;
        while (!doSair) {
            switch (maquinaEstados.getSituacao()) {
                case PedeDecisaoInicio -> pedeDecisaoInicioUI();
                case PedeConfiguracao -> pedeConfiguracaoUI();
                case PedeDecisaoJogada -> pedeDecisaoJogadaUI();
                case JogaMinijogo -> jogaMinijogoUI();
                case FimJogo -> fimJogoUI();
                case AssisteJogada -> assisteJogadaUI();
            }
        }
    }

    private void pedeDecisaoInicioUI() {

        switch (UtilUITexto.getOpcao("--- MENU INICIAL ---",
                "Iniciar jogo", "Continuar jogo", "Ver replay", "Sair")) {
            case 1 -> maquinaEstados.iniciarJogo();
            case 2 -> {
                String nomeFicheiro;
                while (true) {
                    nomeFicheiro = UtilUITexto.getResposta("Nome do ficheiro?");

                    if (nomeFicheiro.isEmpty()) return;

                    String path = "saves/" + nomeFicheiro + ".save";

                    try {
                        maquinaEstados = Utils.lerObjeto(path, QuatroEmLinhaMaquinaEstados.class);
                        System.out.println("Load com sucesso!");
                        break;
                    } catch (ClassNotFoundException | IOException ignored) {}
                }

            }
            case 3 -> {
                String[] ficheiros = Utils.getFicheirosNoDiretorio(Constantes.REPLAY_PATH);

                if (ficheiros.length <= 0) {
                    System.out.println("\nNão há jogos para dar replay.");
                    break;
                }

                int opt = UtilUITexto.getOpcao("Ficheiro: ", false, ficheiros);
                maquinaEstados.verReplay( ficheiros[opt - 1]);
            }
            default -> doSair = true;
        }
    }

    private void pedeConfiguracaoUI() {

        String nomeJogador;

        switch (UtilUITexto.getOpcao("Jogador " + (maquinaEstados.getNumJogadores() + 1) + ":",
                "Humano", "Computador", "Sair")) {
            case 1 -> {
                nomeJogador = pedeNomeJogador();

                if (nomeJogador.isEmpty()) return;

                maquinaEstados.adicionarJogador(TipoJogador.HUMANO, nomeJogador);
            }
            case 2 -> {
                nomeJogador = pedeNomeJogador();

                if (nomeJogador.isEmpty()) return;

                maquinaEstados.adicionarJogador(TipoJogador.COMPUTADOR, nomeJogador);
            }
            default -> doSair = true;
        }

        if (!doSair) System.out.println("\n" + maquinaEstados.getConfigJogadores());
    }

    private void pedeDecisaoJogadaUI() {

        System.out.println();
        printTabuleiro(maquinaEstados.getTabuleiro());

        System.out.println();

        System.out.println("É a vez de " + maquinaEstados.getNomeJogadorAtual() + ".");

        if (maquinaEstados.isComputadorAJogar()) {
            int jogada = maquinaEstados.getJogadaAutomatica();
            String nomeJogadorAtual = maquinaEstados.getNomeJogadorAtual();
            maquinaEstados.jogarFicha(jogada);
            System.out.println("\nO jogador " + nomeJogadorAtual + " jogou na coluna " + (jogada + 1) + "!");
            try { TimeUnit.MILLISECONDS.sleep(MILISEGUNDOS_SLEEP_JOGADA_COMPUTADOR); } catch (InterruptedException ignored) {}
            return;
        }

        ArrayList<String> opcoes = new ArrayList<>();
        opcoes.add("Jogar ficha"); // 1
        opcoes.add("Gravar jogo"); // 2

        // 3
        String optCreditos = "";
        if (maquinaEstados.podeVoltarAtras()) {
            System.out.println("\nTem " + maquinaEstados.getNumCreditos() + " créditos.");
            System.out.println("Pode voltar atrás!");
            optCreditos = "Voltar atrás";
        }
        opcoes.add(optCreditos);

        // 4
        String optMinijogo = "";
        if (maquinaEstados.temMinijogoDisponivel()) {
            System.out.println("\nPode jogar um minijogo!");
            optMinijogo = "Jogar minijogo";
        }
        opcoes.add(optMinijogo);

        // 5
        int numFichasEspeciais = maquinaEstados.getNumFichasEspeciaisJogadorAtual();
        String optFichaEspecial = "";
        if (numFichasEspeciais > 0) {
            System.out.println("\nTem " + numFichasEspeciais + " ficha(s) especial(is)");
            optFichaEspecial = "Jogar ficha especial";
        }
        opcoes.add(optFichaEspecial);

        opcoes.add("Desistir"); // 6

        switch (UtilUITexto.getOpcao("--- PEDE JOGADA ---", opcoes.toArray(new String[0]))) {
            case 1 -> maquinaEstados.jogarFicha(UtilUITexto.getInteiro("\nColuna a jogar: ") - 1);
            case 2 -> {
                while (!gravarJogo(UtilUITexto.getResposta("Nome do ficheiro: "))) System.out.println("Save falhou.");
                System.out.println("Save com sucesso!");
            }
            case 3 -> {
                int numCreditosAUtilizar;
                int numCreditosJogaveis = maquinaEstados.getNumCreditosJogaveis();

                do {
                    numCreditosAUtilizar = UtilUITexto.getInteiro(
                            "\nNúmero de créditos a utilizar [0 - " + numCreditosJogaveis + "]: ");
                } while (numCreditosAUtilizar < 0 || numCreditosAUtilizar > maquinaEstados.getNumCreditosJogaveis());

                if (numCreditosAUtilizar == 0) break;

                maquinaEstados.undoJogada(numCreditosAUtilizar);
                System.out.println("Voltou atrás " + numCreditosAUtilizar + " jogada(s).");
            }
            case 4 -> maquinaEstados.aceitarMinijogo();
            case 5 -> maquinaEstados.jogarFichaEspecial(UtilUITexto.getInteiro("Coluna a remover: ") - 1);
            default -> maquinaEstados.desistir();
        }
    }

    private void jogaMinijogoUI() {

        String pergunta = maquinaEstados.getPerguntaMinijogo();
        String resposta;

        do {
            resposta = UtilUITexto.getResposta(pergunta);

            if (maquinaEstados.isAcabadoMinijogo()) break;

        } while (!maquinaEstados.isValidaRespostaMinijogo(resposta));


        maquinaEstados.enviarRespostaMinijogo(resposta);

        if (maquinaEstados.isAcabadoMinijogo()) {
            if (maquinaEstados.ganhouUltimoMinijogo()) {
                System.out.println("\nGanhou o minijogo e recebeu uma peça especial!");
                return;
            }

            System.out.println("\nAcabou o tempo e perdeu o minijogo. Pontuação final: " + maquinaEstados.getPontuacaoAtualMinijogo());
        }
    }

    private void fimJogoUI() {

        System.out.println("\n--- FIM JOGO ---\n");

        printTabuleiro(maquinaEstados.getTabuleiro());

        System.out.println("\nO jogador " + maquinaEstados.getNomeVencedor() + " ganhou o jogo!");

        if (UtilUITexto.getOpcao("O que pretende fazer?", "Voltar ao menu", "Sair") == 1) {
            maquinaEstados.avancar();
            return;
        }

        doSair = true;
    }

    private void assisteJogadaUI() {
        System.out.println();
        printTabuleiro(maquinaEstados.getTabuleiro());

        System.out.print("\nEnter para continuar...");
        try { System.in.read(); } catch (IOException e) { e.printStackTrace(); }
        maquinaEstados.avancar();
    }

    private void printTabuleiro(List<TipoFicha> tabuleiro) {
        int numLinhas = maquinaEstados.getNumLinhas();
        int numColunas = maquinaEstados.getNumColunas();

        for (int i = 1; i <= numColunas; i++) System.out.print(" " + i);
        System.out.println();

        for (int i = numLinhas - 1; i >= 0; i--) {
            for (int j = 0; j < numColunas; j++) {
                System.out.print(DIVISORIA_TABULEIRO_PRINT);
                switch (tabuleiro.get(i * numColunas + j)) {
                    case FICHA_AMARELA -> System.out.print(FICHA_AMARELA_PRINT);
                    case FICHA_VERMELHA -> System.out.print(FICHA_VERMELHA_PRINT);
                    default -> System.out.print(" ");
                }
            }
            System.out.println(DIVISORIA_TABULEIRO_PRINT + ' ' + (i + 1));
        }
    }

    private String pedeNomeJogador() {
        String nome;
        while (true) {
            nome = UtilUITexto.getResposta("Qual o nome do jogador?");

            if (nome.isEmpty()) return "";

            if (!maquinaEstados.existeJogador(nome)) break;

            System.out.println("Esse nome já está a ser utilizado. Por favor insira outro nome.");
        }

        return nome;
    }

    private boolean gravarJogo(String nomeFicheiro) {
        String path = "saves/" + nomeFicheiro + ".save";
        return Utils.gravarObjeto(path, maquinaEstados);
    }
}
