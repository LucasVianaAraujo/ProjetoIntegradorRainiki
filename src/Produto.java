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


}
