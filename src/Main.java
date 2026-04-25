import java.util.Scanner;

public class Main {
    public static void main (String args []) {
        Scanner sc = new Scanner(System.in);

        int opcao = 0;

        while (opcao != 9) {

            System.out.println("===== PROJETO DE ESTOQUE =====");
            System.out.println("Olá seja bem-vindo, oque gostaria de fazer?");
            System.out.println("==========================");
            System.out.println("1 - Listar Produto");
            System.out.println("2 - Buscar Produto");
            System.out.println("3 - Cadastrar Produto");
            System.out.println("4 - Entrada de estoque");
            System.out.println("5 - Saida de estoque");
            System.out.println("6 - Atualizar produto");
            System.out.println("7 - Remover produto");
            System.out.println("8 - Ver Estoque Baixo");
            System.out.println("9 - Sair");
            System.out.println("==========================");

            opcao = sc.nextInt();

            if (opcao == 1) {
                System.out.println("Listar Produto");
            } else if (opcao == 2) {
                System.out.println("Buscar Produto");
            } else if (opcao == 3) {
                System.out.println("Cadastrar Produto");
            } else if (opcao == 4) {
                System.out.println("Entrada de estoque");
            } else if (opcao == 5) {
                System.out.println("Saida de estoque");
            } else if (opcao == 6) {
                System.out.println("Atualizar produto");
            } else if (opcao == 7) {
                System.out.println("Remover produto");
            } else if (opcao == 8) {
                System.out.println("Ver Estoque Baixo");
            } else if (opcao == 9) {
                System.out.println("Saindo...");
            } else {
                System.out.println("Opção inválida");
            }
        }
    }
}