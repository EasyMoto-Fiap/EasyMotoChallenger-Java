package br.com.easymoto.controller.web;

import br.com.easymoto.dto.NoticiaRequest;
import br.com.easymoto.enums.CategoriaNoticia;
import br.com.easymoto.model.Noticia;
import br.com.easymoto.repository.NoticiaRepository;
import br.com.easymoto.service.NoticiaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/web/noticias")
public class NoticiaWebController {

    @Autowired private NoticiaService noticiaService;
    @Autowired private NoticiaRepository noticiaRepository;

    @GetMapping
    public String listarNoticias(Model model) {
        model.addAttribute("noticias", noticiaRepository.findAll());
        return "noticias/list";
    }

    @GetMapping("/novo")
    @PreAuthorize("hasRole('ADMIN')")
    public String mostrarFormularioNovo(Model model) {
        model.addAttribute("noticiaRequest", new NoticiaRequest("", "", "", null));
        model.addAttribute("categorias", CategoriaNoticia.values());
        return "noticias/form";
    }

    @PostMapping("/salvar")
    @PreAuthorize("hasRole('ADMIN')")
    public String salvar(@Valid @ModelAttribute("noticiaRequest") NoticiaRequest request, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categorias", CategoriaNoticia.values());
            return "noticias/form";
        }
        noticiaService.salvar(request);
        redirectAttributes.addFlashAttribute("mensagem", "Notícia publicada com sucesso!");
        return "redirect:/web/noticias";
    }

    @GetMapping("/deletar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String showDeleteConfirmation(@PathVariable Long id, Model model) {
        Noticia noticia = noticiaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID inválido"));
        model.addAttribute("itemName", "Notícia");
        model.addAttribute("itemDetails", noticia.getTitulo());
        model.addAttribute("deleteUrl", "/web/noticias/deletar/" + id);
        model.addAttribute("cancelUrl", "/web/noticias");
        return "delete-confirm";
    }

    @PostMapping("/deletar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deletar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        noticiaService.deletar(id);
        redirectAttributes.addFlashAttribute("mensagem", "Notícia deletada com sucesso!");
        return "redirect:/web/noticias";
    }
}