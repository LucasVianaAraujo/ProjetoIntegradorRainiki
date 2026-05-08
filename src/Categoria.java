public class Categoria {

    static private int proximoId = 0;

    private int id;
    private String nome;

    public Categoria(String nome) {

        setNome(nome);

        if (this.nome == null) {
            return;
        }

        proximoId++;
        id = proximoId;
    }

    public void setNome(String nome) {

        if (nome == null || nome.trim().isEmpty()) {
            System.out.println("Nome da classificação não pode ser vazio.");
            return;
        }

        if (nome.trim().length() <= 2) {
            System.out.println("Nome da classificação muito curto.");
            return;
        }

        this.nome = nome.trim();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void apresentarCategoria() {

        System.out.printf(
                "ID: %d NOME: %s\n",
                id,
                nome
        );
    }
}