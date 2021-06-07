package jogo.iu.grafica.estados;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import jogo.iu.grafica.resources.MusicPlayer;
import jogo.iu.grafica.stage.MenuBarJogo;
import jogo.iu.grafica.stage.NormalMenuButton;
import jogo.iu.grafica.stage.SlotTabuleiro;
import jogo.logica.Propriedades;
import jogo.logica.QuatroEmLinhaObservable;
import jogo.logica.dados.tabuleiro.TipoFicha;
import jogo.logica.estados.Situacao;
import jogo.utils.Constantes;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class PedeDecisaoJogadaPane extends BorderPane {
    private final QuatroEmLinhaObservable observable;

    private MenuBarJogo menuBarJogo;

    private GridPane tabuleiro;
    private double tamanhoSlot;

    private NormalMenuButton voltarAtrasButton;
    private NormalMenuButton aceitarMinijogoButton;
    private NormalMenuButton jogarFichaEspecialButton;
    private NormalMenuButton desistirButton;

    public PedeDecisaoJogadaPane(QuatroEmLinhaObservable observable) {
        this.observable = observable;
        criarLayout();
        registarListeners();
        registarObservador();
        atualiza();
    }

    private void adicionarGridNode(SlotTabuleiro slot, int lin, int col) {
        tabuleiro.add(slot, col, observable.getNumLinhas() - 1 - lin);

        if (observable.isComputadorAJogar()) return;

        slot.setOnMouseClicked(e -> {
            if (!observable.isJogavelColuna(GridPane.getColumnIndex(slot))) return;

            MusicPlayer.playMusic(Constantes.SOM_PECA_DROP);
            observable.jogarFicha(GridPane.getColumnIndex(slot));
        });

        slot.setOnMouseEntered(e -> {
            int colEntered = GridPane.getColumnIndex(slot);
            Color corHover = Color.web(switch(observable.getFichaAtual()) {
                case FICHA_VERMELHA -> Constantes.COR_VERMELHA_HOVER_HEX;
                case FICHA_AMARELA -> Constantes.COR_AMARELA_HOVER_HEX;
                case NONE -> "0xFFFFFF";
            });

            for (Node node : tabuleiro.getChildren()) {
                if (GridPane.getColumnIndex(node) == colEntered) {
                    ((SlotTabuleiro) node).setMouseInside(true, corHover);
                }
            }
        });

        slot.setOnMouseExited(e -> {
            int colEntered = GridPane.getColumnIndex(slot);
            for (Node node : tabuleiro.getChildren()) {
                if (GridPane.getColumnIndex(node) == colEntered) {
                    ((SlotTabuleiro) node).setMouseInside(false);
                }
            }
        });
    }

    private void updateTabuleiro() {
        tabuleiro = new GridPane();
        tabuleiro.setAlignment(Pos.CENTER);

        int numColunas = observable.getNumColunas();
        int numLinhas = observable.getNumLinhas();
        tamanhoSlot = 600.0 / numColunas;

        List<TipoFicha> tabuleiroActual = observable.getTabuleiro();

        for (int col = 0; col < numColunas; col++) {
            for (int lin = 0; lin < numLinhas; lin++) {
                SlotTabuleiro slot = new SlotTabuleiro(tabuleiroActual.get(lin * numColunas + col), tamanhoSlot);
                adicionarGridNode(slot, lin, col);
            }
        }

        setCenter(tabuleiro);
    }

    private void criarLayout() {
        setBackground(new Background(new BackgroundFill(Color.web("0xc5c5c5"), CornerRadii.EMPTY, Insets.EMPTY)));

        menuBarJogo = new MenuBarJogo();
        setTop(menuBarJogo);

        HBox buttonBox = new HBox(20);

        voltarAtrasButton = new NormalMenuButton("Voltar Atrás");
        aceitarMinijogoButton = new NormalMenuButton("Minijogo");
        jogarFichaEspecialButton = new NormalMenuButton("Ficha Especial");
        desistirButton = new NormalMenuButton("Desistir");

        buttonBox.getChildren().addAll(voltarAtrasButton, aceitarMinijogoButton, jogarFichaEspecialButton, desistirButton);

        buttonBox.setBackground(new Background(new BackgroundFill(
                Color.web(Constantes.COR_AZUL_HEX),
                CornerRadii.EMPTY, Insets.EMPTY)));

        buttonBox.setAlignment(Pos.CENTER);

        buttonBox.setMinWidth(Constantes.LARG_SCENE);
        buttonBox.setMinHeight(Constantes.ALT_FOOTER);

        setBottom(buttonBox);
    }

    private void setLockBotoes(boolean val) {
        voltarAtrasButton.setDisable(val);
        aceitarMinijogoButton.setDisable(val);
        jogarFichaEspecialButton.setDisable(val);
        desistirButton.setDisable(val);
    }

    private void registarListeners() {
    }

    private void registarObservador() { observable.addPropertyChangeListener(Propriedades.MUDA_ESTADO, evt -> atualiza()); }
    private void atualiza() {
        boolean isEstadoCorreto = observable.getSituacao() == Situacao.PedeDecisaoJogada;
        this.setVisible(isEstadoCorreto);

        if (!isEstadoCorreto) return;

        updateTabuleiro();

        if (!observable.isComputadorAJogar()) {
            setLockBotoes(false);
            return;
        }

        setLockBotoes(true);

        Thread thread = new Thread(() -> {
            try { TimeUnit.MILLISECONDS.sleep(Constantes.MILISEGUNDOS_SLEEP_JOGADA_COMPUTADOR); }
            catch (InterruptedException ie) { Thread.currentThread().interrupt(); }

            Platform.runLater(() -> {
                MusicPlayer.playMusic(Constantes.SOM_PECA_DROP);
                int jogadaAutomatica = observable.getJogadaAutomatica();
                observable.jogarFicha(jogadaAutomatica);
            });
        });

        thread.start();
    }
}
