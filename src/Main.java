import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<Produto> estoque = new ArrayList<>();
    static final int LIMITE_BAIXO = 10;
    static Scanner sc = new Scanner(System.in);

    // ========= PAUSA =========
    static void pausar(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // ========= ENTER + LOADING =========
    static void pausarUsuario() {
        System.out.println("\nPressione ENTER para continuar...");
        sc.nextLine(); // limpa buffer
        sc.nextLine(); // espera ENTER

        System.out.print("Carregando");
        for (int i = 0; i < 3; i++) {
            pausar(400);
            System.out.print(".");
        }
        System.out.println("\n");
    }

    public static void main(String[] args) {

        estoque.add(new Produto("Camiseta Basica", "camisa", "M", 49.90, 30));
        estoque.add(new Produto("Calca Jeans Slim", "calca", "G", 129.90, 8));
        estoque.add(new Produto("Vestido Floral", "vestido", "P", 89.90, 5));
        estoque.add(new Produto("Moletom Canguru", "moletom", "GG", 149.90, 20));
        estoque.add(new Produto("Shorts Tactel", "shorts", "M", 59.90, 2));

        int opcao = 0;

        while (opcao != 9) {
            System.out.println("\n====================================");
            System.out.println("   URBAN THREAD SP — ESTOQUE");
            System.out.println("====================================");
            System.out.println(" 1 - Listar Produtos");
            System.out.println(" 2 - Buscar Produto");
            System.out.println(" 3 - Cadastrar Produto");
            System.out.println(" 4 - Entrada de Estoque");
            System.out.println(" 5 - Saida de Estoque");
            System.out.println(" 6 - Atualizar Produto");
            System.out.println(" 7 - Remover Produto");
            System.out.println(" 8 - Ver Estoque Baixo");
            System.out.println(" 9 - Sair");
            System.out.println("====================================");
            System.out.print("Opcao: ");

            if (!sc.hasNextInt()) {
                System.out.println("Por favor, digite um numero.");
                sc.next();
                continue;
            }

            opcao = sc.nextInt();

            System.out.println("\nProcessando...");
            pausar(600);

            switch (opcao) {
                case 1 -> { listarProdutos(); pausarUsuario(); }
                case 2 -> { buscarProduto(); pausarUsuario(); }
                case 3 -> { cadastrarProduto(); pausarUsuario(); }
                case 4 -> { entradaEstoque(); pausarUsuario(); }
                case 5 -> { saidaEstoque(); pausarUsuario(); }
                case 6 -> { atualizarProduto(); pausarUsuario(); }
                case 7 -> { removerProduto(); pausarUsuario(); }
                case 8 -> { verEstoqueBaixo(); pausarUsuario(); }
                case 9 -> System.out.println("\nAte mais!");
                default -> System.out.println("Opcao invalida.");
            }
        }
    }

    static void listarProdutos() {
        if (estoque.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        System.out.println("\n--- PRODUTOS CADASTRADOS ---");
        System.out.println("-".repeat(60));

        for (Produto p : estoque) {
            System.out.printf("%d | %s | %s | %s | R$%.2f | %d%n",
                    p.id, p.nome, p.categoria, p.tamanho, p.preco, p.quantidade);
            pausar(150);
        }
    }

    static void buscarProduto() {
        sc.nextLine(); // limpar buffer
        System.out.print("\nDigite o nome ou categoria: ");
        String busca = sc.nextLine().toLowerCase();

        boolean encontrou = false;

        for (Produto p : estoque) {
            if (p.nome.toLowerCase().contains(busca) || p.categoria.toLowerCase().contains(busca)) {
                imprimirProduto(p);
                pausar(200);
                encontrou = true;
            }
        }

        if (!encontrou) System.out.println("Nenhum produto encontrado.");
    }

    static void cadastrarProduto() {
        sc.nextLine(); // limpar buffer

        System.out.print("\nNome: ");
        String nome = sc.nextLine();

        System.out.print("Preco: ");
        double preco = sc.nextDouble();

        System.out.print("Quantidade: ");
        int qtd = sc.nextInt();

        estoque.add(new Produto(nome, "Outro", "M", preco, qtd));

        System.out.println("Salvando...");
        pausar(800);

        System.out.println("Produto cadastrado!");
    }

    static void entradaEstoque() {
        listarProdutos();
        Produto p = selecionarProduto();
        if (p == null) return;

        System.out.print("Qtd: ");
        int qtd = sc.nextInt();

        p.quantidade += qtd;

        System.out.println("Atualizando...");
        pausar(600);

        System.out.println("Estoque atualizado!");
    }

    static void saidaEstoque() {
        listarProdutos();
        Produto p = selecionarProduto();
        if (p == null) return;

        System.out.print("Qtd: ");
        int qtd = sc.nextInt();

        if (qtd > p.quantidade) {
            System.out.println("Estoque insuficiente!");
            return;
        }

        p.quantidade -= qtd;

        System.out.println("Atualizando...");
        pausar(600);

        System.out.println("Saida registrada!");
    }

    static void atualizarProduto() {
        listarProdutos();
        Produto p = selecionarProduto();
        if (p == null) return;

        sc.nextLine(); // limpar buffer
        System.out.print("Novo nome: ");
        p.nome = sc.nextLine();

        System.out.println("Salvando...");
        pausar(600);

        System.out.println("Atualizado!");
    }

    static void removerProduto() {
        listarProdutos();
        Produto p = selecionarProduto();
        if (p == null) return;

        estoque.remove(p);

        System.out.println("Removendo...");
        pausar(600);

        System.out.println("Removido!");
    }

    static void verEstoqueBaixo() {
        System.out.println("\n--- ESTOQUE BAIXO ---");

        boolean encontrou = false;

        for (Produto p : estoque) {
            if (p.quantidade < LIMITE_BAIXO) {
                imprimirProduto(p);
                pausar(200);
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Todos os produtos estão com estoque ok.");
        }
    }

    static Produto selecionarProduto() {
        System.out.print("\nID: ");
        int id = sc.nextInt();

        for (Produto p : estoque) {
            if (p.id == id) return p;
        }

        System.out.println("Nao encontrado.");
        return null;
    }

    static void imprimirProduto(Produto p) {
        System.out.printf("%d | %s | %s | %s | R$%.2f | %d%n",
                p.id, p.nome, p.categoria, p.tamanho, p.preco, p.quantidade);
    }
}