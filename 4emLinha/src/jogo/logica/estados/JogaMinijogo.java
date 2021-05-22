package jogo.logica.estados;

import jogo.logica.QuatroEmLinhaGestor;

public class JogaMinijogo extends EstadoAdapter {

    protected JogaMinijogo(QuatroEmLinhaGestor quatroEmLinhaGestor) { super(quatroEmLinhaGestor); }

    @Override
    public Estado jogarMinijogo(String resposta) {
        quatroEmLinhaGestor.enviarRespostaMinijogo(resposta);

        if (quatroEmLinhaGestor.isAcabadoMinijogo()) {
            if (quatroEmLinhaGestor.ganhouUltimoMinijogo()) quatroEmLinhaGestor.adicionaFichaEspecialJogadorAtual();

            return new PedeDecisaoJogada(quatroEmLinhaGestor);
        }

        return this;
    }

    @Override
    public Situacao getSituacao() { return Situacao.JogaMinijogo; }
}
