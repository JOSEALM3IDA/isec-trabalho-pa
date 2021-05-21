package jogo.logica.estados;

import jogo.logica.dados.jogadores.TipoJogador;

public interface Estado {

    // PedeDecisaoInicio
    Estado iniciarJogo();       // -> PedeConfiguracao
    Estado verReplay();         // -> AssisteJogada

    // PedeConfiguracao
    Estado adicionarJogador(TipoJogador tipoJogador, String nomeJogador);

    // PedeDecisaoJogada
    Estado jogarFicha(int col);      // -> PedeDecisaoJogada / FimJogo
    Estado undoJogada();        // -> PedeDecisaoJogada
    Estado desistir();          // -> FimJogo
    Estado aceitarMinijogo();   // -> JogoMinijogo

    // JogoMinijogo
    Estado jogarMinijogo(String resposta); // -> PedeDecisaoJogada
    Estado ganharMinijogo();    // -> PedeDecisaoJogada
    Estado perderMinijogo();    // -> FimJogada

    // FimJogada
    Estado ganharJogo();        // -> FimJogo

    // AssisteJogada ; PedeConfiguracao ; FimJogada ; FimJogo
    Estado avancar();           // -> Varios Estados


    Situacao getSituacao();

}
