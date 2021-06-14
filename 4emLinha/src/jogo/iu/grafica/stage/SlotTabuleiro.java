package jogo.iu.grafica.stage;

import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
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
        this.tipoFicha = tipoFicha;
        imageView = new ImageView();
        imageView.setFitWidth(tamanho);
        imageView.setFitHeight(tamanho);
        imageView.setImage(ImageLoader.getImage(getImgFicha()));
        this.getChildren().add(imageView);
    }

    public TipoFicha getTipoFicha() { return tipoFicha; }

    public void setMouseInside(boolean val, TipoFicha tipoFichaHover) {
        if (this.tipoFicha != TipoFicha.NONE) return;

        if (!val) {
            imageView.setImage(ImageLoader.getImage(getImgFicha()));
            return;
        }

        //this.setBackground(new Background(new BackgroundFill(
        //        color,
        //        CornerRadii.EMPTY, Insets.EMPTY)));

        imageView.setImage(ImageLoader.getImage(getImgFichaHover(tipoFichaHover)));

        //this.setBackground(new Background(new BackgroundImage(,
        //        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
    }

    private String getImgFicha() {
        return switch (this.tipoFicha) {
            case FICHA_VERMELHA -> Constantes.SLOT_VERMELHO_IMG;
            case FICHA_AMARELA -> Constantes.SLOT_AMARELO_IMG;
            case NONE -> Constantes.SLOT_VAZIO_IMG;
        };
    }

    private String getImgFichaHover(TipoFicha tipoFichaHover) {
        return switch (tipoFichaHover) {
            case FICHA_VERMELHA -> Constantes.SLOT_VERMELHO_HOVER_IMG;
            case FICHA_AMARELA -> Constantes.SLOT_AMARELO_HOVER_IMG;
            case NONE -> Constantes.SLOT_VAZIO_IMG;
        };
    }
}
