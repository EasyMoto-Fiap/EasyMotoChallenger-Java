package br.com.easymoto.controller.web;

import br.com.easymoto.dto.PatioRequest;
import br.com.easymoto.repository.FilialRepository;
import br.com.easymoto.service.PatioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/web/patios")
public class PatioWebController {
    @Autowired private PatioService patioService;
    @Autowired private FilialRepository filialRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("patios", patioService.listar(null, Pageable.unpaged()).getContent());
        return "patios/list";
    }

    @GetMapping("/novo")
    public String mostrarFormularioNovo(Model model) {
        model.addAttribute("patioRequest", new PatioRequest(null, null, null, null));
        model.addAttribute("filiais", filialRepository.findAll());
        return "patios/form";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("patioRequest") PatioRequest request, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("filiais", filialRepository.findAll());
            return "patios/form";
        }
        patioService.salvar(request);
        redirectAttributes.addFlashAttribute("mensagem", "Pátio salvo com sucesso!");
        return "redirect:/web/patios";
    }
}