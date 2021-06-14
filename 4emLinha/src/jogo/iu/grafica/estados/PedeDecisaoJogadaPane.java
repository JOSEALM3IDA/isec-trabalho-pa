package jogo.iu.grafica.estados;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Slider;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import jogo.iu.grafica.resources.MusicPlayer;
import jogo.iu.grafica.stage.menu.FooterBox;
import jogo.iu.grafica.stage.menu.MenuBarJogo;
import jogo.iu.grafica.stage.menu.button.NormalMenuButton;
import jogo.iu.grafica.stage.tabuleiro.TabuleiroPane;
import jogo.logica.Propriedades;
import jogo.logica.QuatroEmLinhaObservable;
import jogo.logica.estados.Situacao;
import jogo.utils.Constantes;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PedeDecisaoJogadaPane extends BorderPane {
    private static final String FICHA_ESPECIAL_BUTTON_TEXT = "Ficha Especial";
    private static final String FICHA_REGULAR_BUTTON_TEXT = "Ficha Regular";

    private final QuatroEmLinhaObservable observable;

    private MenuBarJogo menuBarJogo;

    private Text listaJogadores;
    private TabuleiroPane tabuleiroPane;
    private Text jogadorText1;
    private Text jogadorText2;
    private Text jogadorText3;

    private NormalMenuButton voltarAtrasButton;
    private Slider voltarAtrasSlider;

    private NormalMenuButton aceitarMinijogoButton;
    private NormalMenuButton jogarFichaEspecialButton;
    private NormalMenuButton desistirButton;

    private ScheduledExecutorService scheduler;

    public PedeDecisaoJogadaPane(QuatroEmLinhaObservable observable) {
        this.observable = observable;
        criarLayout();
        registarListeners();
        registarObservadores();
        atualizarVisibilidade();
    }

    private void criarLayout() {
        HBox listaBox = new HBox();
        listaJogadores = new Text();
        listaJogadores.setTextAlignment(TextAlignment.CENTER);
        listaJogadores.setFill(Color.WHITE);
        listaJogadores.setStyle("-fx-font-size: 20");
        listaBox.setAlignment(Pos.CENTER);
        listaBox.setPadding(new Insets(100));
        listaBox.getChildren().add(listaJogadores);
        listaBox.setMinWidth(Constantes.LARG_SIDEBAR); listaBox.setMaxWidth(Constantes.LARG_SIDEBAR);
        setLeft(listaBox);

        tabuleiroPane = new TabuleiroPane(observable);
        setCenter(tabuleiroPane);

        VBox jogadorTextBox = new VBox();
        jogadorText1 = new Text("Jogador Atual:");
        jogadorText1.setStyle("-fx-font-size: 20");
        jogadorText1.setTextAlignment(TextAlignment.CENTER);
        jogadorText1.setFill(Color.WHITE);
        jogadorText2 = new Text();
        jogadorText2.setStyle("-fx-font-size: 28; -fx-font-weight: bold");
        jogadorText2.setStroke(Color.BLACK);
        jogadorText2.setStrokeWidth(1);
        jogadorText3 = new Text();
        jogadorText3.setStyle("-fx-font-size: 20");
        jogadorText3.setTextAlignment(TextAlignment.CENTER);
        jogadorText3.setFill(Color.WHITE);

        jogadorTextBox.setAlignment(Pos.CENTER);
        jogadorTextBox.getChildren().addAll(jogadorText1, jogadorText2, jogadorText3);
        jogadorTextBox.setPadding(new Insets(100));
        jogadorTextBox.setMinWidth(Constantes.LARG_SIDEBAR); jogadorTextBox.setMaxWidth(Constantes.LARG_SIDEBAR);
        setRight(jogadorTextBox);

        setBackground(new Background(new BackgroundFill(Color.web(Constantes.BACKGROUND_COLOR_HEX), CornerRadii.EMPTY, Insets.EMPTY)));

        menuBarJogo = new MenuBarJogo(observable);
        setTop(menuBarJogo);

        FooterBox buttonBox = new FooterBox(30);

        HBox voltarAtrasBox = new HBox(10);
        voltarAtrasButton = new NormalMenuButton("Voltar Atrás");
        voltarAtrasSlider = new Slider();
        voltarAtrasSlider.setMin(1);
        voltarAtrasSlider.setBlockIncrement(1);
        voltarAtrasSlider.setMajorTickUnit(1);
        voltarAtrasSlider.setMinorTickCount(0);
        voltarAtrasSlider.setShowTickLabels(true);
        voltarAtrasSlider.setShowTickMarks(true);
        voltarAtrasSlider.setSnapToTicks(true);
        voltarAtrasSlider.setValue(1);
        voltarAtrasSlider.setId("voltar-slider");
        voltarAtrasBox.getChildren().addAll(voltarAtrasSlider, voltarAtrasButton);
        voltarAtrasBox.setAlignment(Pos.CENTER);

        aceitarMinijogoButton = new NormalMenuButton("Minijogo");
        jogarFichaEspecialButton = new NormalMenuButton(FICHA_ESPECIAL_BUTTON_TEXT);
        desistirButton = new NormalMenuButton("Desistir");

        buttonBox.getChildren().addAll(voltarAtrasBox, aceitarMinijogoButton, jogarFichaEspecialButton, desistirButton);

        setBottom(buttonBox);
    }

    private void setLockBotoes(boolean doLock) {
        voltarAtrasButton.setDisable(doLock || !observable.podeVoltarAtras());
        aceitarMinijogoButton.setDisable(doLock || !observable.temMinijogoDisponivel());
        jogarFichaEspecialButton.setDisable(doLock || observable.getNumFichasEspeciaisJogadorAtual() <= 0);
        desistirButton.setDisable(doLock);
    }

    private void registarListeners() {
        voltarAtrasButton.setOnAction(e -> {
            double numVezes = voltarAtrasSlider.getValue();
            if (numVezes == 0) return;
            observable.undoJogada((int) numVezes);
        });

        aceitarMinijogoButton.setOnAction(e -> observable.aceitarMinijogo());

        jogarFichaEspecialButton.setOnAction(e -> {
            boolean oldColocarFichaEspecial = tabuleiroPane.isJogarFichaEspecial();
            tabuleiroPane.setJogarFichaEspecial(!oldColocarFichaEspecial);

            if (!oldColocarFichaEspecial) {
                jogarFichaEspecialButton.setText(FICHA_REGULAR_BUTTON_TEXT);
                return;
            }

            jogarFichaEspecialButton.setText(FICHA_ESPECIAL_BUTTON_TEXT);

        });

        desistirButton.setOnAction(e -> observable.desistir());
    }

    private void resetJogarFichaEspecial() {
        jogarFichaEspecialButton.setText(FICHA_ESPECIAL_BUTTON_TEXT);
        tabuleiroPane.setJogarFichaEspecial(false);
    }

    private void fazJogadaAutomatica() {
        scheduler = Executors.newScheduledThreadPool(1);
        scheduler.schedule(() -> {
            Platform.runLater(() -> {
                if (!isVisible()) return;
                MusicPlayer.playMusic(Constantes.SOM_FICHA_DROP);
                int jogadaAutomatica = observable.getJogadaAutomatica();
                observable.jogarFicha(jogadaAutomatica);
            });
            scheduler.shutdownNow();
        }, Constantes.MILISEGUNDOS_SLEEP_JOGADA_COMPUTADOR, TimeUnit.MILLISECONDS);
    }

    private void registarObservadores() {
        observable.addPropertyChangeListener(Propriedades.ATUALIZAR_LISTA_JOGADORES, evt -> atualizarListaJogadores());
        observable.addPropertyChangeListener(Propriedades.ATUALIZAR_JOGADOR_ATUAL, evt -> atualizarJogadorAtual());
        observable.addPropertyChangeListener(Propriedades.FIM_MINIJOGO, evt -> processarFimMinijogo());
        observable.addPropertyChangeListener(Propriedades.ATUALIZAR_ESTADO, evt -> atualizarVisibilidade());
    }

    private void atualizarListaJogadores() { listaJogadores.setText(observable.getConfigJogadores()); }

    private void atualizarJogadorAtual() {
        voltarAtrasSlider.setMax(observable.getNumCreditosJogaveis());
        resetJogarFichaEspecial();

        jogadorText2.setText(observable.getNomeJogadorAtual());
        jogadorText3.setText("Creditos: " + observable.getNumCreditos());
        jogadorText2.setFill(Color.web(switch (observable.getFichaAtual()) {
            case FICHA_VERMELHA -> Constantes.COR_VERMELHA_HEX;
            case FICHA_AMARELA -> Constantes.COR_AMARELA_HEX;
            default -> Constantes.BACKGROUND_COLOR_HEX;
        }));

        if (!observable.isComputadorAJogar()) {
            setLockBotoes(false);
            return;
        }

        setLockBotoes(true);

        if (observable.jogoComecou() && !observable.jogoAcabou() && isVisible()) fazJogadaAutomatica();
    }

    private void processarFimMinijogo() {
        if (observable.isAcabadoMinijogo()) {
            if (observable.ganhouUltimoMinijogo()) {
                MusicPlayer.playMusic(Constantes.SOM_GANHA_MINIJOGO);
                return;
            }

            MusicPlayer.playMusic(Constantes.SOM_PERDE_MINIJOGO);
        }
    }

    private void atualizarVisibilidade() { this.setVisible(observable.getSituacao() == Situacao.PedeDecisaoJogada); }
}
