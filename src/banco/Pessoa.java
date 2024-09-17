package banco;

public class Pessoa {
    private String nome;
    private Conta conta;

    // Construtor
    public Pessoa(String nome, Conta conta) {
        this.nome = nome;
        this.conta = conta;
    }

    // Métodos Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }
}

