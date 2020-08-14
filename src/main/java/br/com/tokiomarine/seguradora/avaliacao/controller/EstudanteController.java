package br.com.tokiomarine.seguradora.avaliacao.controller;

import br.com.tokiomarine.seguradora.avaliacao.entidade.Estudante;
import br.com.tokiomarine.seguradora.avaliacao.service.EstudanteService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/estudantes/")
public class EstudanteController {

    @Autowired
    EstudanteService service;

    @GetMapping("criar")
    public String iniciarCastrado(Estudante estudante) {
        return "cadastrar-estudante";
    }


    @GetMapping("listar")
    public String listarEstudantes(final Model model) {
        model.addAttribute("estudantes", service.buscarEstudantes());
        return "index";
    }

    @PostMapping("add")
    public String adicionarEstudante(@Valid final Estudante estudante, final BindingResult result, final Model model) {
        if (result.hasErrors()) {
            return "cadastrar-estudante";
        }
        service.cadastrarEstudante(estudante);

        return "redirect:listar";
    }

    @GetMapping("editar/{id}")
    public String exibirEdicaoEstudante(@PathVariable final Long id, Model model) {
        Estudante estudante = service.buscarEstudante(id);
        model.addAttribute("estudante", estudante);
        return "atualizar-estudante";
    }

    @PostMapping("atualizar/{id}")
    public String atualizarEstudante(@PathVariable("id") final long id, @Valid final Estudante estudante,
            final BindingResult result, final Model model) {

        estudante.setId(id);
        if (result.hasErrors()) {
            return "atualizar-estudante";
        }

        service.atualizarEstudante(estudante);

        model.addAttribute("estudantes", service.buscarEstudantes());
        return "index";
    }

    @GetMapping("apagar/{id}")
    public String apagarEstudante(@PathVariable("id") final long id, Model model) {

        service.apagarEstudante(id);

        model.addAttribute("estudantes", service.buscarEstudantes());
        return "index";
    }
}
