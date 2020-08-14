package br.com.tokiomarine.seguradora.avaliacao.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import br.com.tokiomarine.seguradora.avaliacao.MockEstudante;
import br.com.tokiomarine.seguradora.avaliacao.entidade.Estudante;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class EstudanteControllerCadastroTest extends EstudanteControllerBaseTest{

    @Test
    public void shouldGetUser() throws Exception {
        final Estudante estudante = MockEstudante.getEstudante();

        doNothing().when(estudanteService).cadastrarEstudante(estudante);

        final MockHttpServletResponse response = mvc.perform(
                post("/estudantes/add")
                        .param("nome", "Lukas")
                        .param("email", "email")
                        .param("telefone", "telefone")
                        .param("matricula", "matricula")
                        .param("curso", "curso"))
                .andExpect(view().name("redirect:listar"))
                .andReturn().getResponse();

        assertEquals(response.getStatus(), HttpStatus.MOVED_TEMPORARILY.value());
        verify(estudanteService, times(1)).cadastrarEstudante(estudante);
    }

    @Test
    public void shouldFailMissingName() throws Exception {
        final Estudante estudante = MockEstudante.getEstudante();

        doNothing().when(estudanteService).cadastrarEstudante(estudante);

        final MockHttpServletResponse response = mvc.perform(
                post("/estudantes/add")
                        .param("email", "email")
                        .param("telefone", "telefone"))
                .andExpect(view().name("cadastrar-estudante"))
                .andExpect(content().string(CoreMatchers.containsString("Nome é obrigatório.")))
                .andReturn().getResponse();

        assertEquals(response.getStatus(), HttpStatus.OK.value());
        verify(estudanteService, never()).cadastrarEstudante(estudante);
    }

    @Test
    public void shouldFailMissingEmail() throws Exception {
        final Estudante estudante = MockEstudante.getEstudante();

        doNothing().when(estudanteService).cadastrarEstudante(estudante);

        final MockHttpServletResponse response = mvc.perform(
                MockMvcRequestBuilders.post("/estudantes/add")
                        .param("nome", "Lukas")
                        .param("telefone", "telefone"))
                .andExpect(view().name("cadastrar-estudante"))
                .andExpect(content().string(CoreMatchers.containsString("Email é obrigatório.")))
                .andReturn().getResponse();

        assertEquals(response.getStatus(), HttpStatus.OK.value());
        verify(estudanteService, never()).cadastrarEstudante(estudante);
    }

    @Test
    public void shouldFailMissingMatricula() throws Exception {
        final Estudante estudante = MockEstudante.getEstudante();

        doNothing().when(estudanteService).cadastrarEstudante(estudante);

        final MockHttpServletResponse response = mvc.perform(
                MockMvcRequestBuilders.post("/estudantes/add")
                        .param("nome", "Lukas")
                        .param("email", "email")
                        .param("telefone", "telefone"))
                .andExpect(view().name("cadastrar-estudante"))
                .andExpect(content().string(CoreMatchers.containsString("Matricula é obrigatória.")))
                .andReturn().getResponse();

        assertEquals(response.getStatus(), HttpStatus.OK.value());
        verify(estudanteService, never()).cadastrarEstudante(estudante);
    }
}
