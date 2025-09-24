package br.com.easymoto.controller.web;

import br.com.easymoto.dto.OperadorRequest;
import br.com.easymoto.repository.FilialRepository;
import br.com.easymoto.service.OperadorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/web/operadores")
public class OperadorWebController {
    @Autowired private OperadorService operadorService;
    @Autowired private FilialRepository filialRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("operadores", operadorService.listar(null, Pageable.unpaged()).getContent());
        return "operadores/list";
    }

    @GetMapping("/novo")
    public String mostrarFormularioNovo(Model model) {
        model.addAttribute("operadorRequest", new OperadorRequest(null, null, null, null, null));
        model.addAttribute("filiais", filialRepository.findAll());
        return "operadores/form";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("operadorRequest") OperadorRequest request, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("filiais", filialRepository.findAll());
            return "operadores/form";
        }
        operadorService.salvar(request);
        redirectAttributes.addFlashAttribute("mensagem", "Operador salvo com sucesso!");
        return "redirect:/web/operadores";
    }
}