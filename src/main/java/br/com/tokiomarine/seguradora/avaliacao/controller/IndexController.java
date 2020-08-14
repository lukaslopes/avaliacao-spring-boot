package br.com.tokiomarine.seguradora.avaliacao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class IndexController {

    @GetMapping("/")
    public String index(final Model model) {
        return "forward:estudantes/listar";
    }

}
