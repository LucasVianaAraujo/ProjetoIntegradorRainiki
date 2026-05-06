public class Categoria {

    static private int proximoId = 0;
    private int id;
    private String nome;

    public Categoria(String nome){
        if(nome.length() <= 2){
            System.out.println("Nome de classificação muito curto, classificação não registrada.");
            return;
        }

        proximoId++;
        id = proximoId;

        this.nome = nome;
    }

    public String retornaNome(){
        return nome;
    }

    public void apresentarCategoria(){
        System.out.printf("ID: %d NOME:%s\n", id, nome);
    }
}
