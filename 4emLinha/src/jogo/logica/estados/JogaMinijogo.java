package jogo.logica.estados;

import jogo.logica.dados.QuatroEmLinha;

public class JogaMinijogo extends EstadoAdapter {

    protected JogaMinijogo(QuatroEmLinha quatroEmLinha) { super(quatroEmLinha); }

    @Override
    public Estado jogarMinijogo(String resposta) {
        quatroEmLinha.enviarRespostaMinijogo(resposta);

        if (quatroEmLinha.isAcabadoMinijogo()) {
            if (quatroEmLinha.ganhouUltimoMinijogo()) quatroEmLinha.adicionaFichaEspecialJogadorAtual();

            return new PedeDecisaoJogada(quatroEmLinha);
        }

        return this;
    }

    @Override
    public Situacao getSituacao() { return Situacao.JogaMinijogo; }
}
