package entitties;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Emprestimo {
    private Usuario usuario;
    private ItemBiblioteca item;
    private LocalDate dataEmprestimo;
    private LocalDate dataPrevistaDevolucao;
    private LocalDate dataDevolucaoReal;
    private boolean ativo;
    private double multa;

    public Emprestimo(Usuario usuario, ItemBiblioteca item, LocalDate dataEmprestimo, int diasParaDevolucao) {
        this.usuario = usuario;
        this.item = item;
        this.dataEmprestimo = dataEmprestimo;
        this.dataPrevistaDevolucao = dataEmprestimo.plusDays(diasParaDevolucao);
        this.ativo = true;
        this.multa = 0.0;
    }

    public Usuario getUsuario() { return usuario; }
    public ItemBiblioteca getItem() { return item; }
    public LocalDate getDataEmprestimo() { return dataEmprestimo; }
    public LocalDate getDataPrevistaDevolucao() { return dataPrevistaDevolucao; }
    public boolean isAtivo() { return ativo; }
    public double getMulta() { return multa; }

    public double calcularMulta() {
        if (ativo || dataDevolucaoReal == null) {
            return 0;
        }

        long diasAtraso = ChronoUnit.DAYS.between(dataPrevistaDevolucao, dataDevolucaoReal);

        if (diasAtraso <= 0) {
            multa = 0.0;
        } else if (diasAtraso <= 3) {
            multa = diasAtraso * 2.0;
        } else {
            multa = diasAtraso * 5.0;
        }
        return multa;
    }

    public void finalizarEmprestimo(LocalDate dataDevolucao) {
        this.dataDevolucaoReal = dataDevolucao;
        this.ativo = false;
        calcularMulta();
        item.devolver();
        usuario.removerEmprestimo(this);
    }

    public void exibirDetalhes() {
        System.out.println("Empréstimo:");
        System.out.println("  Usuário: " + usuario.getNome());
        System.out.println("  Item: " + item.getTitulo());
        System.out.println("  Data empréstimo: " + dataEmprestimo);
        System.out.println("  Previsão devolução: " + dataPrevistaDevolucao);
        System.out.println("  Status: " + (ativo ? "ATIVO" : "FINALIZADO"));
        if (!ativo && multa > 0) {
            System.out.println("  Multa: R$ " + multa);
        }
    }
}