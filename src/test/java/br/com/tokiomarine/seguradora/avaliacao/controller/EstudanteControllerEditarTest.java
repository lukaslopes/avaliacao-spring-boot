package br.com.tokiomarine.seguradora.avaliacao.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import br.com.tokiomarine.seguradora.avaliacao.MockEstudante;
import br.com.tokiomarine.seguradora.avaliacao.entidade.Estudante;
import java.util.Collections;
import java.util.List;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class EstudanteControllerEditarTest extends EstudanteControllerBaseTest {

    @Test
    public void buscarUsuarioPorId() throws Exception {
        final Estudante estudante = MockEstudante.getEstudanteComId();

        when(estudanteService.buscarEstudante(1)).thenReturn(estudante);

        final MockHttpServletResponse response = mvc.perform(
                MockMvcRequestBuilders.get("/estudantes/editar/1"))
                .andExpect(model().attribute("estudante", Matchers.is(estudante)))
                .andReturn().getResponse();

        assertEquals(response.getStatus(), HttpStatus.OK.value());
        verify(estudanteService, times(1)).buscarEstudante(1);
    }

    @Test
    public void atuzalizarUsuarioComSucesso() throws Exception {
        final Estudante estudante = MockEstudante.getEstudanteComId();
        final List<Estudante> estudantes = Collections.singletonList(estudante);

        estudante.setNome("Lukas 2");
        estudante.setEmail("Email 2");
        estudante.setTelefone("telefone 2");
        estudante.setCurso("Comp 2");
        estudante.setMatricula("matricula 2");

        doNothing().when(estudanteService).atualizarEstudante(estudante);
        when(estudanteService.buscarEstudantes()).thenReturn(estudantes);

        final MockHttpServletResponse response = mvc.perform(
                MockMvcRequestBuilders.post("/estudantes/atualizar/1")
                        .param("nome", "Lukas 2")
                        .param("email", "Email 2")
                        .param("telefone", "telefone 2")
                        .param("matricula", "matricula 2")
                        .param("curso", "Comp 2"))
                .andExpect(model().attribute("estudantes", Matchers.is(estudantes)))
                .andExpect(view().name("index"))
                .andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        verify(estudanteService, times(1)).buscarEstudantes();
        verify(estudanteService, times(1)).atualizarEstudante(estudante);
    }

    @Test
    public void atuzalizarUsuarioSemNome() throws Exception {
        final Estudante estudante = MockEstudante.getEstudanteComId();

        when(estudanteService.buscarEstudante(1)).thenReturn(estudante);

        final MockHttpServletResponse response = mvc.perform(
                MockMvcRequestBuilders.post("/estudantes/atualizar/1")
                        .param("nome", "Lukas 2")
                        .param("telefone", "telefone"))
                .andExpect(view().name("atualizar-estudante"))
                .andExpect(content().string(CoreMatchers.containsString("Email é obrigatório.")))
                .andReturn().getResponse();

        verify(estudanteService, never()).buscarEstudantes();
        verify(estudanteService, never()).atualizarEstudante(estudante);
    }

    @Test
    public void atuzalizarUsuarioSemMatricula() throws Exception {
        final Estudante estudante = MockEstudante.getEstudanteComId();

        when(estudanteService.buscarEstudante(1)).thenReturn(estudante);

        final MockHttpServletResponse response = mvc.perform(
                MockMvcRequestBuilders.post("/estudantes/atualizar/1")
                        .param("nome", "Lukas 2")
                        .param("email", "Email 2")
                        .param("telefone", "telefone"))
                .andExpect(view().name("atualizar-estudante"))
                .andExpect(content().string(CoreMatchers.containsString("Matricula é obrigatória.")))
                .andReturn().getResponse();

        verify(estudanteService, never()).buscarEstudantes();
        verify(estudanteService, never()).atualizarEstudante(estudante);
    }

}
