package entitties;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Biblioteca {
    private List<ItemBiblioteca> itens;
    private List<Usuario> usuarios;
    private List<Emprestimo> emprestimos;
    private Queue<Usuario> filaReservas;
    private ItemBiblioteca itemReservado;

    public Biblioteca() {
        this.itens = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.emprestimos = new ArrayList<>();
        this.filaReservas = new LinkedList<>();
    }

    // Cadastro de itens
    public void cadastrarItem(ItemBiblioteca item) {
        itens.add(item);
        System.out.println("Item cadastrado: " + item.getTitulo());
    }

    public ItemBiblioteca buscarItem(String titulo) {
        for (ItemBiblioteca item : itens) {
            if (item.getTitulo().equalsIgnoreCase(titulo)) {
                return item;
            }
        }
        return null;
    }

    public void listarTodosItens() {
        System.out.println("\nTodos os Itens");
        for (ItemBiblioteca item : itens) {
            item.exibirDetalhes();
        }
    }

    public void listarLivrosDisponiveis() {
        System.out.println("\nLivros Disponiveis");
        for (ItemBiblioteca item : itens) {
            if (item instanceof Livro && item.isDisponivel()) {
                item.exibirDetalhes();
            }
        }
    }

    // Filtros por tipo (Exercício 6)
    public void listarApenasLivros() {
        System.out.println("\nSó Livros");
        for (ItemBiblioteca item : itens) {
            if (item instanceof Livro) {
                item.exibirDetalhes();
            }
        }
    }

    public void listarApenasRevistas() {
        System.out.println("\nSó Revistas");
        for (ItemBiblioteca item : itens) {
            if (item instanceof Revista) {
                item.exibirDetalhes();
            }
        }
    }

    public void listarApenasTeses() {
        System.out.println("\nSó Teses");
        for (ItemBiblioteca item : itens) {
            if (item instanceof Tese) {
                item.exibirDetalhes();
            }
        }
    }

    public void listarItensIndisponiveis() {
        System.out.println("\n=== ITENS INDISPONIVEIS ===");
        boolean temIndisponivel = false;
        for (ItemBiblioteca item : itens) {
            if (!item.isDisponivel()) {
                System.out.println("  - " + item.getTitulo());
                temIndisponivel = true;
            }
        }
        if (!temIndisponivel) {
            System.out.println("  Nenhum item indisponivel no momento.");
        }
    }

    // Cadastro de usuários
    public void cadastrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
        System.out.println("Usuário cadastrado: " + usuario.getNome());
    }

    public Usuario buscarUsuario(String registro) {
        for (Usuario u : usuarios) {
            if (u.getRegistro().equals(registro)) {
                return u;
            }
        }
        return null;
    }

    public void listarUsuarios() {
        System.out.println("\nUsuarios Cadastrados");
        for (Usuario u : usuarios) {
            System.out.println(u);
        }
    }

    // Empréstimo
    public boolean realizarEmprestimo(Usuario usuario, ItemBiblioteca item, int diasParaDevolucao) {
        if (!item.isDisponivel()) {
            System.out.println("Item indisponível: " + item.getTitulo());
            return false;
        }

        if (!usuario.podeEmprestar()) {
            System.out.println("Usuário " + usuario.getNome() + " atingiu o limite de empréstimos!");
            return false;
        }

        item.emprestar();
        Emprestimo emprestimo = new Emprestimo(usuario, item, LocalDate.now(), diasParaDevolucao);
        emprestimos.add(emprestimo);
        usuario.adicionarEmprestimo(emprestimo);

        System.out.println("\nEmpréstimo realizado com sucesso!");
        System.out.println(" Item: " + item.getTitulo());
        System.out.println(" Usuário: " + usuario.getNome());
        System.out.println(" Devolver até: " + emprestimo.getDataPrevistaDevolucao());

        return true;
    }

    // Devolução
    public boolean realizarDevolucao(Usuario usuario, ItemBiblioteca item, LocalDate dataDevolucao) {
        Emprestimo emprestimoAtivo = null;

        for (Emprestimo e : emprestimos) {
            if (e.isAtivo() && e.getUsuario().equals(usuario) && e.getItem().equals(item)) {
                emprestimoAtivo = e;
                break;
            }
        }

        if (emprestimoAtivo == null) {
            System.out.println("Empréstimo não encontrado!");
            return false;
        }

        emprestimoAtivo.finalizarEmprestimo(dataDevolucao);
        double multa = emprestimoAtivo.getMulta();

        System.out.println("\n Devolução realizada com sucesso!");
        if (multa > 0) {
            System.out.println(" Multa por atraso: R$ " + multa);
        }

        // Verificar fila de reserva
        if (!filaReservas.isEmpty() && item.equals(itemReservado)) {
            Usuario proximo = filaReservas.poll();
            System.out.println(" Próximo da fila de reserva: " + proximo.getNome());
        }

        return true;
    }

    // Fila de reserva (Exercício 7)
    public void adicionarReserva(ItemBiblioteca item, Usuario usuario) {
        if (item.isDisponivel()) {
            System.out.println("Item disponível, não é necessário reserva!");
            return;
        }

        if (itemReservado == null || !itemReservado.equals(item)) {
            itemReservado = item;
            filaReservas.clear();
        }

        filaReservas.add(usuario);
        System.out.println("Usuário " + usuario.getNome() + " adicionado à fila de reserva de " + item.getTitulo());
    }

    // Relatórios
    public void listarEmprestimosAtivos() {
        System.out.println("\nEmprestimos ativos");
        boolean temAtivos = false;
        for (Emprestimo e : emprestimos) {
            if (e.isAtivo()) {
                e.exibirDetalhes();
                temAtivos = true;
            }
        }
        if (!temAtivos) {
            System.out.println("Nenhum empréstimo ativo no momento.");
        }
    }

    public void listarEmprestimosFinalizados() {
        System.out.println("\nEmprestimos Finalizados");
        for (Emprestimo e : emprestimos) {
            if (!e.isAtivo()) {
                e.exibirDetalhes();
            }
        }
    }

    public void listarEmprestimosPorUsuario(Usuario usuario) {
        System.out.println("\nEmprestimo de: " + usuario.getNome().toUpperCase() + " ");
        for (Emprestimo e : emprestimos) {
            if (e.getUsuario().equals(usuario)) {
                e.exibirDetalhes();
            }
        }
    }
}