package jogo.logica.dados;

import java.io.Serializable;

public class Computador extends Jogador {
    private static int count = 1;

    public Computador() { super("Computador" + count++); }

    @Override
    public String toString() { return nome + " (CPU)"; }
}
