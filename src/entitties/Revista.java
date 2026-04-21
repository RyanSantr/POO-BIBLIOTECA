package entitties;

public class Revista extends ItemBiblioteca {
    private String editor;
    private String mesPublicado;

    public Revista(String titulo, String editora, Integer qntdisponivel,
                   String editor, String mesPublicado) {
        super(titulo, editora, qntdisponivel);
        setEditor(editor);
        setMesPublicado(mesPublicado);
    }

    public String getEditor() { return editor; }

    public void setEditor(String editor) {
        if (editor == null || editor.trim().isEmpty()) {
            throw new IllegalArgumentException("Não pode deixar o editor vazio");
        }
        this.editor = editor;
    }

    public String getMesPublicado() { return mesPublicado; }

    public void setMesPublicado(String mesPublicado) {
        if (mesPublicado == null || mesPublicado.trim().isEmpty()) {
            throw new IllegalArgumentException("Digite o mês válido");
        }

        String[] mesesValidos = {
                "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho",
                "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"
        };

        String mesNormalizado = mesPublicado.trim().substring(0, 1).toUpperCase() +
                mesPublicado.trim().substring(1).toLowerCase();

        boolean mesValido = false;
        for (String mes : mesesValidos) {
            if (mes.equals(mesNormalizado)) {
                mesValido = true;
                break;
            }
        }

        if (!mesValido) {
            throw new IllegalArgumentException("Digite um mês válido EX: Janeiro, Fevereiro");
        }
        this.mesPublicado = mesNormalizado;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("=== REVISTA ===");
        System.out.println("Titulo: " + getTitulo());
        System.out.println("Editor: " + editor);
        System.out.println("Editora: " + getEditora());
        System.out.println("Mês Publicação: " + mesPublicado);
        System.out.println("Quantidade Disponível: " + getQntdisponivel());
        System.out.println();
    }
}