package br.com.tokiomarine.seguradora.avaliacao.service;

import java.util.List;

import java.util.Optional;
import javax.validation.Valid;

import br.com.tokiomarine.seguradora.avaliacao.entidade.Estudante;
import br.com.tokiomarine.seguradora.avaliacao.repository.EstudanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstudanteServiceImpl implements EstudanteService {

    @Autowired
	EstudanteRepository repository;

	@Override
	public void cadastrarEstudante(@Valid Estudante estudante) {
        repository.save(estudante);
	}

	@Override
	public void atualizarEstudante(@Valid Estudante estudante) {
        repository.save(estudante);
	}

	@Override
	public List<Estudante> buscarEstudantes() {
		return (List<Estudante>) repository.findAll();
	}

	@Override
	public Estudante buscarEstudante(final long id) {
	    final Optional<Estudante> result = repository.findById(id);

	    return result.orElseThrow(() -> new IllegalArgumentException("Identificador inválido:" + id));
	}

}
