package jogo.logica.dados.minijogos;

import jogo.utils.Utils;

public class Calculos extends MinijogoAdapter {

    static final int TEMPO_MAXIMO_SEGUNDOS = 30;
    static final char[] OPERADORES = {'+', '-', '/', 'x'};

    private int inteiro1 = -1;
    private int inteiro2 = -1;
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

        if (cronometro.passouTempo(TEMPO_MAXIMO_SEGUNDOS)) {
            cronometro.acabar();
            isGanho = false;
            isAcabado = true;
            return;
        }

        if (!isValidaResposta(resposta)) return;

        double respostaCorreta = -1;
        try { respostaCorreta = aplicarOperador(inteiro1, inteiro2); }
        catch (NumberFormatException nfe) { return; }
        finally { gerarNovaPergunta(); }

        respostaCorreta = Utils.round(respostaCorreta, 3);

        double respostaDada = Double.parseDouble(resposta);
        if (respostaCorreta != respostaDada) return;

        numPontos++;

        if (numPontos >= 5) {
            isGanho = true;
            isAcabado = true;
        }

    }

    @Override
    protected void gerarNovaPergunta() {
        inteiro1 = Utils.getNumeroRandom(100);
        inteiro2 = Utils.getNumeroRandom(100);

        operador = OPERADORES[Utils.getNumeroRandom(OPERADORES.length)];

        perguntaAtual = inteiro1 + " " + operador + " " + inteiro2 + " = ?";
    }

    private double aplicarOperador(int inteiro1, int inteiro2) throws NumberFormatException {
        return switch (operador) {
            case '+' -> inteiro1 + inteiro2;
            case '-' -> inteiro1 - inteiro2;
            case '/' -> (double) inteiro1 / inteiro2;
            case 'x' -> inteiro1 * inteiro2;
            default -> throw new NumberFormatException();
        };
    }
}
