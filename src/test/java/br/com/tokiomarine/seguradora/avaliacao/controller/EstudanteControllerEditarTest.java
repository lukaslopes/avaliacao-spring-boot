package br.com.tokiomarine.seguradora.avaliacao.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import br.com.tokiomarine.seguradora.avaliacao.MockEstudante;
import br.com.tokiomarine.seguradora.avaliacao.entidade.Estudante;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class EstudanteControllerEditarTest extends EstudanteControllerBaseTest{

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

}
