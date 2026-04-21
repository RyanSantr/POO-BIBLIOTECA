package entitties;

import java.time.LocalDate;

public class Professor extends Usuario {
    public Professor(String nome, String registro, LocalDate dataNasc) {
        super(nome, registro, dataNasc);
        this.limiteEmprestimos = 5;
    }
}