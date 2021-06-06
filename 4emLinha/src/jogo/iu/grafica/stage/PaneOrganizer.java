package jogo.iu.grafica.stage;

import javafx.scene.layout.BorderPane;
import jogo.iu.grafica.resources.CSSManager;
import jogo.logica.QuatroEmLinhaObservable;

public class PaneOrganizer extends BorderPane {
    private final QuatroEmLinhaObservable observable;

    private PrincipalPane principalPane;

    public PaneOrganizer(QuatroEmLinhaObservable observable) {
        this.observable = observable;
        CSSManager.setCss(this, "styles.css");
        criarLayout();
        registarListeners();
    }

    void criarLayout() {
        principalPane = new PrincipalPane(observable);
        setCenter(principalPane);
    }

    void registarListeners() {
    }
}
