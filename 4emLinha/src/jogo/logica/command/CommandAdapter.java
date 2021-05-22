package jogo.logica.command;

import jogo.logica.dados.QuatroEmLinha;

import java.io.Serializable;

public class CommandAdapter implements Command, Serializable {
    protected final QuatroEmLinha receiver;

    protected CommandAdapter(QuatroEmLinha receiver){ this.receiver = receiver; }

    @Override
    public boolean execute() { return false; }

    @Override
    public boolean undo() { return false; }
}
