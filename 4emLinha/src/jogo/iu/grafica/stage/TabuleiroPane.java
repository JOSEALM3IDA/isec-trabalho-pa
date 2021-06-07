package jogo.iu.grafica.stage;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import jogo.iu.grafica.resources.MusicPlayer;
import jogo.logica.QuatroEmLinhaObservable;
import jogo.logica.dados.tabuleiro.TipoFicha;
import jogo.utils.Constantes;

import java.util.List;

public class TabuleiroPane extends GridPane {

    private final QuatroEmLinhaObservable observable;

    private final int numColunas;
    private final int numLinhas;
    private final double tamanhoSlot;

    private boolean isJogarFichaEspecial = false;

    public TabuleiroPane(QuatroEmLinhaObservable observable) {
        this.observable = observable;
        setAlignment(Pos.CENTER);
        numColunas = observable.getNumColunas();
        numLinhas = observable.getNumLinhas();
        tamanhoSlot = Constantes.TAM_TABULEIRO / Math.max(numColunas, numLinhas);
    }

    private void adicionarGridNode(SlotTabuleiro slot, int lin, int col) {
        add(slot, col, observable.getNumLinhas() - 1 - lin);

        if (observable.isComputadorAJogar()) return;

        slot.setOnMouseClicked(e -> {
            if (!observable.isJogavelColuna(GridPane.getColumnIndex(slot))) return;

            MusicPlayer.playMusic(Constantes.SOM_PECA_DROP);
            observable.jogarFicha(GridPane.getColumnIndex(slot));
        });

        slot.setOnMouseEntered(e -> {
            if (observable.jogoAcabou()) return;

            int colEntered = GridPane.getColumnIndex(slot);
            Color corHover = Color.web(switch(observable.getFichaAtual()) {
                case FICHA_VERMELHA -> Constantes.COR_VERMELHA_HOVER_HEX;
                case FICHA_AMARELA -> Constantes.COR_AMARELA_HOVER_HEX;
                case NONE -> Constantes.BACKGROUND_COLOR_HEX;
            });

            for (Node child : getChildren()) {
                if (GridPane.getColumnIndex(child) == colEntered && ((SlotTabuleiro) child).getTipoFicha() == TipoFicha.NONE) {
                    ((SlotTabuleiro) child).setMouseInside(true, corHover);
                    return;
                }
            }
        });

        slot.setOnMouseExited(e -> {
            if (observable.jogoAcabou()) return;

            int colEntered = GridPane.getColumnIndex(slot);
            for (Node node : getChildren()) {
                if (GridPane.getColumnIndex(node) == colEntered) {
                    ((SlotTabuleiro) node).setMouseInside(false);
                }
            }
        });
    }

    public void atualizar() {
        getChildren().clear();

        List<TipoFicha> tabuleiroActual = observable.getTabuleiro();

        for (int col = 0; col < numColunas; col++) {
            for (int lin = 0; lin < numLinhas; lin++) {
                SlotTabuleiro slot = new SlotTabuleiro(tabuleiroActual.get(lin * numColunas + col), tamanhoSlot);
                adicionarGridNode(slot, lin, col);
            }
        }
    }

    public boolean isJogarFichaEspecial() { return isJogarFichaEspecial; }
    public void setJogarFichaEspecial(boolean isJogarFichaEspecial) { this.isJogarFichaEspecial = isJogarFichaEspecial; }
}
