package jogo.iu.grafica.stage;

import javafx.scene.control.Button;
import jogo.iu.grafica.resources.MusicPlayer;
import jogo.utils.Constantes;

public class NormalMenuButton extends Button {

    public NormalMenuButton(String s) {
        super(s);

        this.setId("menu-button");
        this.setPrefSize(Constantes.LARG_NORMAL_BTN, Constantes.ALT_NORMAL_BTN);

        this.setOnMouseClicked(e -> click());
        this.setOnMouseEntered(e -> MusicPlayer.playMusic("btn-hover.wav"));
    }

    public void click() {
        MusicPlayer.playMusic("btn-click.mp3");
    }
}
