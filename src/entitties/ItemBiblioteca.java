package entitties;

public class ItemBiblioteca {
    private String titulo;
    private Integer qntpg;
    private String genero;
    private String editora;
    private Integer qntdisponivel;
    private Integer classificacaoIndicativa;

    // Construtor
    public ItemBiblioteca(String titulo, String editora, Integer qntdisponivel) {
        setTitulo(titulo);
        setEditora(editora);
        setQntdisponivel(qntdisponivel);
    }

    // Métodos de controle
    public boolean emprestar() {
        if (qntdisponivel != null && qntdisponivel > 0) {
            qntdisponivel--;
            return true;
        }
        return false;
    }

    public void devolver() {
        if (qntdisponivel != null) {
            qntdisponivel++;
        }
    }

    public boolean isDisponivel() {
        return qntdisponivel != null && qntdisponivel > 0;
    }

    public void exibirDetalhes() {
        System.out.println("Titulo: " + titulo);
        System.out.println("Editora: " + editora);
        System.out.println("Quantidade Disponivel: " + qntdisponivel);
    }

    // Getters e Setters
    public String getTitulo() { return titulo; }

    public void setTitulo(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("Título não pode ser vazio ou nulo");
        }
        this.titulo = titulo;
    }

    public Integer getQntpg() { return qntpg; }

    public void setQntpg(Integer qntpg) {
        if (qntpg == null || qntpg <= 0) {
            throw new IllegalArgumentException("Quantidade de página deve ser maior que 0");
        }
        this.qntpg = qntpg;
    }

    public String getGenero() { return genero; }

    public void setGenero(String genero) {
        if (genero == null || genero.trim().isEmpty()) {
            throw new IllegalArgumentException("Não pode deixar o gênero vazio");
        }
        this.genero = genero;
    }

    public String getEditora() { return editora; }

    public void setEditora(String editora) {
        if (editora == null || editora.trim().isEmpty()) {
            throw new IllegalArgumentException("Editora não pode ser vazio ou nulo");
        }
        this.editora = editora;
    }

    public Integer getQntdisponivel() { return qntdisponivel; }

    public void setQntdisponivel(Integer qntdisponivel) {
        if (qntdisponivel == null || qntdisponivel < 0) {
            throw new IllegalArgumentException("A quantidade no Estoque não pode ser menor que 0");
        }
        this.qntdisponivel = qntdisponivel;
    }

    public Integer getClassificacaoIndicativa() { return classificacaoIndicativa; }

    public void setClassificacaoIndicativa(Integer classificacaoIndicativa) {
        if (classificacaoIndicativa == null || classificacaoIndicativa < 0) {
            throw new IllegalArgumentException("Classificação indicativa não pode ser menor que 0");
        }
        this.classificacaoIndicativa = classificacaoIndicativa;
    }
}