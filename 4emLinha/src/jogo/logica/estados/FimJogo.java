package jogo.logica.estados;

import jogo.logica.QuatroEmLinhaGestor;

public class FimJogo extends EstadoAdapter {

    protected FimJogo(QuatroEmLinhaGestor quatroEmLinhaGestor) { super(quatroEmLinhaGestor); }

    @Override
    public Estado avancar() {
        quatroEmLinhaGestor.limparTudo();
        return new PedeDecisaoInicio(quatroEmLinhaGestor);
    }

    @Override
    public Situacao getSituacao() { return Situacao.FimJogo; }
}
