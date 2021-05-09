package jogo.logica.estados;

public interface Estado {

    // PedeDecisaoInicio
    Estado iniciarJogo();       // -> PedeConfiguracao
    Estado continuarJogo();     // -> PedeDecisaoJogada
    Estado verReplay();         // -> AssisteJogada

    // PedeConfiguracao
    Estado adicionarJogador();
    Estado adicionarJogador(String nome);

    // PedeDecisaoJogada
    Estado jogar();             // -> FimJogada
    Estado undoJogada();        // -> PedeDecisaoJogada
    Estado gravarJogo();        // -> PedeDecisaoJogada
    Estado desistir();          // -> FimJogo
    Estado aceitarMinijogo();   // -> JogoMinijogo

    // JogoMinijogo
    Estado ganharMinijogo();    // -> PedeDecisaoJogada
    Estado perderMinijogo();    // -> FimJogada

    // FimJogada
    Estado ganharJogo();        // -> FimJogo

    // AssisteJogada ; PedeConfiguracao ; FimJogada ; FimJogo
    Estado avancar();           // -> Varios Estados


    Situacao getSituacao();

}
