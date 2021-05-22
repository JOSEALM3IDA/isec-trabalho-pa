package jogo.iu.texto;

import jogo.logica.QuatroEmLinhaMaquinaEstados;
import jogo.logica.dados.TipoFicha;
import jogo.logica.dados.jogadores.TipoJogador;
import jogo.utils.UtilUITexto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class QuatroEmLinhaUITexto {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_VERMELHO = "\u001B[31m";
    private static final String ANSI_AZUL = "\u001B[34m";
    public static final String ANSI_AMARELO = "\u001B[33m";
    private static final String FICHA_AMARELA_PRINT = ANSI_AMARELO + 'A' + ANSI_RESET;
    private static final String FICHA_VERMELHA_PRINT = ANSI_VERMELHO + 'V' + ANSI_RESET;
    private static final String DIVISORIA_TABULEIRO_PRINT = ANSI_AZUL + '|' + ANSI_RESET;

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
                String fileName;
                while (true) {
                    fileName = UtilUITexto.getResposta("Nome do ficheiro?");

                    if (fileName.isEmpty()) return;

                    if (continuarJogo(fileName)) break;

                    System.out.println("Load falhou.");
                }
                System.out.println("Load com sucesso!");
            }
            case 3 -> maquinaEstados.verReplay();
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

        if (maquinaEstados.isComputadorAJogar()) {
            int jogada = maquinaEstados.getJogadaAutomatica();
            String nomeJogadorAtual = maquinaEstados.getNomeJogadorAtual();
            maquinaEstados.jogarFicha(jogada);
            System.out.println("\nO jogador " + nomeJogadorAtual + " jogou na coluna " + (jogada + 1) + "!");
            return;
        }

        ArrayList<String> opcoes = new ArrayList<>();
        opcoes.add("Jogar ficha");
        opcoes.add("Undo jogada");
        opcoes.add("Gravar jogo");
        if (maquinaEstados.temMinijogoDisponivel()) {
            System.out.println("\nPode jogar um minijogo!");
            opcoes.add("Jogar minijogo");
        }

        int numFichasEspeciais = maquinaEstados.getNumFichasEspeciaisJogadorAtual();
        if (numFichasEspeciais > 0) {
            opcoes.add("Jogar ficha especial");
            printNumFichasEspeciais(numFichasEspeciais);
        }

        opcoes.add("Desistir");
        opcoes.add("Sair");

        switch (UtilUITexto.getOpcao("--- PEDE JOGADA ---", opcoes.toArray(new String[0]))) {
            case 1 -> maquinaEstados.jogarFicha(UtilUITexto.getInteiro("Coluna a jogar: ") - 1);
            case 2 -> maquinaEstados.undoJogada();
            case 3 -> {
                while (!gravarJogo(UtilUITexto.getResposta("Nome do ficheiro?"))) System.out.println("Save falhou.");
                System.out.println("Save com sucesso!");
            }
            case 4 -> maquinaEstados.aceitarMinijogo();
            case 5 -> maquinaEstados.jogarFichaEspecial(UtilUITexto.getInteiro("Coluna a remover: ") - 1);
            case 6 -> maquinaEstados.desistir();
            default -> doSair = true;
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

        System.out.println("\nO jogador " + maquinaEstados.getNomeVencedor() + " fez quatro em linha e ganhou o jogo!");

        if (UtilUITexto.getOpcao("O que pretende fazer?", "Voltar ao menu", "Sair") == 1) {
            maquinaEstados.avancar();
            return;
        }

        doSair = true;
    }

    private void assisteJogadaUI() {
    }

    private void printNumFichasEspeciais(int numFichasEspeciais) {
        if (numFichasEspeciais <= 0) return;

        if (numFichasEspeciais > 1) {
            System.out.println("\nTem " + numFichasEspeciais + " fichas especiais!");
            return;
        }

        System.out.println("\nTem uma ficha especial!");
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
        File f = new File("saves/" + nomeFicheiro + ".save");
        if (f.getParentFile().mkdirs()) System.out.println("Criado diretório saves...");

        FileOutputStream fos;
        try {
            if (!f.createNewFile()) {
                System.out.println("Ficheiro " + nomeFicheiro + " ja existe! ");
                return false;
            }
            fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(maquinaEstados);
            oos.close();
            fos.close();
        } catch (IOException ioe) { ioe.printStackTrace(); return false; }

        return true;
    }

    // Podia ir para o utils com um generico
    private boolean continuarJogo(String nomeFicheiro) {
        try (FileInputStream fis = new FileInputStream("saves/" + nomeFicheiro + ".save");
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            Object obj = ois.readObject();

            if (!(obj instanceof QuatroEmLinhaMaquinaEstados)) return false;

            maquinaEstados = (QuatroEmLinhaMaquinaEstados) obj;
        } catch (IOException | ClassNotFoundException ex) { return false; }

        return true;
    }
}
