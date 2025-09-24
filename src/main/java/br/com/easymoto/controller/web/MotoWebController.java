package br.com.easymoto.controller.web;

import br.com.easymoto.dto.MotoRequest;
import br.com.easymoto.repository.ClienteLocacaoRepository;
import br.com.easymoto.repository.LocalizacaoRepository;
import br.com.easymoto.service.MotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web/motos")
public class MotoWebController {

    @Autowired private MotoService motoService;
    @Autowired private ClienteLocacaoRepository locacaoRepository;
    @Autowired private LocalizacaoRepository localizacaoRepository;

    @GetMapping
    public String listarMotos(Model model) {
        model.addAttribute("motos", motoService.listar(null, Pageable.unpaged()).getContent());
        return "motos/list";
    }

    @GetMapping("/novo")
    public String mostrarFormularioNovo(Model model) {
        // Objeto DTO vazio para o formulário
        var motoRequest = new MotoRequest(null, null, null, null, null, null);
        model.addAttribute("motoRequest", motoRequest);
        // Popula os dropdowns
        model.addAttribute("locacoes", locacaoRepository.findAll());
        model.addAttribute("localizacoes", localizacaoRepository.findAll());
        return "motos/form";
    }

    // Faltaria implementar o POST /salvar, GET /editar/{id}, GET /deletar/{id}
}