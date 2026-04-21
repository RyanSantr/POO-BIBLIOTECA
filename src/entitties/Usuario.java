package entitties;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nome;
    private String registro;
    private LocalDate dataNasc;
    private List<Emprestimo> emprestimosAtivos;
    protected int limiteEmprestimos;

    public Usuario(String nome, String registro, LocalDate dataNasc) {
        this.nome = nome;
        this.registro = registro;
        this.dataNasc = dataNasc;
        this.emprestimosAtivos = new ArrayList<>();
        this.limiteEmprestimos = 3;
    }

    // Getters
    public String getNome() { return nome; }
    public String getRegistro() { return registro; }
    public LocalDate getDataNasc() { return dataNasc; }
    public List<Emprestimo> getEmprestimosAtivos() { return emprestimosAtivos; }
    public int getLimiteEmprestimos() { return limiteEmprestimos; }

    public boolean podeEmprestar() {
        return emprestimosAtivos.size() < limiteEmprestimos;
    }

    public void adicionarEmprestimo(Emprestimo emprestimo) {
        emprestimosAtivos.add(emprestimo);
    }

    public void removerEmprestimo(Emprestimo emprestimo) {
        emprestimosAtivos.remove(emprestimo);
    }

    public void listarEmprestimos() {
        System.out.println("Empréstimos ativos de " + nome + ":");
        for (Emprestimo e : emprestimosAtivos) {
            System.out.println("  - " + e.getItem().getTitulo());
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = dataNasc != null ? dataNasc.format(formatter) : "não informada";
        return "Nome: " + nome + "  Registro: " + registro + "  Nasc: " + dataFormatada +
                " Limite: " + limiteEmprestimos + " Empréstimos: " + emprestimosAtivos.size();
    }
}