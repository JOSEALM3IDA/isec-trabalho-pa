package jogo.logica.estados;

import jogo.logica.dados.jogadores.TipoJogador;

public interface Estado {

    // PedeDecisaoInicio
    Estado iniciarJogo();       // -> PedeConfiguracao
    Estado verReplay(String nomeFicheiro);         // -> AssisteJogada

    // PedeConfiguracao
    Estado adicionarJogador(TipoJogador tipoJogador, String nomeJogador);

    // PedeDecisaoJogada
    Estado jogarFicha(int col);      // -> PedeDecisaoJogada / FimJogo
    Estado undoJogada(int numVezes);        // -> PedeDecisaoJogada
    Estado desistir();          // -> FimJogo
    Estado aceitarMinijogo();   // -> JogoMinijogo
    Estado jogarFichaEspecial(int col); // -> PedeDecisaoJogada

    // JogoMinijogo
    Estado jogarMinijogo(String resposta); // -> PedeDecisaoJogada

    // AssisteJogada ; PedeConfiguracao ; FimJogada ; FimJogo
    Estado avancar();           // -> Varios Estados

    Situacao getSituacao();

}
