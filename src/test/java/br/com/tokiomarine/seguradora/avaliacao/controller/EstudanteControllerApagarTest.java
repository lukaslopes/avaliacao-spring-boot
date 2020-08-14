package br.com.tokiomarine.seguradora.avaliacao.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import br.com.tokiomarine.seguradora.avaliacao.MockEstudante;
import br.com.tokiomarine.seguradora.avaliacao.entidade.Estudante;
import java.util.Collections;
import java.util.List;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class EstudanteControllerApagarTest extends EstudanteControllerBaseTest{


    @Test
    public void apagarUsuarioComSucesso() throws Exception {
        final Estudante estudante = MockEstudante.getEstudanteComId();
        final List<Estudante> estudantes = Collections.singletonList(estudante);

        doNothing().when(estudanteService).apagarEstudante(estudante.getId());
        when(estudanteService.buscarEstudantes()).thenReturn(estudantes);

        final MockHttpServletResponse response = mvc.perform(
                MockMvcRequestBuilders.get("/estudantes/apagar/1"))
                .andExpect(model().attribute("estudantes", Matchers.is(estudantes)))
                .andExpect(view().name("index"))
                .andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        verify(estudanteService, times(1)).buscarEstudantes();
        verify(estudanteService, times(1)).apagarEstudante(estudante.getId());
    }
}
