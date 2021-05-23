package jogo.logica.command;

public class DesfazerJogadaCommand extends CommandAdapter {

    Command jogada;

    public DesfazerJogadaCommand(Command jogada) {
        this.jogada = jogada;
    }

    @Override
    public boolean execute() { return jogada.undo(); }

}
