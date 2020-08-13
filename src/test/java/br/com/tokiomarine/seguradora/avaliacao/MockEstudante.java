package br.com.tokiomarine.seguradora.avaliacao;

import br.com.tokiomarine.seguradora.avaliacao.entidade.Estudante;

public class MockEstudante {

    public static  Estudante getEstudante() {
        final Estudante estudante = new Estudante();
        estudante.setEmail("email");
        estudante.setNome("Lukas");
        estudante.setTelefone("telefone");
        return estudante;
    }

    public static Estudante getEstudanteComId(){
        final Estudante estudante = getEstudante();
        estudante.setId(1L);
        return estudante;
    }
}
