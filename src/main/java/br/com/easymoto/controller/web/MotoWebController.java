package br.com.easymoto.controller.web;

import br.com.easymoto.dto.MotoRequest;
import br.com.easymoto.repository.ClienteLocacaoRepository;
import br.com.easymoto.repository.LocalizacaoRepository;
import br.com.easymoto.service.MotoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/web/motos")
public class MotoWebController {

    @Autowired private MotoService motoService;
    @Autowired private ClienteLocacaoRepository locacaoRepository;
    @Autowired private LocalizacaoRepository localizacaoRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("motos", motoService.listar(null, Pageable.unpaged()).getContent());
        return "motos/list";
    }

    @GetMapping("/novo")
    public String mostrarFormularioNovo(Model model) {
        var motoRequest = new MotoRequest(null, null, null, null, null, null);
        model.addAttribute("motoRequest", motoRequest);
        model.addAttribute("locacoes", locacaoRepository.findAll());
        model.addAttribute("localizacoes", localizacaoRepository.findAll());
        return "motos/form";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("motoRequest") MotoRequest request, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("locacoes", locacaoRepository.findAll());
            model.addAttribute("localizacoes", localizacaoRepository.findAll());
            return "motos/form";
        }
        motoService.salvar(request);
        redirectAttributes.addFlashAttribute("mensagem", "Moto salva com sucesso!");
        return "redirect:/web/motos";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        motoService.deletar(id);
        redirectAttributes.addFlashAttribute("mensagem", "Moto deletada com sucesso!");
        return "redirect:/web/motos";
    }
}