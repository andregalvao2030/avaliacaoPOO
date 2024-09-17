package banco;

import java.util.Date;

public class ContaSalario extends Conta {
    private static final double TAXA_MANTENCAO = 10.00;
    private int numeroTransacoes;

    // Construtor vazio
    public ContaSalario() {
    }

    // Construtor com todos os atributos
    public ContaSalario(String numeroConta, String agencia, Date dataAbertura, double saldo, int numeroTransacoes) {
        super(numeroConta, agencia, dataAbertura, saldo);
        this.numeroTransacoes = numeroTransacoes;
    }

    public double getTaxaManutencao() {
        if (numeroTransacoes > 2) {
            return TAXA_MANTENCAO;
        } else {
            return 0;
        }
    }

    public void setNumeroTransacoes(int numeroTransacoes) {
        this.numeroTransacoes = numeroTransacoes;
    }

    public int getNumeroTransacoes() {
        return numeroTransacoes;
    }
}
