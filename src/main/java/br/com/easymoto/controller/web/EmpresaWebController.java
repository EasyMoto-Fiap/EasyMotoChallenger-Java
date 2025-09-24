package br.com.easymoto.controller.web;

import br.com.easymoto.dto.EmpresaRequest;
import br.com.easymoto.service.EmpresaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/web/empresas")
public class EmpresaWebController {

    @Autowired private EmpresaService empresaService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("empresas", empresaService.listar(null, Pageable.unpaged()).getContent());
        return "empresas/list";
    }

    @GetMapping("/novo")
    public String mostrarFormularioNovo(Model model) {
        model.addAttribute("empresaRequest", new EmpresaRequest(null, null));
        return "empresas/form";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("empresaRequest") EmpresaRequest request, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "empresas/form";
        }
        empresaService.salvar(request);
        redirectAttributes.addFlashAttribute("mensagem", "Empresa salva com sucesso!");
        return "redirect:/web/empresas";
    }
}