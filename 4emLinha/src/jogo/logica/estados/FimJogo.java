package jogo.logica.estados;

import jogo.logica.dados.QuatroEmLinha;

public class FimJogo extends EstadoAdapter {

    protected FimJogo(QuatroEmLinha quatroEmLinha) { super(quatroEmLinha); }

    @Override
    public Estado avancar() {
        quatroEmLinha.limparTudo();
        return new PedeDecisaoInicio(quatroEmLinha);
    }

    @Override
    public Situacao getSituacao() { return Situacao.FimJogo; }
}
