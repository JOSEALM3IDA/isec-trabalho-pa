package jogo;

import jogo.logica.QuatroEmLinhaMaquinaEstados;
import jogo.iu.texto.QuatroEmLinhaUITexto;

public class QuatroEmLinhaApp {
    public static void main(String[] args) {
        QuatroEmLinhaMaquinaEstados maquinaEstados = new QuatroEmLinhaMaquinaEstados();
        QuatroEmLinhaUITexto uiTexto = new QuatroEmLinhaUITexto(maquinaEstados);
        uiTexto.comecar();
    }
}
