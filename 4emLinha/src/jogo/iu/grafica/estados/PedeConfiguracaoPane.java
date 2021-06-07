package jogo.iu.grafica.estados;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import jogo.iu.grafica.resources.FontManager;
import jogo.iu.grafica.stage.MenuBackground;
import jogo.iu.grafica.stage.NormalMenuButton;
import jogo.logica.Propriedades;
import jogo.logica.QuatroEmLinhaObservable;
import jogo.logica.dados.jogadores.TipoJogador;
import jogo.logica.estados.Situacao;
import jogo.utils.Constantes;

public class PedeConfiguracaoPane extends BorderPane {
    private final QuatroEmLinhaObservable observable;

    private Text configText;

    private Text infoText;

    private Text jogadorAtualText;
    private Text nomeText;
    private TextField nomeField;
    private ComboBox<TipoJogador> tipoJogadorCombobox;
    private Text erroText;
    private NormalMenuButton adicionarButton;

    public PedeConfiguracaoPane(QuatroEmLinhaObservable observable) {
        this.observable = observable;
        criarLayout();
        registarListeners();
        registarObservador();
        atualiza();
    }

    private VBox getBoxConfig() {
        VBox configBox = new VBox(100);
        configBox.setBackground(new Background(new BackgroundFill(
                Color.web(Constantes.COR_AZUL_HEX),
                CornerRadii.EMPTY, Insets.EMPTY)));

        configText = new Text("Configuração");
        configText.setFont(FontManager.loadFont(Constantes.FONT_MENU, 24));
        configText.setFill(Color.WHITE);
        configText.setStroke(Color.BLACK);
        configText.setStrokeWidth(1);
        configBox.setAlignment(Pos.CENTER);

        infoText = new Text();
        infoText.setFill(Color.WHITE);
        infoText.setStyle("-fx-font-size: 20");
        infoText.setTextAlignment(TextAlignment.CENTER);

        VBox buttonBox = new VBox(15);

        jogadorAtualText = new Text();
        jogadorAtualText.setFill(Color.WHITE);
        jogadorAtualText.setStyle("-fx-font-weight: bold");
        jogadorAtualText.setStyle("-fx-font-size: 20");

        VBox nomeBox = new VBox(5);
        nomeText = new Text("Nome");
        nomeText.setFill(Color.WHITE);

        HBox nomeTipoBox = new HBox(10);

        nomeField = new TextField();

        tipoJogadorCombobox = new ComboBox<>();
        tipoJogadorCombobox.getItems().addAll(TipoJogador.values());
        tipoJogadorCombobox.setMinWidth(130);

        nomeTipoBox.getChildren().addAll(nomeField, tipoJogadorCombobox);
        nomeTipoBox.setAlignment(Pos.CENTER);

        nomeBox.getChildren().addAll(nomeText, nomeTipoBox);
        nomeBox.setPadding(new Insets(50));

        erroText = new Text();
        erroText.setFill(Color.RED);
        erroText.setStroke(Color.RED);
        erroText.setStrokeWidth(1);
        erroText.setStyle("-fx-font-size: 12");

        adicionarButton = new NormalMenuButton("Adicionar Jogador");

        buttonBox.getChildren().addAll(jogadorAtualText, nomeBox, erroText, adicionarButton);
        buttonBox.setAlignment(Pos.CENTER);

        configBox.getChildren().addAll(configText, infoText, buttonBox);

        configBox.setMaxWidth(Constantes.LARG_SIDEBAR);
        configBox.setMinWidth(Constantes.LARG_SIDEBAR);
        return configBox;
    }

    private void criarLayout() {
        setLeft(new MenuBackground());
        setRight(getBoxConfig());
    }

    private void registarListeners() {
        adicionarButton.setOnMouseClicked(e -> {
            adicionarButton.click();

            String nomeJogador = nomeField.getText();
            TipoJogador tipoJogador = tipoJogadorCombobox.getSelectionModel().getSelectedItem();

            if (nomeJogador.isBlank()) {
                erroText.setText("O nome do jogador não pode estar vazio!");
                return;
            }

            if (observable.existeJogador(nomeJogador)) {
                erroText.setText("Esse nome já está a ser utilizado!");
                return;
            }

            observable.adicionarJogador(tipoJogador, nomeJogador);
            erroText.setText("");
        });
    }

    private void registarObservador() { observable.addPropertyChangeListener(Propriedades.MUDA_ESTADO, evt -> atualiza()); }
    private void atualiza() {
        infoText.setText(observable.getConfigJogadores());
        nomeField.setText("");
        tipoJogadorCombobox.getSelectionModel().select(TipoJogador.HUMANO);
        jogadorAtualText.setText("Jogador " + (observable.getNumJogadores() + 1));

        this.setVisible(observable.getSituacao() == Situacao.PedeConfiguracao);
    }
}
