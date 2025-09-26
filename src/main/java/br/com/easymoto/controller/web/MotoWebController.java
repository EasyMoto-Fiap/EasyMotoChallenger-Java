package br.com.easymoto.controller.web;

import br.com.easymoto.dto.MotoRequest;
import br.com.easymoto.enums.StatusMoto;
import br.com.easymoto.model.Moto;
import br.com.easymoto.repository.ClienteLocacaoRepository;
import br.com.easymoto.repository.LocalizacaoRepository;
import br.com.easymoto.repository.MotoRepository;
import br.com.easymoto.service.MotoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/web/motos")
public class MotoWebController {

    @Autowired private MotoService motoService;
    @Autowired private MotoRepository motoRepository;
    @Autowired private ClienteLocacaoRepository locacaoRepository;
    @Autowired private LocalizacaoRepository localizacaoRepository;

    @GetMapping
    public String listar(@RequestParam(required = false) StatusMoto status, Model model) {
        model.addAttribute("motos", motoService.listar(null, status, Pageable.unpaged()).getContent());
        model.addAttribute("statusOptions", StatusMoto.values());
        model.addAttribute("selectedStatus", status);
        return "motos/list";
    }

    @GetMapping("/novo")
    public String mostrarFormularioNovo(Model model) {
        var motoRequest = new MotoRequest(null, null, null, StatusMoto.DISPONIVEL, null, null);
        model.addAttribute("motoRequest", motoRequest);
        model.addAttribute("locacoes", locacaoRepository.findAll());
        model.addAttribute("localizacoes", localizacaoRepository.findAll());
        model.addAttribute("statusOptions", StatusMoto.values());
        model.addAttribute("isEditMode", false);
        return "motos/form";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("motoRequest") MotoRequest request, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("locacoes", locacaoRepository.findAll());
            model.addAttribute("localizacoes", localizacaoRepository.findAll());
            model.addAttribute("statusOptions", StatusMoto.values());
            return "motos/form";
        }
        motoService.salvar(request);
        redirectAttributes.addFlashAttribute("mensagem", "Moto salva com sucesso!");
        return "redirect:/web/motos";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Moto moto = motoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de Moto inválido:" + id));

        var motoRequest = new MotoRequest(
                moto.getPlaca(),
                moto.getModelo(),
                moto.getAnoFabricacao(),
                moto.getStatusMoto(),
                moto.getLocacao().getId(),
                moto.getLocalizacao().getId()
        );

        model.addAttribute("motoRequest", motoRequest);
        model.addAttribute("motoId", id);
        model.addAttribute("locacoes", locacaoRepository.findAll());
        model.addAttribute("localizacoes", localizacaoRepository.findAll());
        model.addAttribute("statusOptions", StatusMoto.values());
        model.addAttribute("isEditMode", true);
        return "motos/form";
    }

    @PostMapping("/atualizar/{id}")
    public String atualizar(@PathVariable Long id, @Valid @ModelAttribute("motoRequest") MotoRequest request, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("motoId", id);
            model.addAttribute("locacoes", locacaoRepository.findAll());
            model.addAttribute("localizacoes", localizacaoRepository.findAll());
            model.addAttribute("statusOptions", StatusMoto.values());
            model.addAttribute("isEditMode", true);
            return "motos/form";
        }
        motoService.atualizar(id, request);
        redirectAttributes.addFlashAttribute("mensagem", "Moto atualizada com sucesso!");
        return "redirect:/web/motos";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        motoService.deletar(id);
        redirectAttributes.addFlashAttribute("mensagem", "Moto deletada com sucesso!");
        return "redirect:/web/motos";
    }
}