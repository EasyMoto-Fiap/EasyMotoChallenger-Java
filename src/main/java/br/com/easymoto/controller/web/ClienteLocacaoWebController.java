package br.com.easymoto.controller.web;

import br.com.easymoto.dto.ClienteLocacaoRequest;
import br.com.easymoto.repository.ClienteRepository;
import br.com.easymoto.service.ClienteLocacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@RequestMapping("/web/locacoes")
public class ClienteLocacaoWebController {

    @Autowired private ClienteLocacaoService locacaoService;
    @Autowired private ClienteRepository clienteRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("locacoes", locacaoService.listar(null, Pageable.unpaged()).getContent());
        return "locacoes/list";
    }

    @GetMapping("/novo")
    public String mostrarFormularioNovo(Model model) {
        var request = new ClienteLocacaoRequest(LocalDate.now(), LocalDate.now().plusDays(7), null, null);
        model.addAttribute("locacaoRequest", request);
        model.addAttribute("clientes", clienteRepository.findAll());
        return "locacoes/form";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("locacaoRequest") ClienteLocacaoRequest request, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("clientes", clienteRepository.findAll());
            return "locacoes/form";
        }
        locacaoService.salvar(request);
        redirectAttributes.addFlashAttribute("mensagem", "Locação salva com sucesso!");
        return "redirect:/web/locacoes";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        locacaoService.deletar(id);
        redirectAttributes.addFlashAttribute("mensagem", "Locação deletada com sucesso!");
        return "redirect:/web/locacoes";
    }
}