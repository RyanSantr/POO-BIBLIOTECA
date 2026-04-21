package entitties;

import java.time.LocalDate;

public class ServidorAdministrativo extends Usuario {
    public ServidorAdministrativo(String nome, String registro, LocalDate dataNasc) {
        super(nome, registro, dataNasc);
        this.limiteEmprestimos = 4;
    }
}