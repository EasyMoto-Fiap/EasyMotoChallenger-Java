package br.com.easymoto.controller.web;

import br.com.easymoto.repository.AuditoriaMotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web/auditoria")
@PreAuthorize("hasRole('ADMIN')")
public class AuditoriaWebController {

    @Autowired
    private AuditoriaMotoRepository auditoriaMotoRepository;

    @GetMapping("/motos")
    public String listarAuditoriaMotos(Model model) {
        model.addAttribute("auditorias", auditoriaMotoRepository.findAll(Sort.by(Sort.Direction.DESC, "dataHora")));
        return "auditoria/list_motos";
    }
}