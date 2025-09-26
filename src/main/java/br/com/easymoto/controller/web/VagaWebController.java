package br.com.easymoto.controller.web;

import br.com.easymoto.dto.VagaRequest;
import br.com.easymoto.enums.StatusVaga;
import br.com.easymoto.model.Vaga;
import br.com.easymoto.repository.MotoRepository;
import br.com.easymoto.repository.PatioRepository;
import br.com.easymoto.repository.VagaRepository;
import br.com.easymoto.service.VagaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/web/vagas")
public class VagaWebController {

    @Autowired private VagaService vagaService;
    @Autowired private VagaRepository vagaRepository;
    @Autowired private PatioRepository patioRepository;
    @Autowired private MotoRepository motoRepository;

    @GetMapping
    public String listar(@RequestParam(required = false) StatusVaga status, Model model) {
        model.addAttribute("vagas", vagaService.listar(status, Pageable.unpaged()).getContent());
        model.addAttribute("statusOptions", StatusVaga.values());
        model.addAttribute("selectedStatus", status);
        return "vagas/list";
    }

    @GetMapping("/novo")
    public String mostrarFormularioNovo(Model model) {
        model.addAttribute("vagaRequest", new VagaRequest(StatusVaga.LIVRE, null, null, null, null));
        model.addAttribute("patios", patioRepository.findAll());
        model.addAttribute("motos", motoRepository.findAll());
        model.addAttribute("statusOptions", StatusVaga.values());
        model.addAttribute("isEditMode", false);
        return "vagas/form";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("vagaRequest") VagaRequest request, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("patios", patioRepository.findAll());
            model.addAttribute("motos", motoRepository.findAll());
            model.addAttribute("statusOptions", StatusVaga.values());
            return "vagas/form";
        }
        vagaService.salvar(request);
        redirectAttributes.addFlashAttribute("mensagem", "Vaga salva com sucesso!");
        return "redirect:/web/vagas";
    }

    @GetMapping("/editar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Vaga vaga = vagaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de Vaga inválido:" + id));

        var request = new VagaRequest(
                vaga.getStatusVaga(),
                vaga.getPatio().getId(),
                vaga.getMoto().getId(),
                vaga.getFileira(),
                vaga.getColuna()
        );

        model.addAttribute("vagaRequest", request);
        model.addAttribute("vagaId", id);
        model.addAttribute("patios", patioRepository.findAll());
        model.addAttribute("motos", motoRepository.findAll());
        model.addAttribute("statusOptions", StatusVaga.values());
        model.addAttribute("isEditMode", true);
        return "vagas/form";
    }

    @PostMapping("/atualizar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String atualizar(@PathVariable Long id, @Valid @ModelAttribute("vagaRequest") VagaRequest request, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("vagaId", id);
            model.addAttribute("patios", patioRepository.findAll());
            model.addAttribute("motos", motoRepository.findAll());
            model.addAttribute("statusOptions", StatusVaga.values());
            model.addAttribute("isEditMode", true);
            return "vagas/form";
        }
        vagaService.atualizar(id, request);
        redirectAttributes.addFlashAttribute("mensagem", "Vaga atualizada com sucesso!");
        return "redirect:/web/vagas";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            vagaService.deletar(id);
            redirectAttributes.addFlashAttribute("mensagem", "Vaga deletada com sucesso!");
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Ocorreu um erro de integridade ao tentar excluir a vaga.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Ocorreu um erro ao tentar excluir a vaga.");
        }
        return "redirect:/web/vagas";
    }
}