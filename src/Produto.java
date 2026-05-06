import java.util.ArrayList;

public class Produto {
        static int proximoId = 0;

        int    id;
        String nome;
        String categoria;
        String tamanho;
        double preco;
        int    quantidade;

    Produto( String nome,String categoria, String tamanho, double preco, int quantidade) {
        proximoId++;

        this.id         = proximoId;
        this.nome       = nome;
        this.categoria = categoria;
        this.tamanho    = tamanho;
        this.preco      = preco;
        this.quantidade = quantidade;
    }

    public void apresentarProduto(){
        System.out.printf("ID: %d NOME: %s CATEGORIA: %s TAMANHO: %s  PRECO: %.2f QUANTIDADE: %d\n", id, nome, categoria, tamanho, preco, quantidade);
    }


}
