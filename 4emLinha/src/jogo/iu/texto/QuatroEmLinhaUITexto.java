package jogo.iu.texto;

import jogo.logica.QuatroEmLinhaMaquinaEstados;
import jogo.logica.dados.TipoFicha;
import jogo.logica.dados.jogadores.TipoJogador;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class QuatroEmLinhaUITexto {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_VERMELHO = "\u001B[31m";
    private static final String ANSI_AZUL = "\u001B[34m";
    private static final String FICHA_AZUL_PRINT = ANSI_AZUL + '@' + ANSI_RESET;
    private static final String FICHA_VERMELHA_PRINT = ANSI_VERMELHO + '@' + ANSI_RESET;

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
                while (!continuarJogo(UtilUITexto.getResposta("Nome do ficheiro?"))) {
                    System.out.println("Load falhou.");
                }
                System.out.println("Load com sucesso!");
            }
            case 3 -> maquinaEstados.verReplay();
            default -> doSair = true;
        }
    }

    private void pedeConfiguracaoUI() {

        switch (UtilUITexto.getOpcao("Jogador " + (maquinaEstados.getNumJogadores() + 1) + ":",
                "Humano", "Computador", "Sair")) {
            case 1 -> maquinaEstados.adicionarJogador(TipoJogador.HUMANO, pedeNomeJogador());
            case 2 -> maquinaEstados.adicionarJogador(TipoJogador.COMPUTADOR, pedeNomeJogador());
            default -> doSair = true;
        }

        System.out.println("\n" + maquinaEstados.getConfigJogadores());
    }

    private void pedeDecisaoJogadaUI() {
        System.out.println();
        printTabuleiro(maquinaEstados.getTabuleiro());

        if (maquinaEstados.isComputadorAJogar()) {
            int jogada = maquinaEstados.getJogadaAutomatica();
            String nomeJogadorAtual = maquinaEstados.getNomeJogadorAtual();
            maquinaEstados.jogarFicha(jogada);
            System.out.println("\nO jogador " + nomeJogadorAtual + " jogou na coluna " + jogada + "!");
            return;
        }

        ArrayList<String> opcoes = new ArrayList<>();
        opcoes.add("Jogar ficha");
        opcoes.add("Undo jogada");
        opcoes.add("Gravar jogo");
        if (maquinaEstados.temMinijogoDisponivel()) {
            System.out.println("Pode jogar um minijogo!");
            opcoes.add("Jogar minijogo");
        }
        opcoes.add("Sair");

        switch (UtilUITexto.getOpcao("--- PEDE JOGADA ---", opcoes.toArray(new String[0]))) {
            case 1 -> maquinaEstados.jogarFicha(UtilUITexto.getInteiro("Coluna a jogar:") - 1);
            case 2 -> maquinaEstados.undoJogada();
            case 3 -> {
                while (!gravarJogo(UtilUITexto.getResposta("Nome do ficheiro?"))) System.out.println("Save falhou.");
                System.out.println("Save com sucesso!");
            }
            case 4 -> maquinaEstados.aceitarMinijogo();
            default -> doSair = true;
        }
    }

    private void jogaMinijogoUI() {
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

    private void printTabuleiro(List<TipoFicha> tabuleiro) {
        int numLinhas = maquinaEstados.getNumLinhas();
        int numColunas = maquinaEstados.getNumColunas();

        for (int i = numLinhas - 1; i >= 0; i--) {
            for (int j = 0; j < numColunas; j++) {
                System.out.print('|');
                switch (tabuleiro.get(i * numColunas + j)) {
                    case FICHA_AZUL -> System.out.print(FICHA_AZUL_PRINT);
                    case FICHA_VERMELHA -> System.out.print(FICHA_VERMELHA_PRINT);
                    default -> System.out.print(" ");
                }
            }
            System.out.println('|');
        }
    }

    private String pedeNomeJogador() {
        String nome;
        while (true) {
            nome = UtilUITexto.getResposta("Qual o nome do jogador?");

            if (!maquinaEstados.existeJogador(nome)) break;

            System.out.println("Esse nome ja esta a ser utilizado. Por favor insira outro nome.");
        }

        return nome;
    }

    private boolean gravarJogo(String nomeFicheiro) {
        File f = new File("saves/" + nomeFicheiro + ".save");
        if (f.getParentFile().mkdirs()) System.out.println("Criado diretorio saves...");

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
