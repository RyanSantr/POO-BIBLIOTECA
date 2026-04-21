package core;

import entitties.*;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Biblioteca biblioteca = new Biblioteca();
        int opcaoPrincipal = 0;

        System.out.println("=== SISTEMA DE BIBLIOTECA ===\n");

        try {
            // Inicialização de teste
            inicializarDados(biblioteca);
        } catch (Exception e) {
            System.out.println("Erro ao inicializar dados: " + e.getMessage());
        }

        // Menu principal
        do {
            System.out.println("\n MENU");
            System.out.println("1 - Usuarios");
            System.out.println("2 - Itens");
            System.out.println("3 - Emprestimo");
            System.out.println("4 - Devolucao");
            System.out.println("5 - Reserva");
            System.out.println("6 - Relatorios");
            System.out.println("0 - Sair");
            System.out.print("Opcao: ");

            try {
                opcaoPrincipal = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Digite um numero valido!");
                sc.nextLine();
                continue;
            }

            switch (opcaoPrincipal) {
                case 1:
                    menuUsuarios(sc, biblioteca);
                    break;
                case 2:
                    menuItens(sc, biblioteca);
                    break;
                case 3:
                    realizarEmprestimo(sc, biblioteca);
                    break;
                case 4:
                    realizarDevolucao(sc, biblioteca);
                    break;
                case 5:
                    adicionarReserva(sc, biblioteca);
                    break;
                case 6:
                    menuRelatorios(sc, biblioteca);
                    break;
                case 0:
                    System.out.println("Sistema encerrado.");
                    break;
                default:
                    System.out.println("Opcao invalida!");
            }

        } while (opcaoPrincipal != 0);

        sc.close();
    }

    // Inicializar dados de teste
    private static void inicializarDados(Biblioteca biblioteca) {
        System.out.println("Cadastrando itens...");

        try {
            Livro livro1 = new Livro("Java Completo", "Editora Tech", 3, "Herbert Schildt");
            Livro livro2 = new Livro("Clean Code", "Alta Books", 2, "Robert Martin");
            Livro livro3 = new Livro("Design Patterns", "Addison Wesley", 1, "Erich Gamma");
            Revista revista1 = new Revista("Revista Tech", "Editora Globo", 5, "Joao Silva", "Janeiro");
            Revista revista2 = new Revista("Super Interessante", "Editora Abril", 3, "Maria Santos", "Março");
            Tese tese1 = new Tese("IA na Educacao", "Editora Academica", 1, "Ana Paula", "Dr. Ricardo", "Tecnologia Educacional");

            biblioteca.cadastrarItem(livro1);
            biblioteca.cadastrarItem(livro2);
            biblioteca.cadastrarItem(livro3);
            biblioteca.cadastrarItem(revista1);
            biblioteca.cadastrarItem(revista2);
            biblioteca.cadastrarItem(tese1);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao cadastrar item: " + e.getMessage());
        }

        System.out.println("Cadastrando usuarios...");

        try {
            Aluno aluno1 = new Aluno("Joao Silva", "2024001", LocalDate.of(2000, 5, 15));
            Aluno aluno2 = new Aluno("Maria Souza", "2024002", LocalDate.of(2001, 8, 22));
            Professor prof1 = new Professor("Carlos Oliveira", "2024101", LocalDate.of(1980, 3, 10));
            ServidorAdministrativo serv1 = new ServidorAdministrativo("Ana Costa", "2024201", LocalDate.of(1990, 7, 19));

            biblioteca.cadastrarUsuario(aluno1);
            biblioteca.cadastrarUsuario(aluno2);
            biblioteca.cadastrarUsuario(prof1);
            biblioteca.cadastrarUsuario(serv1);
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar usuario: " + e.getMessage());
        }

        System.out.println("Sistema inicializado!\n");
    }

    // Menu Usuarios
    private static void menuUsuarios(Scanner sc, Biblioteca biblioteca) {
        int opcao = 0;
        do {
            System.out.println("\n--- MENU USUARIOS ---");
            System.out.println("1 - Cadastrar usuario");
            System.out.println("2 - Listar usuarios");
            System.out.println("3 - Buscar usuario");
            System.out.println("4 - Emprestimos do usuario");
            System.out.println("0 - Voltar");
            System.out.print("Opcao: ");

            try {
                opcao = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Digite um numero valido!");
                sc.nextLine();
                continue;
            }

            switch (opcao) {
                case 1:
                    cadastrarUsuario(sc, biblioteca);
                    break;
                case 2:
                    biblioteca.listarUsuarios();
                    break;
                case 3:
                    System.out.print("Registro: ");
                    String registro = sc.nextLine();
                    Usuario u = biblioteca.buscarUsuario(registro);
                    if (u != null) {
                        System.out.println(u);
                    } else {
                        System.out.println("Usuario nao encontrado!");
                    }
                    break;
                case 4:
                    System.out.print("Registro do usuario: ");
                    String reg = sc.nextLine();
                    Usuario user = biblioteca.buscarUsuario(reg);
                    if (user != null) {
                        biblioteca.listarEmprestimosPorUsuario(user);
                    } else {
                        System.out.println("Usuario nao encontrado!");
                    }
                    break;
                case 0:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("Opcao invalida!");
            }
        } while (opcao != 0);
    }

    // Menu Itens
    private static void menuItens(Scanner sc, Biblioteca biblioteca) {
        int opcao = 0;
        do {
            System.out.println("\n--- MENU ITENS ---");
            System.out.println("1 - Listar todos os itens");
            System.out.println("2 - Filtrar por tipo");
            System.out.println("3 - Livros disponiveis");
            System.out.println("0 - Voltar");
            System.out.print("Opcao: ");

            try {
                opcao = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Digite um numero valido!");
                sc.nextLine();
                continue;
            }

            switch (opcao) {
                case 1:
                    biblioteca.listarTodosItens();
                    break;
                case 2:
                    menuFiltros(sc, biblioteca);
                    break;
                case 3:
                    biblioteca.listarLivrosDisponiveis();
                    break;
                case 0:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("Opcao invalida!");
            }
        } while (opcao != 0);
    }

    // Menu de Filtros
    private static void menuFiltros(Scanner sc, Biblioteca biblioteca) {
        int opcao = 0;
        do {
            System.out.println("\n--- FILTRAR POR TIPO ---");
            System.out.println("1 - Livros");
            System.out.println("2 - Revistas");
            System.out.println("3 - Teses");
            System.out.println("0 - Voltar");
            System.out.print("Opcao: ");

            try {
                opcao = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Digite um numero valido!");
                sc.nextLine();
                continue;
            }

            switch (opcao) {
                case 1:
                    biblioteca.listarApenasLivros();
                    break;
                case 2:
                    biblioteca.listarApenasRevistas();
                    break;
                case 3:
                    biblioteca.listarApenasTeses();
                    break;
                case 0:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("Opcao invalida!");
            }
        } while (opcao != 0);
    }

    // Menu dos Relatorios
    private static void menuRelatorios(Scanner sc, Biblioteca biblioteca) {
        int opcao = 0;
        do {
            System.out.println("\n--- RELATORIOS ---");
            System.out.println("1 - Emprestimos ativos");
            System.out.println("2 - Emprestimos finalizados");
            System.out.println("3 - Itens indisponiveis");
            System.out.println("0 - Voltar");
            System.out.print("Opcao: ");

            try {
                opcao = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Digite um numero valido!");
                sc.nextLine();
                continue;
            }

            switch (opcao) {
                case 1:
                    biblioteca.listarEmprestimosAtivos();
                    break;
                case 2:
                    biblioteca.listarEmprestimosFinalizados();
                    break;
                case 3:
                    biblioteca.listarItensIndisponiveis();
                    break;
                case 0:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("Opcao invalida!");
            }
        } while (opcao != 0);
    }

    // Cadastrar de Usuario
    private static void cadastrarUsuario(Scanner sc, Biblioteca biblioteca) {
        System.out.println("\n--- CADASTRAR USUARIO ---");

        int tipo = 0;
        boolean tipoValido = false;
        while (!tipoValido) {
            try {
                System.out.print("Tipo (1-Aluno, 2-Professor, 3-Servidor): ");
                tipo = sc.nextInt();
                sc.nextLine();
                if (tipo >= 1 && tipo <= 3) {
                    tipoValido = true;
                } else {
                    System.out.println("Digite 1, 2 ou 3!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Digite um numero valido!");
                sc.nextLine();
            }
        }

        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Registro: ");
        String registro = sc.nextLine();

        // Validação da data de nascimento
        LocalDate dataNasc = null;
        boolean dataValida = false;
        while (!dataValida) {
            System.out.print("Data de Nascimento (AAAA-MM-DD): ");
            String dataStr = sc.nextLine();
            try {
                dataNasc = LocalDate.parse(dataStr);
                dataValida = true;
            } catch (Exception e) {
                System.out.println("Data invalida! Use o formato AAAA-MM-DD (exemplo: 1990-05-15)");
            }
        }

        Usuario usuario = null;

        try {
            switch (tipo) {
                case 1:
                    usuario = new Aluno(nome, registro, dataNasc);
                    System.out.println("Aluno cadastrado com limite de 3 emprestimos");
                    break;
                case 2:
                    usuario = new Professor(nome, registro, dataNasc);
                    System.out.println("Professor cadastrado com limite de 5 emprestimos");
                    break;
                case 3:
                    usuario = new ServidorAdministrativo(nome, registro, dataNasc);
                    System.out.println("Servidor cadastrado com limite de 4 emprestimos");
                    break;
            }
            biblioteca.cadastrarUsuario(usuario);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao cadastrar usuario: " + e.getMessage());
        }
    }

    // Realizar Emprestimo
    private static void realizarEmprestimo(Scanner sc, Biblioteca biblioteca) {
        System.out.println("\n--- REALIZAR EMPRESTIMO ---");

        System.out.print("Registro do usuario: ");
        String registro = sc.nextLine();
        Usuario usuario = biblioteca.buscarUsuario(registro);

        if (usuario == null) {
            System.out.println("Usuario nao encontrado!");
            return;
        }

        System.out.println("Usuario: " + usuario.getNome());

        System.out.print("Titulo do item: ");
        String titulo = sc.nextLine();
        ItemBiblioteca item = biblioteca.buscarItem(titulo);

        if (item == null) {
            System.out.println("Item nao encontrado!");
            return;
        }

        int dias = 0;
        boolean diasValido = false;
        while (!diasValido) {
            try {
                System.out.print("Dias para devolucao: ");
                dias = sc.nextInt();
                if (dias > 0) {
                    diasValido = true;
                } else {
                    System.out.println("Digite um numero maior que zero!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Digite um numero valido!");
                sc.nextLine();
            }
        }

        try {
            biblioteca.realizarEmprestimo(usuario, item, dias);
        } catch (Exception e) {
            System.out.println("Erro ao realizar emprestimo: " + e.getMessage());
        }
    }

    // Realizar Devolucao
    private static void realizarDevolucao(Scanner sc, Biblioteca biblioteca) {
        System.out.println("\n--- REALIZAR DEVOLUCAO ---");

        System.out.print("Registro do usuario: ");
        String registro = sc.nextLine();
        Usuario usuario = biblioteca.buscarUsuario(registro);

        if (usuario == null) {
            System.out.println("Usuario nao encontrado!");
            return;
        }

        System.out.print("Titulo do item: ");
        String titulo = sc.nextLine();
        ItemBiblioteca item = biblioteca.buscarItem(titulo);

        if (item == null) {
            System.out.println("Item nao encontrado!");
            return;
        }

        // Validação da data de devolução
        LocalDate dataDevolucao = null;
        boolean dataValida = false;

        while (!dataValida) {
            System.out.print("Data de devolucao (AAAA-MM-DD) - Enter para hoje: ");
            String dataStr = sc.nextLine();

            if (dataStr.isEmpty()) {
                dataDevolucao = LocalDate.now();
                dataValida = true;
            } else {
                try {
                    dataDevolucao = LocalDate.parse(dataStr);
                    dataValida = true;
                } catch (Exception e) {
                    System.out.println("Data invalida! Use o formato AAAA-MM-DD (exemplo: 2024-12-25)");
                }
            }
        }

        try {
            biblioteca.realizarDevolucao(usuario, item, dataDevolucao);
        } catch (Exception e) {
            System.out.println("Erro ao realizar devolucao: " + e.getMessage());
        }
    }

    // Adicionar a Reserva
    private static void adicionarReserva(Scanner sc, Biblioteca biblioteca) {
        System.out.println("\n--- ADICIONAR RESERVA ---");

        System.out.print("Registro do usuario: ");
        String registro = sc.nextLine();
        Usuario usuario = biblioteca.buscarUsuario(registro);

        if (usuario == null) {
            System.out.println("Usuario nao encontrado!");
            return;
        }

        System.out.print("Titulo do item: ");
        String titulo = sc.nextLine();
        ItemBiblioteca item = biblioteca.buscarItem(titulo);

        if (item == null) {
            System.out.println("Item nao encontrado!");
            return;
        }

        try {
            biblioteca.adicionarReserva(item, usuario);
        } catch (Exception e) {
            System.out.println("Erro ao adicionar reserva: " + e.getMessage());
        }
    }
}