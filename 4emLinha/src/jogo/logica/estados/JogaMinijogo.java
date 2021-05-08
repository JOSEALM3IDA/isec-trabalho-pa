package jogo.logica.estados;

import jogo.logica.dados.QuatroEmLinha;

public class JogaMinijogo extends EstadoAdapter {

    protected JogaMinijogo(QuatroEmLinha quatroEmLinha) { super(quatroEmLinha); }

    @Override
    public Estado ganharMinijogo() {
        // TODO
        System.out.println("GANHAR MINIJOGO - WIP");
        return super.ganharMinijogo();
    }

    @Override
    public Estado perderMinijogo() {
        // TODO
        System.out.println("PERDER MINIJOGO - WIP");
        return super.perderMinijogo();
    }

    @Override
    public Situacao getSituacao() { return Situacao.JogaMinijogo; }
}
