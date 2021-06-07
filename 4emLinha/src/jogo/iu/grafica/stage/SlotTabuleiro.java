package jogo.iu.grafica.stage;

import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import jogo.iu.grafica.resources.ImageLoader;
import jogo.logica.dados.tabuleiro.TipoFicha;
import jogo.utils.Constantes;

public class SlotTabuleiro extends HBox {

    private ImageView imageView;
    private final double tamanho;
    private TipoFicha tipoFicha;

    public SlotTabuleiro(TipoFicha tipoFicha, double tamanho) {
        this.tamanho = tamanho;
        this.tipoFicha = tipoFicha;

        setAs(tipoFicha);
    }

    public void setAs(TipoFicha tipoFicha) {
        String nomeFichImagem = switch (tipoFicha) {
            case FICHA_VERMELHA -> Constantes.SLOT_VERMELHO_IMG;
            case FICHA_AMARELA -> Constantes.SLOT_AMARELO_IMG;
            case NONE -> Constantes.SLOT_VAZIO_IMG;
        };

        imageView = new ImageView();
        imageView.setFitWidth(tamanho);
        imageView.setFitHeight(tamanho);
        imageView.setImage(ImageLoader.getImage(nomeFichImagem));
        this.getChildren().add(imageView);
    }

    public TipoFicha getTipoFicha() { return tipoFicha; }

    public void setMouseInside(boolean val) { setMouseInside(val, Color.WHITE); }

    public void setMouseInside(boolean val, Color color) {
        if (tipoFicha != TipoFicha.NONE) return;

        if (!val) {
            this.setBackground(Background.EMPTY);
            return;
        }

        this.setBackground(new Background(new BackgroundFill(
                color,
                CornerRadii.EMPTY, Insets.EMPTY)));
    }
}
