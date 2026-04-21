package entitties;

public class Tese extends ItemBiblioteca {
    private String autor;
    private String orientador;
    private String areaPesquisa;

    public Tese(String titulo, String editora, Integer qntdisponivel,
                String autor, String orientador, String areaPesquisa) {
        super(titulo, editora, qntdisponivel);
        setAutor(autor);
        setOrientador(orientador);
        setAreaPesquisa(areaPesquisa);
    }

    public String getAutor() { return autor; }

    public void setAutor(String autor) {
        if (autor == null || autor.trim().isEmpty()) {
            throw new IllegalArgumentException("Autor não pode ser vazio");
        }
        this.autor = autor;
    }

    public String getOrientador() { return orientador; }

    public void setOrientador(String orientador) {
        this.orientador = orientador;
    }

    public String getAreaPesquisa() { return areaPesquisa; }

    public void setAreaPesquisa(String areaPesquisa) {
        this.areaPesquisa = areaPesquisa;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("-Teses-");
        System.out.println("Titulo: " + getTitulo());
        System.out.println("Autor: " + autor);
        System.out.println("Orientador: " + orientador);
        System.out.println("Área de Pesquisa: " + areaPesquisa);
        System.out.println("Editora: " + getEditora());
        System.out.println("Quantidade Disponível: " + getQntdisponivel());
        System.out.println();
    }
}