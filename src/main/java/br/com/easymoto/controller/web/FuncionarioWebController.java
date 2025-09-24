package br.com.easymoto.controller.web;

import br.com.easymoto.dto.FuncionarioRequest;
import br.com.easymoto.dto.FuncionarioResponse;
import br.com.easymoto.enums.TypeCargo;
import br.com.easymoto.model.Funcionario;
import br.com.easymoto.repository.FilialRepository;
import br.com.easymoto.repository.FuncionarioRepository;
import br.com.easymoto.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/web/funcionarios")
@PreAuthorize("hasRole('ADMIN')") // Garante que apenas ADMINs acessem
public class FuncionarioWebController {

    @Autowired private FuncionarioService funcionarioService;
    @Autowired private FuncionarioRepository funcionarioRepository;
    @Autowired private FilialRepository filialRepository;

    @GetMapping
    public String listar(Model model) {
        // CORREÇÃO: Converte a lista de Funcionario para FuncionarioResponse
        var funcionariosResponse = funcionarioRepository.findAll().stream()
                .map(f -> new FuncionarioResponse(f.getId(), f.getNomeFunc(), f.getCpfFunc(), f.getCargo(), f.getTelefoneFunc(), f.getEmailFunc(), null, f.getFilial().getId(), f.getFilial().getNomeFilial()))
                .collect(Collectors.toList());
        model.addAttribute("funcionarios", funcionariosResponse);
        return "funcionarios/list";
    }

    @GetMapping("/novo")
    public String mostrarFormularioNovo(Model model) {
        var request = new FuncionarioRequest(null, null, null, null, null, "", null);
        model.addAttribute("funcionarioRequest", request);
        model.addAttribute("filiais", filialRepository.findAll());
        model.addAttribute("cargos", TypeCargo.values());
        return "funcionarios/form";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("funcionarioRequest") FuncionarioRequest request, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("filiais", filialRepository.findAll());
            model.addAttribute("cargos", TypeCargo.values());
            return "funcionarios/form";
        }
        funcionarioService.salvar(request);
        redirectAttributes.addFlashAttribute("mensagem", "Funcionário salvo com sucesso!");
        return "redirect:/web/funcionarios";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Funcionario funcionario = funcionarioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Funcionário inválido:" + id));
        var request = new FuncionarioRequest(
                funcionario.getNomeFunc(),
                funcionario.getCpfFunc(),
                funcionario.getCargo(),
                funcionario.getTelefoneFunc(),
                funcionario.getEmailFunc(),
                "", // Deixa a senha em branco por segurança
                funcionario.getFilial().getId()
        );
        model.addAttribute("funcionarioRequest", request);
        model.addAttribute("funcionarioId", id);
        model.addAttribute("filiais", filialRepository.findAll());
        model.addAttribute("cargos", TypeCargo.values());
        return "funcionarios/form";
    }

    @PostMapping("/atualizar/{id}")
    public String atualizar(@PathVariable Long id, @Valid @ModelAttribute("funcionarioRequest") FuncionarioRequest request, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("funcionarioId", id);
            model.addAttribute("filiais", filialRepository.findAll());
            model.addAttribute("cargos", TypeCargo.values());
            return "funcionarios/form";
        }
        funcionarioService.atualizar(id, request);
        redirectAttributes.addFlashAttribute("mensagem", "Funcionário atualizado com sucesso!");
        return "redirect:/web/funcionarios";
    }
}