package entitties;

public class Livro extends ItemBiblioteca {
    private String autor;

    public Livro(String titulo, String editora, Integer qntdisponivel, String autor) {
        super(titulo, editora, qntdisponivel);
        setAutor(autor);
    }

    public String getAutor() { return autor; }

    public void setAutor(String autor) {
        if (autor == null || autor.trim().isEmpty()) {
            throw new IllegalArgumentException("Autor não pode ser vazio");
        }
        this.autor = autor;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("=== LIVRO ===");
        System.out.println("Titulo: " + getTitulo());
        System.out.println("Autor: " + autor);
        System.out.println("Editora: " + getEditora());
        System.out.println("Quantidade Disponível: " + getQntdisponivel());
        System.out.println();
    }
}