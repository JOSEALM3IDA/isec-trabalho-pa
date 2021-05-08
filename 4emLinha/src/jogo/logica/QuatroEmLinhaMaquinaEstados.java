package jogo.logica;

import jogo.logica.dados.QuatroEmLinha;
import jogo.logica.estados.Estado;
import jogo.logica.estados.PedeDecisaoInicio;

public class QuatroEmLinhaMaquinaEstados {
    Estado estadoAtual;
    QuatroEmLinha quatroEmLinha;

    public QuatroEmLinhaMaquinaEstados() {
        this.quatroEmLinha = new QuatroEmLinha();
        this.estadoAtual = new PedeDecisaoInicio(quatroEmLinha);
    }

}
