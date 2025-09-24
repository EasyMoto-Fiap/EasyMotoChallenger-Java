package br.com.easymoto.controller.web;

import br.com.easymoto.repository.MotoRepository;
import br.com.easymoto.repository.PatioRepository;
import br.com.easymoto.service.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web/vagas")
public class VagaWebController {

    @Autowired private VagaService vagaService;
    @Autowired private PatioRepository patioRepository;
    @Autowired private MotoRepository motoRepository;

    @GetMapping
    public String listarVagas(Model model) {
        model.addAttribute("vagas", vagaService.listar(null, Pageable.unpaged()).getContent());
        return "vagas/list";
    }

    // Faltaria o /novo, /salvar, /editar, /deletar
}