package br.com.easymoto.controller.web;


import br.com.easymoto.dto.LocalizacaoRequest;
import br.com.easymoto.service.LocalizacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/web/localizacoes")
public class LocalizacaoWebController {
    @Autowired private LocalizacaoService localizacaoService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("localizacoes", localizacaoService.listar(null, Pageable.unpaged()).getContent());
        return "localizacoes/list";
    }

    @GetMapping("/novo")
    public String mostrarFormularioNovo(Model model) {
        model.addAttribute("localizacaoRequest", new LocalizacaoRequest(null, LocalDateTime.now(), null, null, null));
        return "localizacoes/form";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("localizacaoRequest") LocalizacaoRequest request, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "localizacoes/form";
        }
        localizacaoService.salvar(request);
        redirectAttributes.addFlashAttribute("mensagem", "Localização salva com sucesso!");
        return "redirect:/web/localizacoes";
    }
}