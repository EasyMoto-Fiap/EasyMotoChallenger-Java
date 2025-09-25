package br.com.easymoto.controller.web;

import br.com.easymoto.dto.ClienteLocacaoRequest;
import br.com.easymoto.enums.StatusLocacao;
import br.com.easymoto.model.ClienteLocacao;
import br.com.easymoto.repository.ClienteLocacaoRepository;
import br.com.easymoto.repository.ClienteRepository;
import br.com.easymoto.service.ClienteLocacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@RequestMapping("/web/locacoes")
public class ClienteLocacaoWebController {

    @Autowired private ClienteLocacaoService locacaoService;
    @Autowired private ClienteLocacaoRepository locacaoRepository;
    @Autowired private ClienteRepository clienteRepository;

    @GetMapping
    public String listar(
            @RequestParam(required = false) StatusLocacao status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim,
            Model model) {

        model.addAttribute("locacoes", locacaoService.listar(status, dataInicio, dataFim, Pageable.unpaged()).getContent());
        model.addAttribute("statusOptions", StatusLocacao.values());
        model.addAttribute("selectedStatus", status);
        model.addAttribute("selectedDataInicio", dataInicio);
        model.addAttribute("selectedDataFim", dataFim);
        return "locacoes/list";
    }

    @GetMapping("/novo")
    public String mostrarFormularioNovo(Model model) {
        var request = new ClienteLocacaoRequest(LocalDate.now(), LocalDate.now().plusDays(7), StatusLocacao.ATIVA, null);
        model.addAttribute("locacaoRequest", request);
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("statusOptions", StatusLocacao.values());
        model.addAttribute("isEditMode", false);
        return "locacoes/form";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("locacaoRequest") ClienteLocacaoRequest request, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("clientes", clienteRepository.findAll());
            return "locacoes/form";
        }
        locacaoService.salvar(request);
        redirectAttributes.addFlashAttribute("mensagem", "Locação salva com sucesso!");
        return "redirect:/web/locacoes";
    }


    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        ClienteLocacao locacao = locacaoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de Locação inválido:" + id));

        var request = new ClienteLocacaoRequest(
                locacao.getDataInicio(),
                locacao.getDataFim(),
                locacao.getStatusLocacao(),
                locacao.getCliente().getId()
        );

        model.addAttribute("locacaoRequest", request);
        model.addAttribute("locacaoId", id);
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("isEditMode", true);
        return "locacoes/form";
    }

    @PostMapping("/atualizar/{id}")
    public String atualizar(@PathVariable Long id, @Valid @ModelAttribute("locacaoRequest") ClienteLocacaoRequest request, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("locacaoId", id);
            model.addAttribute("clientes", clienteRepository.findAll());
            model.addAttribute("isEditMode", true);
            return "locacoes/form";
        }
        locacaoService.atualizar(id, request);
        redirectAttributes.addFlashAttribute("mensagem", "Locação atualizada com sucesso!");
        return "redirect:/web/locacoes";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        locacaoService.deletar(id);
        redirectAttributes.addFlashAttribute("mensagem", "Locação deletada com sucesso!");
        return "redirect:/web/locacoes";
    }
}