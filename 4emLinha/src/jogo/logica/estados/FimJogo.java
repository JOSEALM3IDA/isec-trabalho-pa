package jogo.logica.estados;

import jogo.logica.QuatroEmLinhaGestor;
import jogo.utils.Constantes;
import jogo.utils.Utils;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FimJogo extends EstadoAdapter {



    protected FimJogo(QuatroEmLinhaGestor quatroEmLinhaGestor) {
        super(quatroEmLinhaGestor);
        gravarReplay(quatroEmLinhaGestor);
    }

    private void gravarReplay(QuatroEmLinhaGestor quatroEmLinhaGestor) {
        String[] replaysExistentes = Utils.getFicheirosNoDiretorio(Constantes.REPLAY_PATH);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd-HH.mm.ss");
        LocalDateTime ldt = LocalDateTime.now();
        String nomeNovoFicheiro = Constantes.REPLAY_PATH + dtf.format(ldt) + ".replay";


        if (replaysExistentes.length < Constantes.NUM_MAX_REPLAYS) {
            Utils.gravarObjeto(nomeNovoFicheiro, quatroEmLinhaGestor);
            return;
        }

        String oldest = null;
        long oldestData = 0;
        for (var replay : replaysExistentes) {

            File replayFicheiro = new File(replay);

            if (oldest == null) {
                oldest = replay;
                oldestData = replayFicheiro.lastModified();
                continue;
            }

            if (replayFicheiro.lastModified() < oldestData) {
                oldest = replay;
                oldestData = replayFicheiro.lastModified();
            }
        }

        File oldestFile = new File(Constantes.REPLAY_PATH + oldest);
        oldestFile.delete();

        Utils.gravarObjeto(nomeNovoFicheiro, quatroEmLinhaGestor);
    }

    @Override
    public Estado avancar() {
        quatroEmLinhaGestor.limparTudo();
        return new PedeDecisaoInicio(quatroEmLinhaGestor);
    }

    @Override
    public Situacao getSituacao() { return Situacao.FimJogo; }
}
