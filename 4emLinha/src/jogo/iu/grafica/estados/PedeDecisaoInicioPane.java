package jogo.iu.grafica.estados;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import jogo.iu.grafica.resources.FontManager;
import jogo.iu.grafica.resources.ImageLoader;
import jogo.iu.grafica.stage.MainMenuButton;
import jogo.iu.grafica.stage.MenuBackground;
import jogo.logica.Propriedades;
import jogo.logica.QuatroEmLinhaObservable;
import jogo.logica.estados.Situacao;
import jogo.utils.Constantes;

import java.io.File;

public class PedeDecisaoInicioPane extends HBox {
    private final QuatroEmLinhaObservable observable;

    private Text tituloText;

    private Button iniciarButton;
    private Button continuarButton;
    private Button replayButton;
    private Button sairButton;

    public PedeDecisaoInicioPane(QuatroEmLinhaObservable observable) {
        this.observable = observable;
        criarLayout();
        registarListeners();
        registarObservador();
        atualiza();
    }

    void criarLayout() {

        VBox verticalBox = new VBox(100);

        verticalBox.setBackground(new Background(new BackgroundFill(
                Color.web(Constantes.COR_AZUL_HEX),
                CornerRadii.EMPTY, Insets.EMPTY)));

        tituloText = new Text("QUATRO\nEM\nLINHA");
        tituloText.setTextAlignment(TextAlignment.CENTER);
        tituloText.setFill(Color.WHITE);
        tituloText.setStroke(Color.BLACK);
        tituloText.setStrokeWidth(1);
        tituloText.setFont(FontManager.loadFont(Constantes.FONT_MENU, 30));

        VBox buttonBox = new VBox(15);

        iniciarButton = new MainMenuButton("Iniciar Jogo");
        continuarButton = new MainMenuButton("Continuar Jogo");
        replayButton = new MainMenuButton("Ver Replay");
        sairButton = new MainMenuButton("Sair");

        buttonBox.getChildren().addAll(iniciarButton, continuarButton, replayButton, sairButton);

        verticalBox.getChildren().addAll(tituloText, buttonBox);

        verticalBox.setAlignment(Pos.CENTER);
        verticalBox.setPadding(new Insets(50));

        setAlignment(Pos.CENTER_LEFT);
        getChildren().addAll(new MenuBackground(), verticalBox);
    }

    void registarListeners() {
        iniciarButton.setOnMouseClicked(e -> {
            ((MainMenuButton) iniciarButton).click();
            observable.iniciarJogo();
        });

        continuarButton.setOnMouseClicked(e -> {
            ((MainMenuButton) continuarButton).click();

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Escolher Save");
            fileChooser.setInitialDirectory(new File(Constantes.SAVE_PATH));
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Todos", "*.*"));
            File hFile = fileChooser.showOpenDialog(PedeDecisaoInicioPane.this.getScene().getWindow());
            if (hFile != null) { observable.verReplay(hFile.getAbsolutePath()); }
        });

        replayButton.setOnMouseClicked(e -> {
            ((MainMenuButton) replayButton).click();

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Escolher Replay");
            fileChooser.setInitialDirectory(new File(Constantes.REPLAY_PATH));
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Todos", "*.*"));
            File hFile = fileChooser.showOpenDialog(PedeDecisaoInicioPane.this.getScene().getWindow());
            if (hFile != null) { observable.verReplay(hFile.getAbsolutePath()); }
        });

        sairButton.setOnMouseClicked(e -> {
            ((MainMenuButton) sairButton).click();
            Platform.exit();
        });

    }

    private void registarObservador() { observable.addPropertyChangeListener(Propriedades.MUDA_ESTADO, evt -> atualiza()); }
    private void atualiza() { this.setVisible(observable.getSituacao() == Situacao.PedeDecisaoInicio); }
}
