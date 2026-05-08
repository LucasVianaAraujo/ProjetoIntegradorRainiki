public class Produto {

    static int proximoId = 0;

    private int id;
    private String nome;
    private Categoria categoria;
    private String tamanho;
    private double preco;
    private int quantidade;

    public Produto(
            String nome,
            Categoria categoria,
            String tamanho,
            double preco,
            int quantidade
    ) {

        proximoId++;
        this.id = proximoId;

        setNome(nome);
        setCategoria(categoria);
        setTamanho(tamanho);
        setPreco(preco);
        setQuantidade(quantidade);
    }

    public void setNome(String nome) {

        if (nome == null || nome.trim().isEmpty()) {
            System.out.println("ERRO: o nome não pode ser vazio.");
            return;
        }

        if (nome.trim().length() < 3) {
            System.out.println("ERRO: o nome deve ter pelo menos 3 caracteres.");
            return;
        }

        this.nome = nome.trim();
    }

    public void setCategoria(Categoria categoria) {

        if (categoria == null) {
            System.out.println("ERRO: categoria inválida.");
            return;
        }

        this.categoria = categoria;
    }

    public void setTamanho(String tamanho) {

        if (tamanho == null || tamanho.trim().isEmpty()) {
            System.out.println("ERRO: o tamanho não pode ser vazio.");
            return;
        }

        tamanho = tamanho.trim().toUpperCase();

        if (
                !tamanho.equals("PP") &&
                        !tamanho.equals("P") &&
                        !tamanho.equals("M") &&
                        !tamanho.equals("G") &&
                        !tamanho.equals("GG")
        ) {

            System.out.println("ERRO: tamanho inválido.");
            return;
        }

        this.tamanho = tamanho;
    }

    public void setPreco(double preco) {

        if (preco <= 0) {
            System.out.println("ERRO: o preço deve ser maior que zero.");
            return;
        }

        this.preco = preco;
    }

    public void setQuantidade(int quantidade) {

        if (quantidade < 0) {
            System.out.println("ERRO: a quantidade não pode ser negativa.");
            return;
        }

        this.quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public String getTamanho() {
        return tamanho;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void apresentarProduto() {

        System.out.printf(
                "ID: %d | NOME: %s | CATEGORIA: %s | TAMANHO: %s | PRECO: R$%.2f | QUANTIDADE: %d%n",
                id,
                nome,
                categoria.getNome(),
                tamanho,
                preco,
                quantidade
        );
    }
}