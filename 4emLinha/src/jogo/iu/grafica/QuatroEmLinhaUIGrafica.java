package jogo.iu.grafica;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jogo.iu.grafica.stage.PaneOrganizer;
import jogo.logica.QuatroEmLinhaMaquinaEstados;

public class QuatroEmLinhaUIGrafica extends Application {

    QuatroEmLinhaMaquinaEstados maquinaEstados = new QuatroEmLinhaMaquinaEstados();

    @Override
    public void start(Stage stage) {
        PaneOrganizer root = new PaneOrganizer();

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Quatro em Linha");
        stage.setMinWidth(300); stage.setMinHeight(300);
        stage.show();
    }



}
