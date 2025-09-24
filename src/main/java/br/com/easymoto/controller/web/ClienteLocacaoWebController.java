package br.com.easymoto.controller.web;

import br.com.easymoto.repository.ClienteRepository;
import br.com.easymoto.service.ClienteLocacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web/locacoes")
public class ClienteLocacaoWebController {

    @Autowired private ClienteLocacaoService locacaoService;
    @Autowired private ClienteRepository clienteRepository;

    @GetMapping
    public String listarLocacoes(Model model) {
        model.addAttribute("locacoes", locacaoService.listar(null, Pageable.unpaged()).getContent());
        return "locacoes/list";
    }

    // Faltaria o /novo, /salvar, /editar, /deletar
}