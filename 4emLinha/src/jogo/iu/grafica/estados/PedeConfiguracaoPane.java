package jogo.iu.grafica.estados;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import jogo.iu.grafica.resources.FontManager;
import jogo.iu.grafica.stage.MainMenuButton;
import jogo.iu.grafica.stage.MenuBackground;
import jogo.iu.grafica.stage.NormalMenuButton;
import jogo.logica.Propriedades;
import jogo.logica.QuatroEmLinhaObservable;
import jogo.logica.estados.Situacao;
import jogo.utils.Constantes;

public class PedeConfiguracaoPane extends HBox {
    private final QuatroEmLinhaObservable observable;

    private Button adicionarButton;

    public PedeConfiguracaoPane(QuatroEmLinhaObservable observable) {
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

        VBox buttonBox = new VBox(15);

        adicionarButton = new NormalMenuButton("Adicionar Jogador");

        buttonBox.getChildren().addAll(adicionarButton);

        verticalBox.getChildren().addAll(buttonBox);

        verticalBox.setAlignment(Pos.BOTTOM_CENTER);
        verticalBox.setPadding(new Insets(50));

        setAlignment(Pos.CENTER_LEFT);
        getChildren().addAll(new MenuBackground(), verticalBox);
    }

    void registarListeners() {
    }

    private void registarObservador() { observable.addPropertyChangeListener(Propriedades.MUDA_ESTADO, evt -> atualiza()); }
    private void atualiza() { this.setVisible(observable.getSituacao() == Situacao.PedeConfiguracao); }
}
