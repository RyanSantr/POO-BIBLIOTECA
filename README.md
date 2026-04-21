# Sistema de Biblioteca

Sistema simples para gerenciamento de biblioteca desenvolvido em Java.

## Funcionalidades

- Cadastro de usuários (Aluno, Professor, Servidor Administrativo)
- Cadastro de itens (Livro, Revista, Tese)
- Empréstimo e devolução de itens
- Cálculo automático de multa por atraso
- Fila de reserva para itens indisponíveis
- Relatórios diversos


## Classes Principais

| Classe | Descrição |
|--------|-----------|
| ItemBiblioteca | Classe base para todos os itens |
| Livro | Livros da biblioteca |
| Revista | Revistas da biblioteca |
| Tese | Teses acadêmicas |
| Usuario | Classe base para usuários |
| Aluno | Aluno com limite de 3 empréstimos |
| Professor | Professor com limite de 5 empréstimos |
| ServidorAdministrativo | Servidor com limite de 4 empréstimos |
| Emprestimo | Registro de empréstimos e multas |
| Biblioteca | Gerencia todos os recursos |

## Regras de Negócio

### Multa por atraso
- Até 0 dias: sem multa
- 1 a 3 dias: R$ 2 por dia
- Acima de 3 dias: R$ 5 por dia

### Limite de empréstimos
- Aluno: 3 itens
- Professor: 5 itens
- Servidor Administrativo: 4 itens

## Como Executar

1. Compilar:

```bash javac src/core/Main.java `

## MENU AO INICIAR

1 - Usuarios
2 - Itens
3 - Emprestimo
4 - Devolucao
5 - Reserva
6 - Relatorios
0 - Sair

## Tratamento de Erros
O sistema trata:

Entrada de dados inválida (letras ao invés de números)

Formato de data incorreto

Dados obrigatórios vazios

Itens indisponíveis para empréstimo

## AUTOR 

Feito por Ryan dos Santos - para Trabalho de POO.
