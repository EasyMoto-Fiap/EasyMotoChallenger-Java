package br.com.easymoto.controller.web;

import br.com.easymoto.dto.VagaRequest;
import br.com.easymoto.repository.MotoRepository;
import br.com.easymoto.repository.PatioRepository;
import br.com.easymoto.service.VagaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/web/vagas")
public class VagaWebController {

    @Autowired private VagaService vagaService;
    @Autowired private PatioRepository patioRepository;
    @Autowired private MotoRepository motoRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("vagas", vagaService.listar(null, Pageable.unpaged()).getContent());
        return "vagas/list";
    }

    @GetMapping("/novo")
    public String mostrarFormularioNovo(Model model) {
        model.addAttribute("vagaRequest", new VagaRequest(null, null, null, null, null));
        model.addAttribute("patios", patioRepository.findAll());
        model.addAttribute("motos", motoRepository.findAll());
        return "vagas/form";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("vagaRequest") VagaRequest request, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("patios", patioRepository.findAll());
            model.addAttribute("motos", motoRepository.findAll());
            return "vagas/form";
        }
        vagaService.salvar(request);
        redirectAttributes.addFlashAttribute("mensagem", "Vaga salva com sucesso!");
        return "redirect:/web/vagas";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        vagaService.deletar(id);
        redirectAttributes.addFlashAttribute("mensagem", "Vaga deletada com sucesso!");
        return "redirect:/web/vagas";
    }
}