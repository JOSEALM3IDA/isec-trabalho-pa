package jogo.iu.grafica.stage;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import jogo.iu.grafica.resources.MusicPlayer;
import jogo.utils.Constantes;

public class MainMenuButton extends Button {

    public MainMenuButton(String s) {
        super(s);

        this.setId("menu-button");
        this.setPrefSize(Constantes.LARG_MENU_BTN, Constantes.ALT_MENU_BTN);
        setAlignment(Pos.CENTER);

        this.setOnMouseClicked(e -> click());
        this.setOnMouseEntered(e -> MusicPlayer.playMusic("btn-hover.wav"));
    }

    public void click() {
        MusicPlayer.playMusic("btn-click.mp3");
    }
}
