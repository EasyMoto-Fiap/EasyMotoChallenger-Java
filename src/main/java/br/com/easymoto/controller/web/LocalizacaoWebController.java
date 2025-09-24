package br.com.easymoto.controller.web;

import br.com.easymoto.service.LocalizacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web/localizacoes")
@PreAuthorize("hasRole('ADMIN')")
public class LocalizacaoWebController {
    @Autowired private LocalizacaoService localizacaoService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("localizacoes", localizacaoService.listar(null, Pageable.unpaged()).getContent());
        return "localizacoes/list";
    }
}