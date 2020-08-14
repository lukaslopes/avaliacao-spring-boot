package br.com.tokiomarine.seguradora.avaliacao.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.tokiomarine.seguradora.avaliacao.MockEstudante;
import br.com.tokiomarine.seguradora.avaliacao.entidade.Estudante;
import br.com.tokiomarine.seguradora.avaliacao.repository.EstudanteRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class EstudanteServiceTest {

    @MockBean
    EstudanteRepository repository;
    @Autowired
    private EstudanteService service;

    @Test
    public void shouldSaveEstudante() {
        final Estudante estudante = MockEstudante.getEstudante();

        when(repository.save(estudante)).thenReturn(null);

        service.cadastrarEstudante(estudante);

        verify(repository, times(1)).save(estudante);
    }

    @Test
    public void buscarEstudantes() {
        final Estudante estudante = MockEstudante.getEstudante();

        when(repository.findAll()).thenReturn(Collections.singletonList(estudante));

        final List<Estudante> estudantes = service.buscarEstudantes();

        assertEquals(estudantes.size(), 1);
        assertEquals(estudantes.get(0).getNome(), "Lukas");
        verify(repository, times(1)).findAll();
    }

    @Test
    public void buscarZeroEstudantes() {
        final Estudante estudante = MockEstudante.getEstudante();

        when(repository.findAll()).thenReturn(Collections.EMPTY_LIST);

        final List<Estudante> estudantes = service.buscarEstudantes();

        assertEquals(estudantes.size(), 0);
        verify(repository, times(1)).findAll();
    }

    @Test
    public void buscarEstudante() {
        final Estudante estudante = MockEstudante.getEstudanteComId();
        final Optional<Estudante> optionalEstudate = Optional.of(estudante);

        when(repository.findById(estudante.getId())).thenReturn(optionalEstudate);

        final Estudante resultEstudante = service.buscarEstudante(estudante.getId());

        assertNotNull(estudante);
        assertEquals(estudante, resultEstudante);
        verify(repository, times(1)).findById(estudante.getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void buscarEstudanteNaoExistente() {

        when(repository.findById(2l)).thenReturn(Optional.empty());

        service.buscarEstudante(2l);
    }

    @Test
    public void shouldAtualizarEstudante() {
        final Estudante estudante = MockEstudante.getEstudante();

        when(repository.save(estudante)).thenReturn(null);

        service.atualizarEstudante(estudante);

        verify(repository, times(1)).save(estudante);
    }

    @Test
    public void shouldDeletarEstudante() {

        doNothing().when(repository).deleteById(1l);

        service.apagarEstudante(1l);

        verify(repository, times(1)).deleteById(1l);
    }

}
