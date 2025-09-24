package br.com.easymoto.controller.web;

import br.com.easymoto.dto.FilialRequest;
import br.com.easymoto.repository.EmpresaRepository;
import br.com.easymoto.service.FilialService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/web/filiais")
public class FilialWebController {
    @Autowired private FilialService filialService;
    @Autowired private EmpresaRepository empresaRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("filiais", filialService.listar(null, Pageable.unpaged()).getContent());
        return "filiais/list";
    }

    @GetMapping("/novo")
    public String mostrarFormularioNovo(Model model) {
        model.addAttribute("filialRequest", new FilialRequest(null, null, null, null, null, null));
        model.addAttribute("empresas", empresaRepository.findAll());
        return "filiais/form";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("filialRequest") FilialRequest request, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("empresas", empresaRepository.findAll());
            return "filiais/form";
        }
        filialService.salvar(request);
        redirectAttributes.addFlashAttribute("mensagem", "Filial salva com sucesso!");
        return "redirect:/web/filiais";
    }
}