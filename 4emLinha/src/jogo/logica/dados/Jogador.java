package jogo.logica.dados;

import java.io.Serializable;

public class Jogador implements Serializable {
    String nome;

    public Jogador() {}

    public Jogador(String nome) { this.nome = nome; }

    public String getNome() { return nome; }

    @Override
    public String toString() { return nome; }
}
