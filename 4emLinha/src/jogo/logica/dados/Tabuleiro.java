package jogo.logica.dados;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tabuleiro implements Serializable {
    private static final int DEFAULT_NUM_LINHAS = 6;
    private static final int DEFAULT_NUM_COLUNAS = 7;

    private final int numColunas;
    private final int numLinhas;

    ArrayList<TipoFicha> tabuleiro = new ArrayList<>(DEFAULT_NUM_LINHAS * DEFAULT_NUM_COLUNAS);

    public Tabuleiro() {
        numColunas = DEFAULT_NUM_COLUNAS;
        numLinhas = DEFAULT_NUM_LINHAS;
        initTabuleiro();
    }

    private void initTabuleiro() {
        tabuleiro.clear();
        for (int i = 0; i < numLinhas * numColunas; i++) tabuleiro.add(TipoFicha.NONE);
    }

    public void addFicha(int col, TipoFicha ficha) {
        for (int lin = 0; lin < numLinhas; lin++) {
            if (getFichaEm(lin, col) != TipoFicha.NONE) continue;

            setFichaEm(lin, col, ficha);
            return;
        }
    }

    private TipoFicha getFichaEm(int lin, int col) { return tabuleiro.get(lin * numColunas + col); }

    private void setFichaEm(int lin, int col, TipoFicha tipoFicha) { tabuleiro.set(lin * numColunas + col, tipoFicha); }

    public List<TipoFicha> getFichas() { return Collections.unmodifiableList(tabuleiro); }
    public int getNumLinhas() { return numLinhas; }
    public int getNumColunas() { return numColunas; }

    public boolean checkQuatroEmLinha(TipoFicha ficha) {
        return checkQuatroEmLinhaLinhas(ficha)
                || checkQuatroEmLinhaColunas(ficha)
                || checkQuatroEmLinhaDiagonaisSubir(ficha)
                || checkQuatroEmLinhaDiagonaisDescer(ficha);
    }

    private boolean checkQuatroEmLinhaLinhas(TipoFicha ficha) {
        int counter;
        for (int lin = 0; lin < numLinhas; lin++) {
            counter = 0;
            for (int col = 0; col < numColunas; col++) {
                if (getFichaEm(lin, col) == ficha) counter++;

                if (counter >= 4) return true;
            }
        }

        return false;
    }

    private boolean checkQuatroEmLinhaColunas(TipoFicha ficha) {
        int counter;
        for (int col = 0; col < numColunas; col++) {
            counter = 0;
            for (int lin = 0; lin < numLinhas; lin++) {
                if (getFichaEm(lin, col) == ficha) counter++;

                if (counter >= 4) return true;
            }
        }

        return false;
    }

    private boolean checkQuatroEmLinhaDiagonaisSubir(TipoFicha ficha) {
        return false;
    }

    private boolean checkQuatroEmLinhaDiagonaisDescer(TipoFicha ficha) {
        return false;
    }

    public void limpar() { initTabuleiro(); }

}
