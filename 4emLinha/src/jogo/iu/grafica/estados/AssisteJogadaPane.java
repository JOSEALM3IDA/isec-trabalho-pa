package jogo.iu.grafica.estados;

import javafx.scene.layout.BorderPane;
import jogo.logica.Propriedades;
import jogo.logica.QuatroEmLinhaObservable;
import jogo.logica.estados.Situacao;

public class AssisteJogadaPane extends BorderPane {
    private final QuatroEmLinhaObservable observable;

    public AssisteJogadaPane(QuatroEmLinhaObservable observable) {
        this.observable = observable;
        criarLayout();
        registarListeners();
        registarObservador();
        atualiza();
    }

    void criarLayout() {
    }

    void registarListeners() {
    }

    private void registarObservador() { observable.addPropertyChangeListener(Propriedades.MUDA_ESTADO, evt -> atualiza()); }
    private void atualiza() { this.setVisible(observable.getSituacao() == Situacao.AssisteJogada); }
}
