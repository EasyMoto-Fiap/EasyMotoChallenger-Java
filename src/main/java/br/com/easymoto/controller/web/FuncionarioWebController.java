package br.com.easymoto.controller.web;

import br.com.easymoto.model.Funcionario;
import br.com.easymoto.repository.FilialRepository;
import br.com.easymoto.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/web/funcionarios")
public class FuncionarioWebController {

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private FilialRepository filialRepository;

    @GetMapping
    public String listarFuncionarios(Model model) {
        // Aqui seria melhor buscar uma lista e não uma Page, mas para simplificar:
        model.addAttribute("funcionarios", funcionarioService.listar(null, org.springframework.data.domain.Pageable.unpaged()).getContent());
        return "funcionarios/list";
    }

    @GetMapping("/novo")
    public String mostrarFormularioNovo(Model model) {
        model.addAttribute("funcionarioRequest", new br.com.easymoto.dto.FuncionarioRequest(null, null, null, null, null, null, null));
        model.addAttribute("filiais", filialRepository.findAll()); // Popula o dropdown
        model.addAttribute("cargos", br.com.easymoto.enums.TypeCargo.values());
        return "funcionarios/form";
    }

    @PostMapping("/salvar")
    public String salvarFuncionario(@Valid @ModelAttribute("funcionarioRequest") br.com.easymoto.dto.FuncionarioRequest funcionarioRequest, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("filiais", filialRepository.findAll());
            model.addAttribute("cargos", br.com.easymoto.enums.TypeCargo.values());
            return "funcionarios/form";
        }
        funcionarioService.salvar(funcionarioRequest);
        redirectAttributes.addFlashAttribute("mensagem", "Funcionário salvo com sucesso!");
        return "redirect:/web/funcionarios";
    }

    // ATENÇÃO: Métodos para Editar e Deletar precisariam de mais lógica,
    // como buscar o funcionário antes. Ficam como um próximo passo para você.
}