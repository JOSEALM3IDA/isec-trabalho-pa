package jogo.logica.dados;

public class Jogador {
    String nome;

    public Jogador(String nome) { this.nome = nome; }

    public String getNome() { return nome; }

    @Override
    public String toString() { return nome; }
}
