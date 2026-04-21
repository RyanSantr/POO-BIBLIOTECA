package entitties;

import java.time.LocalDate;

public class Aluno extends Usuario {
    public Aluno(String nome, String registro, LocalDate dataNasc) {
        super(nome, registro, dataNasc);
        this.limiteEmprestimos = 3;
    }
}