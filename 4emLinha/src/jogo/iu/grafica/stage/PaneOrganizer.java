package jogo.iu.grafica.stage;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class PaneOrganizer extends BorderPane {
    Pane centro;

    public PaneOrganizer() {
        criarLayout();
        registarListeners();;
    }

    void criarLayout() {
        centro = new Pane();
        this.setCenter(centro);
    }

    void registarListeners() {
    }
}
