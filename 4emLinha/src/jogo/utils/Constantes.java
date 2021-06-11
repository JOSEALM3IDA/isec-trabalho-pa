package jogo.utils;

public class Constantes {
    private Constantes() {}

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_VERMELHO = "\u001B[31m";
    public static final String ANSI_AZUL = "\u001B[34m";
    public static final String ANSI_AMARELO = "\u001B[33m";
    public static final String FICHA_AMARELA_PRINT = ANSI_AMARELO + 'A' + ANSI_RESET;
    public static final String FICHA_VERMELHA_PRINT = ANSI_VERMELHO + 'V' + ANSI_RESET;
    public static final String DIVISORIA_TABULEIRO_PRINT = ANSI_AZUL + '|' + ANSI_RESET;

    public static final String REPLAY_PATH = "replays/";
    public static final String SAVE_PATH = "saves/";
    public static final int NUM_MAX_REPLAYS = 5;
    public static final int MILISEGUNDOS_SLEEP_JOGADA_COMPUTADOR = 1200;

    public static final double LARG_SCENE = 1280;
    public static final double ALT_SCENE = 720;
    public static final double LARG_SIDEBAR = 320;
    public static final double ALT_FOOTER = 100;
    public static final int LARG_MENU_BTN = 220;
    public static final int ALT_MENU_BTN = 50;
    public static final int LARG_NORMAL_BTN = 170;
    public static final int ALT_NORMAL_BTN = 40;
    public static final double TAM_TABULEIRO = 600.0;

    public static final String FONT_MENU = "RetroWide.ttf";
    public static final String IMG_BACKGROUND_MENU = "menu-background.jpg";

    public static final String SOM_PECA_DROP = "peca-drop.mp3";
    public static final String SOM_HOVER_BTN = "btn-hover.wav";
    public static final String SOM_CLICK_BTN = "btn-click.mp3";

    public static final String COR_AZUL_HEX = "0x0073d3";
    public static final String COR_VERMELHA_HEX = "0xff0000";
    public static final String COR_AMARELA_HEX = "0xeee800";
    public static final String COR_VERMELHA_HOVER_HEX = "0xffc4c4";
    public static final String COR_AMARELA_HOVER_HEX = "0xeee896";
    public static final String BACKGROUND_COLOR_HEX = "0x8197a7";

    public static final String SLOT_VERMELHO_IMG = "slot-vermelho.png";
    public static final String SLOT_AMARELO_IMG = "slot-amarelo.png";
    public static final String SLOT_VAZIO_IMG = "slot-vazio.png";

}
