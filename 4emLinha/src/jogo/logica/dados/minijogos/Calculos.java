package jogo.logica.dados.minijogos;

import jogo.utils.Utils;

import java.util.Random;

public class Calculos extends MinijogoAdapter {

    static final int TEMPO_MAXIMO = 30; // Segundos
    static final char[] OPERADORES = {'+', '-', '/', '*'};

    private int inteiro1 = -1, inteiro2 = -1;
    private char operador = ' ';

    @Override
    public void comecar() {
        isComecado = true;
        cronometro.comecar();

        gerarNovaPergunta();
    }

    @Override
    public boolean isValidaResposta(String resposta) {
        try { Double.parseDouble(resposta); } catch (NumberFormatException nfe) { return false; }
        return true;
    }

    @Override
    public void receberResposta(String resposta) {
        if (isAcabado) return;

        if (inteiro1 == -1 || inteiro2 == -1) return;
        if (operador == ' ') return;

        if (!cronometro.isAtivo()) return;

        if (cronometro.passouTempo(TEMPO_MAXIMO)) {
            cronometro.acabar();
            isGanho = false;
            isAcabado = true;
            return;
        }



        if (!isValidaResposta(resposta)) return;

        double resultado;
        try { resultado = aplicarOperador(inteiro1, inteiro2); } catch (NumberFormatException nfe) { return; }
        finally { gerarNovaPergunta(); }

        resultado = Utils.round(resultado, 3);

        if (resultado != Double.parseDouble(resposta)) return;

        System.out.println("CORRETO!");

        numPontos++;

        if (numPontos >= 5) {
            isGanho = true;
            isAcabado = true;
        }

    }

    @Override
    protected void gerarNovaPergunta() {
        System.out.println("NOVA PERGUNTA");
        Random random = new Random();
        inteiro1 = random.nextInt(100);
        inteiro2 = random.nextInt(100);

        operador = OPERADORES[random.nextInt(OPERADORES.length)];

        perguntaAtual = inteiro1 + " " + operador + " " + inteiro2 + " = ?";
    }

    private double aplicarOperador(int inteiro1, int inteiro2) throws NumberFormatException {
        switch (operador) {
            case '+' -> { return inteiro1 + inteiro2; }
            case '-' -> { return inteiro1 - inteiro2; }
            case '/' -> { return (double) inteiro1 / inteiro2; }
            case 'x' -> { return inteiro1 * inteiro2; }
            default -> throw new NumberFormatException();
        }
    }


}
