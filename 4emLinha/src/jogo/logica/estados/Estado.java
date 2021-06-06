package jogo.logica.estados;

import jogo.logica.dados.QuatroEmLinhaGestor;
import jogo.logica.dados.jogadores.TipoJogador;

public interface Estado {

    // PedeDecisaoInicio
    Estado iniciarJogo();       // -> PedeConfiguracao
    Estado continuarJogo(QuatroEmLinhaGestor quatroEmLinhaGestor);      // -> PedeDecisaoJogadaPane
    Estado verReplay(QuatroEmLinhaGestor quatroEmLinhaGestor);         // -> AssisteJogada

    // PedeConfiguracao
    Estado adicionarJogador(TipoJogador tipoJogador, String nomeJogador);

    // PedeDecisaoJogadaPane
    Estado jogarFicha(int col);      // -> PedeDecisaoJogadaPane / FimJogo
    Estado undoJogada(int numVezes);        // -> PedeDecisaoJogadaPane
    Estado desistir();          // -> FimJogo
    Estado aceitarMinijogo();   // -> JogoMinijogo
    Estado jogarFichaEspecial(int col); // -> PedeDecisaoJogadaPane

    // JogoMinijogo
    Estado jogarMinijogo(String resposta); // -> PedeDecisaoJogadaPane

    // AssisteJogada ; PedeConfiguracao ; FimJogada ; FimJogo
    Estado avancar();           // -> Varios Estados

    Situacao getSituacao();
}
