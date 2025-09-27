package br.com.easymoto.controller.web;

import br.com.easymoto.dto.AuditoriaMotoRequest;
import br.com.easymoto.model.AuditoriaMoto;
import br.com.easymoto.repository.AuditoriaMotoRepository;
import br.com.easymoto.service.AuditoriaMotoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/web/auditoria/motos")
@PreAuthorize("hasRole('ADMIN')")
public class AuditoriaWebController {

    @Autowired
    private AuditoriaMotoService auditoriaMotoService;

    @Autowired
    private AuditoriaMotoRepository auditoriaMotoRepository;

    @GetMapping
    public String listarAuditoriaMotos(
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) String operacao,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim,
            Model model,
            @PageableDefault(size = 10, sort = "dataHora", direction = org.springframework.data.domain.Sort.Direction.DESC) Pageable pageable) {

        model.addAttribute("auditorias", auditoriaMotoService.listar(userName, operacao, dataInicio, dataFim, pageable));
        model.addAttribute("userName", userName);
        model.addAttribute("operacao", operacao);
        model.addAttribute("dataInicio", dataInicio);
        model.addAttribute("dataFim", dataFim);
        return "auditoria/list";
    }

    @GetMapping("/view/{id}")
    public String mostrarDetalhes(@PathVariable Long id, Model model) {
        model.addAttribute("auditoria", auditoriaMotoService.buscarPorId(id));
        return "auditoria/details";
    }

    @GetMapping("/novo")
    public String mostrarFormularioNovo(Model model) {
        model.addAttribute("auditoriaRequest", new AuditoriaMotoRequest(null, "", "", "", ""));
        model.addAttribute("isEditMode", false);
        return "auditoria/form";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        AuditoriaMoto auditoria = auditoriaMotoRepository.findById(id).orElseThrow();
        var request = new AuditoriaMotoRequest(
                auditoria.getId(),
                auditoria.getUserName(),
                auditoria.getOperacao(),
                auditoria.getOldValues(),
                auditoria.getNewValues()
        );
        model.addAttribute("auditoriaRequest", request);
        model.addAttribute("isEditMode", true);
        return "auditoria/form";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("auditoriaRequest") AuditoriaMotoRequest request, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "auditoria/form";
        }
        auditoriaMotoService.salvar(request);
        redirectAttributes.addFlashAttribute("mensagem", "Registro de auditoria salvo com sucesso!");
        return "redirect:/web/auditoria/motos";
    }
}