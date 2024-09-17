package banco;

import java.util.Date;

public class ContaCorrente extends Conta {
    private static final double TAXA_MANTENCAO = 30.00;

    // Construtor vazio
    public ContaCorrente() {
    }

    // Construtor com todos os atributos
    public ContaCorrente(String numeroConta, String agencia, Date dataAbertura, double saldo) {
        super(numeroConta, agencia, dataAbertura, saldo);
    }

    public double getTaxaManutencao() {
        return TAXA_MANTENCAO;
    }
}