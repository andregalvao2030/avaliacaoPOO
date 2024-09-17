package banco;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private List<Pessoa> pessoas = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    // Método principal
    public static void main(String[] args) {
        Main sistema = new Main();
        sistema.menu();
    }

    // Menu principal
    public void menu() {
        while (true) {
            System.out.println("\n==================== Menu Principal ====================");
            System.out.println("1. Cadastrar Pessoa e Conta");
            System.out.println("2. Listar Pessoas e Contas");
            System.out.println("3. Editar Conta");
            System.out.println("4. Sair");
            System.out.println("=======================================================");

            int opcao = lerOpcao("Escolha uma opção (1-4):", 1, 4);

            switch (opcao) {
                case 1:
                    cadastrarPessoa();
                    break;
                case 2:
                    listarPessoas();
                    break;
                case 3:
                    editarConta();
                    break;
                case 4:
                    System.out.println("Saindo... Até logo!");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    // Cadastra uma nova pessoa e uma conta
    public void cadastrarPessoa() {
        System.out.print("Digite o nome da pessoa: ");
        String nome = scanner.nextLine().trim();

        System.out.println("Escolha o tipo de conta:");
        System.out.println("1. Conta Corrente");
        System.out.println("2. Conta Poupança");
        System.out.println("3. Conta Salário");
        int tipoConta = lerOpcao("Escolha uma opção (1-3):", 1, 3);

        Conta conta = null;
        switch (tipoConta) {
            case 1:
                conta = criarContaCorrente();
                break;
            case 2:
                conta = criarContaPoupanca();
                break;
            case 3:
                conta = criarContaSalario();
                break;
        }

        Pessoa pessoa = new Pessoa(nome, conta);
        pessoas.add(pessoa);
        System.out.println("Pessoa e conta cadastradas com sucesso!");
    }

    // Cria uma Conta Corrente
    private ContaCorrente criarContaCorrente() {
        String numero = lerString("Número da conta: ");
        String agencia = lerString("Agência: ");
        Date dataAbertura = lerData("Data de abertura (dd/MM/yyyy): ");
        double saldo = lerDouble("Saldo inicial: ");
        return new ContaCorrente(numero, agencia, dataAbertura, saldo);
    }

    // Cria uma Conta Poupança
    private ContaPoupanca criarContaPoupanca() {
        String numero = lerString("Número da conta: ");
        String agencia = lerString("Agência: ");
        Date dataAbertura = lerData("Data de abertura (dd/MM/yyyy): ");
        double saldo = lerDouble("Saldo inicial: ");
        return new ContaPoupanca(numero, agencia, dataAbertura, saldo);
    }

    // Cria uma Conta Salário
    private ContaSalario criarContaSalario() {
        String numero = lerString("Número da conta: ");
        String agencia = lerString("Agência: ");
        Date dataAbertura = lerData("Data de abertura (dd/MM/yyyy): ");
        double saldo = lerDouble("Saldo inicial: ");
        int transacoes = lerInt("Número de transações: ");
        return new ContaSalario(numero, agencia, dataAbertura, saldo, transacoes);
    }

    // Lê uma string
    private String lerString(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine().trim();
    }

    // Lê um número inteiro com validação
    private int lerInt(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número inteiro válido.");
                scanner.next(); // Limpa o buffer
            }
        }
    }

    // Lê um número decimal com validação
    private double lerDouble(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número válido.");
                scanner.next(); // Limpa o buffer
            }
        }
    }

    // Lê uma data com validação
    private Date lerData(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return dateFormat.parse(scanner.nextLine());
            } catch (ParseException e) {
                System.out.println("Data inválida. Por favor, use o formato dd/MM/yyyy.");
            }
        }
    }

    // Lê uma opção entre um intervalo específico
    private int lerOpcao(String mensagem, int min, int max) {
        while (true) {
            try {
                System.out.print(mensagem);
                int opcao = scanner.nextInt();
                scanner.nextLine(); // Limpa o buffer
                if (opcao >= min && opcao <= max) {
                    return opcao;
                } else {
                    System.out.println("Opção fora do intervalo. Escolha entre " + min + " e " + max + ".");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número válido.");
                scanner.next(); // Limpa o buffer
            }
        }
    }

    // Lista todas as pessoas e suas contas
    public void listarPessoas() {
        if (pessoas.isEmpty()) {
            System.out.println("Nenhuma pessoa cadastrada.");
            return;
        }
        for (Pessoa pessoa : pessoas) {
            System.out.println("\nNome: " + pessoa.getNome());
            Conta conta = pessoa.getConta();
            System.out.println("Número da Conta: " + conta.getNumeroConta());
            System.out.println("Agência: " + conta.getAgencia());
            System.out.println("Data de Abertura: " + dateFormat.format(conta.getDataAbertura()));
            System.out.println("Saldo: " + conta.getSaldo());
            if (conta instanceof ContaPoupanca) {
                System.out.println("Rendimento: " + ((ContaPoupanca) conta).calcularRendimento());
            } else if (conta instanceof ContaSalario) {
                System.out.println("Taxa de Manutenção: " + ((ContaSalario) conta).getTaxaManutencao());
            } else if (conta instanceof ContaCorrente) {
                System.out.println("Taxa de Manutenção: " + ((ContaCorrente) conta).getTaxaManutencao());
            }
        }
    }

    // Edita uma conta existente
    public void editarConta() {
        String numeroConta = lerString("Digite o número da conta para editar: ");
        Conta conta = encontrarContaPorNumero(numeroConta);

        if (conta == null) {
            System.out.println("Conta não encontrada.");
            return;
        }

        System.out.println("Escolha o que deseja editar:");
        System.out.println("1. Saldo");
        if (conta instanceof ContaSalario) {
            System.out.println("2. Número de Transações");
        }
        int escolha = lerOpcao("Escolha uma opção:", 1, conta instanceof ContaSalario ? 2 : 1);

        switch (escolha) {
            case 1:
                double novoSaldo = lerDouble("Digite o novo saldo: ");
                conta.setSaldo(novoSaldo);
                System.out.println("Saldo atualizado com sucesso.");
                break;
            case 2:
                if (conta instanceof ContaSalario) {
                    int novasTransacoes = lerInt("Digite o novo número de transações: ");
                    ((ContaSalario) conta).setNumeroTransacoes(novasTransacoes);
                    System.out.println("Número de transações atualizado com sucesso.");
                } else {
                    System.out.println("Essa opção é válida apenas para Conta Salário.");
                }
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    // Encontra uma conta pelo número
    private Conta encontrarContaPorNumero(String numeroConta) {
        for (Pessoa pessoa : pessoas) {
            if (pessoa.getConta().getNumeroConta().equals(numeroConta)) {
                return pessoa.getConta();
            }
        }
        return null;
    }
}


