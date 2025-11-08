package br.com.easymoto.controller.web;

import br.com.easymoto.dto.MotoRequest;
import br.com.easymoto.enums.StatusMoto;
import br.com.easymoto.repository.ClienteLocacaoRepository;
import br.com.easymoto.repository.LocalizacaoRepository;
import br.com.easymoto.repository.MotoRepository;
import br.com.easymoto.service.MotoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/web/motos")
public class MotoWebController {

    @Autowired
    private MotoService motoService;

    @Autowired
    private MotoRepository motoRepository;

    @Autowired
    private ClienteLocacaoRepository locacaoRepository;

    @Autowired
    private LocalizacaoRepository localizacaoRepository;

    @GetMapping
    public String listar(@RequestParam(required = false) StatusMoto status, Model model) {
        model.addAttribute("motos", motoService.listar(null, status, Pageable.unpaged()).getContent());
        model.addAttribute("statusOptions", StatusMoto.values());
        model.addAttribute("selectedStatus", status);
        return "motos/list";
    }

    @GetMapping("/novo")
    public String mostrarFormularioNovo(Model model) {
        MotoRequest motoRequest = new MotoRequest(null, null, null, StatusMoto.DISPONIVEL, null, null);
        model.addAttribute("motoRequest", motoRequest);
        model.addAttribute("locacoes", locacaoRepository.findAll());
        model.addAttribute("localizacoes", localizacaoRepository.findAll());
        model.addAttribute("statusOptions", StatusMoto.values());
        model.addAttribute("isEditMode", false);
        return "motos/form";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("motoRequest") MotoRequest motoRequest,
                         BindingResult result,
                         RedirectAttributes redirectAttributes,
                         Model model) {
        if (result.hasErrors()) {
            model.addAttribute("locacoes", locacaoRepository.findAll());
            model.addAttribute("localizacoes", localizacaoRepository.findAll());
            model.addAttribute("statusOptions", StatusMoto.values());
            model.addAttribute("isEditMode", false);
            return "motos/form";
        }

        try {
            motoService.salvar(motoRequest);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Moto cadastrada com sucesso!");
            return "redirect:/web/motos";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("locacoes", locacaoRepository.findAll());
            model.addAttribute("localizacoes", localizacaoRepository.findAll());
            model.addAttribute("statusOptions", StatusMoto.values());
            model.addAttribute("isEditMode", false);
            model.addAttribute("mensagemErro", "Erro ao salvar a moto. Verifique se a placa já está cadastrada.");
            return "motos/form";
        }
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        var motoOptional = motoRepository.findById(id);
        if (motoOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Moto não encontrada.");
            return "redirect:/web/motos";
        }

        var moto = motoOptional.get();
        MotoRequest motoRequest = new MotoRequest(
                moto.getModelo(),
                moto.getPlaca(),
                moto.getAnoFabricacao(),
                moto.getStatusMoto(),
                moto.getLocacao() != null ? moto.getLocacao().getId() : null,
                moto.getLocalizacao() != null ? moto.getLocalizacao().getId() : null
        );

        model.addAttribute("moto", moto);
        model.addAttribute("motoRequest", motoRequest);
        model.addAttribute("locacoes", locacaoRepository.findAll());
        model.addAttribute("localizacoes", localizacaoRepository.findAll());
        model.addAttribute("statusOptions", StatusMoto.values());
        model.addAttribute("isEditMode", true);

        return "motos/form";
    }

    @PostMapping("/atualizar/{id}")
    public String atualizar(@PathVariable Long id,
                            @Valid @ModelAttribute("motoRequest") MotoRequest motoRequest,
                            BindingResult result,
                            RedirectAttributes redirectAttributes,
                            Model model) {
        if (result.hasErrors()) {
            var moto = motoRepository.findById(id).orElse(null);
            model.addAttribute("moto", moto);
            model.addAttribute("locacoes", locacaoRepository.findAll());
            model.addAttribute("localizacoes", localizacaoRepository.findAll());
            model.addAttribute("statusOptions", StatusMoto.values());
            model.addAttribute("isEditMode", true);
            return "motos/form";
        }

        try {
            motoService.atualizar(id, motoRequest);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Moto atualizada com sucesso!");
            return "redirect:/web/motos";
        } catch (DataIntegrityViolationException e) {
            var moto = motoRepository.findById(id).orElse(null);
            model.addAttribute("moto", moto);
            model.addAttribute("locacoes", locacaoRepository.findAll());
            model.addAttribute("localizacoes", localizacaoRepository.findAll());
            model.addAttribute("statusOptions", StatusMoto.values());
            model.addAttribute("isEditMode", true);
            model.addAttribute("mensagemErro", "Erro ao atualizar a moto. Verifique se a placa já está cadastrada.");
            return "motos/form";
        }
    }

    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            motoService.deletar(id);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Moto excluída com sucesso!");
        } catch (DataIntegrityViolationException ex) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Não foi possível excluir a moto, pois ela está associada a uma vaga.");
        }
        return "redirect:/web/motos";
    }
}
