package jogo.iu.texto;

public class QuatroEmLinhaUI {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_VERMELHO = "\u001B[31m";
    public static final String ANSI_VERDE = "\u001B[32m";
    public static final String ANSI_AZUL = "\u001B[34m";
    public static final String FICHA_BRANCA = "\uD83D\uDCBF";
    public static final String FICHA_AZUL = ANSI_AZUL + FICHA_BRANCA + ANSI_RESET;
    public static final String FICHA_VERMELHA = ANSI_VERMELHO + FICHA_BRANCA + ANSI_RESET;
}
