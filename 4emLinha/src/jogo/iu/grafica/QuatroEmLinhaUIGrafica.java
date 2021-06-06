package jogo.iu.grafica;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jogo.iu.grafica.resources.ImageLoader;
import jogo.iu.grafica.stage.PaneOrganizer;
import jogo.logica.QuatroEmLinhaMaquinaEstados;
import jogo.logica.QuatroEmLinhaObservable;

public class QuatroEmLinhaUIGrafica extends Application {
    @Override
    public void start(Stage stage) {

        QuatroEmLinhaMaquinaEstados maquinaEstados = new QuatroEmLinhaMaquinaEstados();
        QuatroEmLinhaObservable observable = new QuatroEmLinhaObservable(maquinaEstados);

        PaneOrganizer root = new PaneOrganizer(observable);

        Scene scene = new Scene(root, 1066.666, 600); // 16:9
        stage.setScene(scene);
        stage.setTitle("Quatro em Linha");
        stage.getIcons().add(ImageLoader.getImage("icon.png"));
        stage.setResizable(false);
        stage.setOnCloseRequest(windowEvent -> Platform.exit());
        stage.show();
    }
}
