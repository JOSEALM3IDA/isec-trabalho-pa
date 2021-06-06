package jogo.iu.grafica.estados;

import javafx.scene.layout.HBox;
import jogo.logica.Propriedades;
import jogo.logica.QuatroEmLinhaObservable;
import jogo.logica.estados.Situacao;

public class JogaMinijogoPane extends HBox {
    private final QuatroEmLinhaObservable observable;


    public JogaMinijogoPane(QuatroEmLinhaObservable observable) {
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
    private void atualiza() { this.setVisible(observable.getSituacao() == Situacao.JogaMinijogo); }
}
