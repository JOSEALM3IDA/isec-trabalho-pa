package jogo.logica.command;

public interface Command {

    // operacao representada por este comando
    // retorna true se a operacao foi realizada com sucesso
    // e se a operacao de undo for possivel
    boolean execute();

    // undo correspondente a esta operacao
    // retorna true se o undo teve sucesso
    boolean undo();
}
