package banco;

import java.util.Date;

public class ContaPoupanca extends Conta {
    private static final double RENDIMENTO = 0.15;

    // Construtor vazio
    public ContaPoupanca() {
    }

    // Construtor com todos os atributos
    public ContaPoupanca(String numeroConta, String agencia, Date dataAbertura, double saldo) {
        super(numeroConta, agencia, dataAbertura, saldo);
    }

    public double calcularRendimento() {
        return getSaldo() * RENDIMENTO;
    }
}
