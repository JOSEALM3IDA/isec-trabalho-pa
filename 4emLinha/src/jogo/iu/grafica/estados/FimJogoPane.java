package jogo.iu.grafica.estados;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

public class FimJogoPane extends BorderPane {
    private final QuatroEmLinhaObservable observable;

    private MenuBarJogo menuBarJogo;

    private TabuleiroPane tabuleiroPane;

    private Text listaJogadores;
    private Text vencedorText1;
    private Text vencedorText2;

    private NormalMenuButton avancarButton;

    public FimJogoPane(QuatroEmLinhaObservable observable) {
        this.observable = observable;
        criarLayout();
        registarListeners();
        registarObservador();
        atualizarVisibilidade();
    }

    private void criarLayout() {
        setBackground(new Background(new BackgroundFill(Color.web(Constantes.BACKGROUND_COLOR_HEX), CornerRadii.EMPTY, Insets.EMPTY)));

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
        vencedorText1 = new Text("Vencedor:");
        vencedorText1.setStyle("-fx-font-size: 20");
        vencedorText1.setTextAlignment(TextAlignment.CENTER);
        vencedorText1.setFill(Color.WHITE);
        vencedorText2 = new Text();
        vencedorText2.setStyle("-fx-font-size: 24; -fx-font-weight: bold");
        vencedorText2.setStroke(Color.BLACK);
        vencedorText2.setStrokeWidth(1);
        jogadorTextBox.setAlignment(Pos.CENTER);
        jogadorTextBox.getChildren().addAll(vencedorText1, vencedorText2);
        jogadorTextBox.setPadding(new Insets(100));
        jogadorTextBox.setMinWidth(Constantes.LARG_SIDEBAR); jogadorTextBox.setMaxWidth(Constantes.LARG_SIDEBAR);
        setRight(jogadorTextBox);

        menuBarJogo = new MenuBarJogo(observable);
        setTop(menuBarJogo);

        FooterBox buttonBox = new FooterBox(30);

        avancarButton = new NormalMenuButton("Menu Inicial");
        buttonBox.getChildren().addAll(avancarButton);

        setBottom(buttonBox);
    }

    void registarListeners() { avancarButton.setOnAction(e -> observable.voltar()); }

    private void registarObservador() {
        observable.addPropertyChangeListener(Propriedades.ATUALIZAR_LISTA_JOGADORES, evt -> atualizarListaJogadores());
        observable.addPropertyChangeListener(Propriedades.ATUALIZAR_VENCEDOR, evt -> atualizarVencedor());
        observable.addPropertyChangeListener(Propriedades.ATUALIZAR_ESTADO, evt -> atualizarVisibilidade());
    }

    private void atualizarListaJogadores() { listaJogadores.setText(observable.getConfigJogadores()); }

    private void atualizarVencedor() {
        if (!observable.jogoAcabou()) return;

        MusicPlayer.playMusic(Constantes.SOM_FIM_JOGO);

        if (observable.isEmpatado()) {
            vencedorText2.setText("Empate!");
            vencedorText2.setFill(Color.web(Constantes.COR_AZUL_HEX));
            return;
        }

        vencedorText2.setText(observable.getNomeVencedor());
        vencedorText2.setFill(Color.web(switch (observable.getFichaVencedor()) {
            case FICHA_VERMELHA -> Constantes.COR_VERMELHA_HEX;
            case FICHA_AMARELA -> Constantes.COR_AMARELA_HEX;
            default -> Constantes.BACKGROUND_COLOR_HEX;
        }));
    }

    private void atualizarVisibilidade() { this.setVisible(observable.getSituacao() == Situacao.FimJogo); }
}
