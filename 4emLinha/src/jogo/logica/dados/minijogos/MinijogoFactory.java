package jogo.logica.dados.minijogos;

public class MinijogoFactory {

    private static int minijogoCount = 0;

    public static Minijogo getMinijogo() {
        minijogoCount++;

        if (minijogoCount % 2 == 0) return new Palavras();

        return new Calculos();
    }

    public static void reset() { minijogoCount = 0; }
}
