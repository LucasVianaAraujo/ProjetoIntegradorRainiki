import java.util.ArrayList;
import java.util.Scanner;

// ============================================================
//  URBAN THREAD SP — Sistema de Controle de Estoque
//  Projeto Integrador — SENAC
// ============================================================

public class Main {

    // --- Modelo de produto ---
    static class Produto {
        int    id;
        String nome;
        String categoria; // MELHORIA: campo novo
        String tamanho;   // MELHORIA: campo novo
        double preco;
        int    quantidade;

        Produto(int id, String nome, String categoria, String tamanho, double preco, int quantidade) {
            this.id         = id;
            this.nome       = nome;
            this.categoria  = categoria;
            this.tamanho    = tamanho;
            this.preco      = preco;
            this.quantidade = quantidade;
        }
    }

    // --- Estado global ---
    static ArrayList<Produto> estoque   = new ArrayList<>();
    static Scanner            sc        = new Scanner(System.in);
    static int                proximoId = 6;
    static final int          LIMITE_BAIXO = 10; // MELHORIA: constante, fácil de mudar

    // ============================================================
    public static void main(String[] args) {

        // Produtos pré-carregados
        estoque.add(new Produto(1, "Camiseta Basica",  "Camiseta", "M",  49.90, 30));
        estoque.add(new Produto(2, "Calca Jeans Slim", "Calca",    "G",  129.90, 8));
        estoque.add(new Produto(3, "Vestido Floral",   "Vestido",  "P",  89.90, 5));
        estoque.add(new Produto(4, "Moletom Canguru",  "Moletom",  "GG", 149.90, 20));
        estoque.add(new Produto(5, "Shorts Tactel",    "Shorts",   "M",  59.90, 2));

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

            // MELHORIA: protege contra entrada inválida (texto no lugar de número)
            if (!sc.hasNextInt()) {
                System.out.println("Por favor, digite um numero.");
                sc.next();
                continue;
            }
            opcao = sc.nextInt();

            switch (opcao) {
                case 1 -> listarProdutos();
                case 2 -> buscarProduto();
                case 3 -> cadastrarProduto();
                case 4 -> entradaEstoque();
                case 5 -> saidaEstoque();
                case 6 -> atualizarProduto();
                case 7 -> removerProduto();
                case 8 -> verEstoqueBaixo();
                case 9 -> System.out.println("\nAte mais! Obrigado por usar o sistema.");
                default -> System.out.println("Opcao invalida. Tente novamente.");
            }
        }
    }

    // ============================================================
    //  LISTAR
    // ============================================================
    static void listarProdutos() {
        if (estoque.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }
        System.out.println("\n--- PRODUTOS CADASTRADOS ---");
        System.out.printf("%-4s %-20s %-10s %-6s %9s %6s%n",
                "ID", "Nome", "Categoria", "Tam", "Preco", "Qtd");
        System.out.println("-".repeat(60));
        for (Produto p : estoque) {
            System.out.printf("%-4d %-20s %-10s %-6s R$%7.2f %6d%n",
                    p.id, p.nome, p.categoria, p.tamanho, p.preco, p.quantidade);
        }
    }

    // ============================================================
    //  BUSCAR
    // ============================================================
    static void buscarProduto() {
        System.out.print("\nDigite o nome ou categoria: ");
        String busca = sc.next().toLowerCase();
        boolean encontrou = false;

        for (Produto p : estoque) {
            if (p.nome.toLowerCase().contains(busca) ||
                    p.categoria.toLowerCase().contains(busca)) {
                imprimirProduto(p);
                encontrou = true;
            }
        }
        if (!encontrou) System.out.println("Nenhum produto encontrado para \"" + busca + "\".");
    }

    // ============================================================
    //  CADASTRAR
    // ============================================================
    static void cadastrarProduto() {
        System.out.print("\nNome: ");
        String nome = sc.next();

        System.out.println("Categoria:");
        System.out.println(" 1-Camiseta  2-Calca  3-Vestido  4-Moletom  5-Shorts  6-Outro");
        System.out.print("Opcao: ");
        String[] cats = {"Camiseta","Calca","Vestido","Moletom","Shorts","Outro"};
        int catIdx = sc.nextInt() - 1;
        String categoria = (catIdx >= 0 && catIdx < cats.length) ? cats[catIdx] : "Outro";

        System.out.println("Tamanho: 1-PP  2-P  3-M  4-G  5-GG  6-XGG");
        System.out.print("Opcao: ");
        String[] tams = {"PP","P","M","G","GG","XGG"};
        int tamIdx = sc.nextInt() - 1;
        String tamanho = (tamIdx >= 0 && tamIdx < tams.length) ? tams[tamIdx] : "M";

        System.out.print("Preco: ");
        double preco = sc.nextDouble();

        System.out.print("Quantidade inicial: ");
        int qty = sc.nextInt();

        // MELHORIA: validação básica
        if (preco < 0 || qty < 0) {
            System.out.println("Preco e quantidade nao podem ser negativos.");
            return;
        }

        estoque.add(new Produto(proximoId++, nome, categoria, tamanho, preco, qty));
        System.out.println("Produto \"" + nome + "\" cadastrado com sucesso! ID: " + (proximoId - 1));
    }

    // ============================================================
    //  ENTRADA
    // ============================================================
    static void entradaEstoque() {
        listarProdutos();
        Produto p = selecionarProduto();
        if (p == null) return;

        System.out.print("Quantidade a adicionar: ");
        int qtd = sc.nextInt();
        if (qtd <= 0) { System.out.println("Quantidade deve ser maior que zero."); return; }

        p.quantidade += qtd;
        System.out.printf("Entrada registrada. Estoque de \"%s\": %d unidades.%n", p.nome, p.quantidade);
    }

    // ============================================================
    //  SAIDA
    // ============================================================
    static void saidaEstoque() {
        listarProdutos();
        Produto p = selecionarProduto();
        if (p == null) return;

        System.out.print("Quantidade a retirar: ");
        int qtd = sc.nextInt();

        if (qtd <= 0) { System.out.println("Quantidade deve ser maior que zero."); return; }
        if (qtd > p.quantidade) {
            System.out.printf("Estoque insuficiente. Disponivel: %d unidades.%n", p.quantidade);
            return;
        }

        p.quantidade -= qtd;
        System.out.printf("Saida registrada. Estoque de \"%s\": %d unidades.%n", p.nome, p.quantidade);
    }

    // ============================================================
    //  ATUALIZAR
    // ============================================================
    static void atualizarProduto() {
        listarProdutos();
        Produto p = selecionarProduto();
        if (p == null) return;

        System.out.print("Novo nome (atual: " + p.nome + "): ");
        p.nome = sc.next();
        System.out.print("Novo preco (atual: R$" + String.format("%.2f", p.preco) + "): ");
        p.preco = sc.nextDouble();
        System.out.println("Produto atualizado com sucesso.");
    }

    // ============================================================
    //  REMOVER
    // ============================================================
    static void removerProduto() {
        listarProdutos();
        Produto p = selecionarProduto();
        if (p == null) return;

        estoque.remove(p);
        System.out.println("Produto \"" + p.nome + "\" removido com sucesso.");
    }

    // ============================================================
    //  ESTOQUE BAIXO
    // ============================================================
    static void verEstoqueBaixo() {
        System.out.println("\n--- ESTOQUE BAIXO (abaixo de " + LIMITE_BAIXO + " unidades) ---");
        boolean encontrou = false;
        for (Produto p : estoque) {
            if (p.quantidade < LIMITE_BAIXO) {
                imprimirProduto(p);
                encontrou = true;
            }
        }
        if (!encontrou) System.out.println("Todos os produtos estao com estoque adequado.");
    }

    // ============================================================
    //  AUXILIARES
    // ============================================================
    static Produto selecionarProduto() {
        System.out.print("\nDigite o ID do produto: ");
        if (!sc.hasNextInt()) { sc.next(); System.out.println("ID invalido."); return null; }
        int id = sc.nextInt();
        for (Produto p : estoque) {
            if (p.id == id) return p;
        }
        System.out.println("Produto com ID " + id + " nao encontrado.");
        return null;
    }

    static void imprimirProduto(Produto p) {
        System.out.printf("ID: %d | %s | %s | Tam: %s | R$%.2f | Qtd: %d%n",
                p.id, p.nome, p.categoria, p.tamanho, p.preco, p.quantidade);
    }
}