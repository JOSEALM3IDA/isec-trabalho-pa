package jogo.logica.dados;

import jogo.logica.dados.minijogos.Calculos;
import jogo.logica.dados.minijogos.Minijogo;
import jogo.logica.dados.minijogos.Palavras;

public class MinijogoFactory {

    private static int minijogoCount = 0;

    public static Minijogo getMinijogo() {
        minijogoCount++;

        if (minijogoCount % 2 == 0) return new Palavras();

        return new Calculos();
    }

}
