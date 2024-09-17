package banco;

import java.util.Date;

public class Conta {
    private String numeroConta;
    private String agencia;
    private Date dataAbertura;
    private double saldo;

    // Construtor vazio
    public Conta() {
    }

    // Construtor com todos os atributos
    public Conta(String numeroConta, String agencia, Date dataAbertura, double saldo) {
        this.numeroConta = numeroConta;
        this.agencia = agencia;
        this.dataAbertura = dataAbertura;
        this.saldo = saldo;
    }

    // Métodos Getters e Setters
    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public Date getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(Date dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    // Método para movimentar a conta (exemplo simples)
    public void movimentar(double valor) {
        this.saldo += valor;
    }
}
