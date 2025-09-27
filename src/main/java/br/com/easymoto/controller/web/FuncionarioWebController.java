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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/web/funcionarios")
@PreAuthorize("hasRole('ADMIN')")
public class FuncionarioWebController {

    @Autowired private FuncionarioService funcionarioService;
    @Autowired private FuncionarioRepository funcionarioRepository;
    @Autowired private FilialRepository filialRepository;


    @GetMapping("/novo")
    public String mostrarFormularioNovo(Model model) {
        model.addAttribute("funcionarioRequest", new FuncionarioRequest(null, null, null, null, null, "", null));
        model.addAttribute("filiais", filialRepository.findAll());
        model.addAttribute("cargos", TypeCargo.values());
        return "funcionarios/form";
    }

    @GetMapping
    public String listar(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) TypeCargo cargo,
            Model model,
            @PageableDefault(size = 10, sort = "nomeFunc") Pageable pageable) {

        Page<FuncionarioResponse> funcionariosPage = funcionarioService.listar(nome, cargo, pageable);
        model.addAttribute("funcionarios", funcionariosPage.getContent());
        model.addAttribute("cargos", TypeCargo.values());

        model.addAttribute("selectedNome", nome);
        model.addAttribute("selectedCargo", cargo);

        return "funcionarios/list";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("funcionarioRequest") FuncionarioRequest request, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("filiais", filialRepository.findAll());
            model.addAttribute("cargos", TypeCargo.values());
            return "funcionarios/form";
        }
        funcionarioService.salvar(request);
        redirectAttributes.addFlashAttribute("mensagem", "Funcionário cadastrado com sucesso!");
        return "redirect:/web/funcionarios";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Funcionario funcionario = funcionarioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Funcionário inválido:" + id));
        var request = new FuncionarioRequest(
                funcionario.getNomeFunc(), funcionario.getCpfFunc(), funcionario.getCargo(),
                funcionario.getTelefoneFunc(), funcionario.getEmailFunc(), "", funcionario.getFilial().getId()
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

        boolean isPasswordChanged = request.password() != null && !request.password().isEmpty();
        FuncionarioResponse funcionarioAtualizado = funcionarioService.atualizar(id, request);

        String successMessage = isPasswordChanged
                ? "Senha de " + funcionarioAtualizado.nomeFunc() + " atualizada com sucesso!"
                : "Dados de " + funcionarioAtualizado.nomeFunc() + " atualizados com sucesso!";

        redirectAttributes.addFlashAttribute("mensagem", successMessage);
        return "redirect:/web/funcionarios";
    }

    @GetMapping("/deletar/{id}")
    public String showDeleteConfirmation(@PathVariable Long id, RedirectAttributes redirectAttributes, Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Funcionario funcionarioLogado = funcionarioRepository.findByEmailFunc(userDetails.getUsername());

        if (funcionarioLogado != null && funcionarioLogado.getId().equals(id)) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Ação não permitida: Você não pode excluir seu próprio usuário.");
            return "redirect:/web/funcionarios";
        }

        Funcionario funcionarioParaDeletar = funcionarioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID inválido"));
        model.addAttribute("itemName", "Funcionário");
        model.addAttribute("itemDetails", funcionarioParaDeletar.getNomeFunc());
        model.addAttribute("deleteUrl", "/web/funcionarios/deletar/" + id);
        model.addAttribute("cancelUrl", "/web/funcionarios");
        return "delete-confirm";
    }

    @PostMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            funcionarioService.deletar(id);
            redirectAttributes.addFlashAttribute("mensagem", "Funcionário deletado com sucesso!");
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Não é possível excluir o funcionário, pois ele pode ter registros associados.");
        }
        return "redirect:/web/funcionarios";
    }
}