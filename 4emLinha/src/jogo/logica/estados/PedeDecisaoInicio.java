package jogo.logica.estados;

import jogo.logica.QuatroEmLinhaGestor;
import jogo.utils.Constantes;
import jogo.utils.Utils;

import java.io.IOException;

public class PedeDecisaoInicio extends EstadoAdapter {

    public PedeDecisaoInicio(QuatroEmLinhaGestor quatroEmLinhaGestor) { super(quatroEmLinhaGestor); }

    @Override
    public Estado iniciarJogo() { return new PedeConfiguracao(quatroEmLinhaGestor); }

    @Override
    public Estado verReplay(String nomeFicheiro) {
        try {
            quatroEmLinhaGestor = Utils.lerObjeto(Constantes.REPLAY_PATH + nomeFicheiro, QuatroEmLinhaGestor.class);
        } catch (IOException | ClassNotFoundException ignored) { ignored.printStackTrace(); }

        if (quatroEmLinhaGestor == null) return null;

        quatroEmLinhaGestor.resetTabuleiro();
        quatroEmLinhaGestor.resetEstadoJogadores();

        return new AssisteJogada(quatroEmLinhaGestor);
    }

    @Override
    public Situacao getSituacao() { return Situacao.PedeDecisaoInicio; }

}
