package jogo.iu.grafica.estados;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import jogo.iu.grafica.stage.FooterBox;
import jogo.iu.grafica.stage.MenuBarJogo;
import jogo.iu.grafica.stage.NormalMenuButton;
import jogo.iu.grafica.stage.TabuleiroPane;
import jogo.logica.Propriedades;
import jogo.logica.QuatroEmLinhaObservable;
import jogo.logica.estados.Situacao;
import jogo.utils.Constantes;

public class AssisteJogadaPane extends BorderPane {
    private final QuatroEmLinhaObservable observable;

    private MenuBarJogo menuBarJogo;

    private TabuleiroPane tabuleiroPane;

    private Text listaJogadores;
    private Text jogadorText1;
    private Text jogadorText2;

    private Text infoJogadaText;
    private NormalMenuButton avancarButton;

    public AssisteJogadaPane(QuatroEmLinhaObservable observable) {
        this.observable = observable;
        criarLayout();
        registarListeners();
        registarObservadores();
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
        jogadorText1 = new Text("Jogador Atual:");
        jogadorText1.setStyle("-fx-font-size: 20");
        jogadorText1.setTextAlignment(TextAlignment.CENTER);
        jogadorText1.setFill(Color.WHITE);
        jogadorText2 = new Text();
        jogadorText2.setStyle("-fx-font-size: 24; -fx-font-weight: bold");
        jogadorText2.setStroke(Color.BLACK);
        jogadorText2.setStrokeWidth(1);
        jogadorTextBox.setAlignment(Pos.CENTER);
        jogadorTextBox.getChildren().addAll(jogadorText1, jogadorText2);
        jogadorTextBox.setPadding(new Insets(100));
        jogadorTextBox.setMinWidth(Constantes.LARG_SIDEBAR); jogadorTextBox.setMaxWidth(Constantes.LARG_SIDEBAR);
        setRight(jogadorTextBox);

        menuBarJogo = new MenuBarJogo(observable);
        setTop(menuBarJogo);

        FooterBox footerBox = new FooterBox();
        BorderPane footerPane = new BorderPane();
        footerPane.setMinWidth(footerBox.getMinWidth());
        footerPane.setPadding(new Insets(28, 50, 0, 50));

        avancarButton = new NormalMenuButton("Avançar");
        footerPane.setRight(avancarButton);

        infoJogadaText = new Text();
        infoJogadaText.setStyle("-fx-font-size: 22; -fx-fill: white");
        footerPane.setLeft(infoJogadaText);

        footerBox.getChildren().addAll(footerPane);

        setBottom(footerBox);
    }

    void registarListeners() {
        avancarButton.setOnAction(e -> {
            infoJogadaText.setText(observable.getDescricaoComandoAtual());
            observable.avancar();
        });

        setOnKeyReleased(e -> {
            if (e.getCode() != KeyCode.ENTER) return;
            avancarButton.fire();
        });
    }

    private void registarObservadores() {
        observable.addPropertyChangeListener(Propriedades.ATUALIZAR_LISTA_JOGADORES, evt -> atualizarListaJogadores());
        observable.addPropertyChangeListener(Propriedades.ATUALIZAR_JOGADOR_ATUAL, evt -> atualizarJogadorAtual());
        observable.addPropertyChangeListener(Propriedades.ATUALIZAR_ESTADO, evt -> atualizarVisibilidade());
    }

    private void atualizarListaJogadores() { listaJogadores.setText(observable.getConfigJogadores()); }

    private void atualizarJogadorAtual() {
        jogadorText2.setText(observable.getNomeJogadorAtual());
        jogadorText2.setFill(Color.web(switch (observable.getFichaAtual()) {
            case FICHA_VERMELHA -> Constantes.COR_VERMELHA_HEX;
            case FICHA_AMARELA -> Constantes.COR_AMARELA_HEX;
            default -> Constantes.BACKGROUND_COLOR_HEX;
        }));
    }

    private void atualizarVisibilidade() { this.setVisible(observable.getSituacao() == Situacao.AssisteJogada); }
}
