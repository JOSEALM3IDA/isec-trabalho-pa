package jogo.iu.grafica.stage;

import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import jogo.iu.grafica.resources.MusicPlayer;
import jogo.utils.Constantes;

public class NormalMenuButton extends Button {

    public NormalMenuButton(String s) {
        super(s);

        this.setId("menu-button");
        this.setPrefSize(Constantes.LARG_NORMAL_BTN, Constantes.ALT_NORMAL_BTN);
        registerListeners();
    }

    public void registerListeners() {
        this.setOnMouseClicked(e -> {
            if (e.getButton() != MouseButton.PRIMARY) return;
            MusicPlayer.playMusic(Constantes.SOM_CLICK_BTN);
        });

        this.setOnMouseEntered(e -> MusicPlayer.playMusic(Constantes.SOM_HOVER_BTN));
    }
}
