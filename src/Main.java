import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<Produto> estoque = new ArrayList<>();
    static ArrayList<Categoria> categorias = new ArrayList<>();

    static final int LIMITE_BAIXO = 10;
    static Scanner sc = new Scanner(System.in);

    static void pausar(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    static void limparTerminal() {
        try {

            if (System.getProperty("os.name").contains("Windows")) {

                new ProcessBuilder("cmd", "/c", "cls")
                        .inheritIO()
                        .start()
                        .waitFor();

            } else {

                new ProcessBuilder("clear")
                        .inheritIO()
                        .start()
                        .waitFor();
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void pausarUsuario() {

        System.out.println("\nPressione ENTER para continuar...");
        sc.nextLine();
        sc.nextLine();

        System.out.print("Carregando");

        for (int i = 0; i < 3; i++) {
            pausar(400);
            System.out.print(".");
        }

        System.out.println("\n");
    }

    public static void main(String[] args) {

        categorias.add(new Categoria("camisa"));
        categorias.add(new Categoria("calca"));
        categorias.add(new Categoria("vestido"));
        categorias.add(new Categoria("moletom"));
        categorias.add(new Categoria("shorts"));

        estoque.add(new Produto("Camiseta Basica", categorias.get(0), "M", 49.90, 30));
        estoque.add(new Produto("Calca Jeans Slim", categorias.get(1), "G", 129.90, 8));
        estoque.add(new Produto("Vestido Floral", categorias.get(2), "P", 89.90, 5));
        estoque.add(new Produto("Moletom Canguru", categorias.get(3), "GG", 149.90, 20));
        estoque.add(new Produto("Shorts Tactel", categorias.get(4), "M", 59.90, 2));

        int opcao = 0;

        while (opcao != 12) {

            System.out.println("\n====================================");
            System.out.println("   URBAN THREAD SP — ESTOQUE");
            System.out.println("====================================");
            System.out.println(" 1 - Listar Produtos");
            System.out.println(" 2 - Buscar Produto Por Nome");
            System.out.println(" 3 - Cadastrar Produto");
            System.out.println(" 4 - Entrada de Estoque");
            System.out.println(" 5 - Saida de Estoque");
            System.out.println(" 6 - Atualizar Produto");
            System.out.println(" 7 - Remover Produto");
            System.out.println(" 8 - Ver Estoque Baixo");
            System.out.println(" 9 - Listar Categorias");
            System.out.println(" 10 - Cadastrar Categoria");
            System.out.println(" 11 - Buscar por Categoria");
            System.out.println(" 12 - Sair");
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
                case 9 -> { listarCategorias(); pausarUsuario(); }
                case 10 -> { cadastrarCategoria(); pausarUsuario(); }
                case 11 -> { buscarPorCategoria(); pausarUsuario(); }
                case 12 -> System.out.println("\nAte mais!");

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

        for (Produto p : estoque) {
            p.apresentarProduto();
            pausar(150);
        }
    }



    static void buscarProduto() {

        sc.nextLine();

        System.out.print("\nBuscar: ");
        String busca = sc.nextLine().toLowerCase();

        boolean encontrou = false;

        for (Produto p : estoque) {

            if (p.getNome().toLowerCase().contains(busca)) {

                p.apresentarProduto();
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhum produto encontrado.");
        }
    }

    static void cadastrarProduto() {

        sc.nextLine();

        System.out.print("\nNome: ");
        String nome = sc.nextLine();

        listarCategorias();

        System.out.print("ID categoria: ");
        int idCat = sc.nextInt();

        Categoria cat = null;

        for (Categoria c : categorias) {
            if (c.getId() == idCat) {
                cat = c;
                break;
            }
        }

        if (cat == null) {
            System.out.println("Categoria nao encontrada.");
            return;
        }

        sc.nextLine();

        System.out.print("Tamanho: ");
        String tamanho = sc.nextLine();

        System.out.print("Preco: ");
        double preco = sc.nextDouble();

        System.out.print("Quantidade: ");
        int qtd = sc.nextInt();

        estoque.add(new Produto(nome, cat, tamanho, preco, qtd));

        System.out.println("Produto cadastrado!");
    }

    static void entradaEstoque() {

        listarProdutos();

        Produto p = selecionarProduto();
        if (p == null) return;

        System.out.print("Qtd: ");
        int qtd = sc.nextInt();

        p.setQuantidade(p.getQuantidade() + qtd);

        System.out.println("Atualizado!");
    }

    static void saidaEstoque() {

        listarProdutos();

        Produto p = selecionarProduto();
        if (p == null) return;

        System.out.print("Qtd: ");
        int qtd = sc.nextInt();

        if (qtd > p.getQuantidade()) {
            System.out.println("Estoque insuficiente!");
            return;
        }

        p.setQuantidade(p.getQuantidade() - qtd);

        System.out.println("Atualizado!");
    }

    static void atualizarProduto() {

        listarProdutos();

        Produto p = selecionarProduto();
        if (p == null) return;

        sc.nextLine();

        System.out.print("Novo nome: ");
        p.setNome(sc.nextLine());

        System.out.println("Atualizado!");
    }

    static void removerProduto() {

        listarProdutos();

        Produto p = selecionarProduto();
        if (p == null) return;

        estoque.remove(p);

        System.out.println("Removido!");
    }

    static void verEstoqueBaixo() {

        System.out.println("\n--- ESTOQUE BAIXO ---");

        boolean encontrou = false;

        for (Produto p : estoque) {

            if (p.getQuantidade() < LIMITE_BAIXO) {
                p.apresentarProduto();
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Estoque OK.");
        }
    }

    static void listarCategorias() {

        System.out.println("\n--- CATEGORIAS ---");

        for (Categoria c : categorias) {
            c.apresentarCategoria();
        }
    }

    static void cadastrarCategoria() {

        sc.nextLine();

        System.out.print("Nome categoria: ");
        String nome = sc.nextLine();

        Categoria c = new Categoria(nome);

        if (c.getNome() != null) {
            categorias.add(c);
            System.out.println("Categoria cadastrada!");
        }
    }

    static void buscarPorCategoria() {

        listarCategorias();

        sc.nextLine();

        System.out.print("\nID Categoria: ");
        int busca = sc.nextInt();

        boolean encontrou = false;

        for (Produto p : estoque) {

            if (p.getCategoria().getId() == busca) {
                p.apresentarProduto();
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhum produto nessa categoria.");
        }
    }

    static Produto selecionarProduto() {

        System.out.print("\nID: ");
        int id = sc.nextInt();

        for (Produto p : estoque) {
            if (p.getId() == id) return p;
        }

        System.out.println("Nao encontrado.");
        return null;
    }
}