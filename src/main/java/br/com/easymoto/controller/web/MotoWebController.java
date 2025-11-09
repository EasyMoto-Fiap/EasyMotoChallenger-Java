package br.com.easymoto.controller.web;

import br.com.easymoto.dto.MotoRequest;
import br.com.easymoto.enums.StatusMoto;
import br.com.easymoto.model.Moto;
import br.com.easymoto.repository.ClienteLocacaoRepository;
import br.com.easymoto.repository.LocalizacaoRepository;
import br.com.easymoto.service.MotoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/web/motos")
@RequiredArgsConstructor
public class MotoWebController {

    private final MotoService motoService;
    private final ClienteLocacaoRepository clienteLocacaoRepository;
    private final LocalizacaoRepository localizacaoRepository;

    @GetMapping
    public String listar(Model model) {
        List<Moto> motos = motoService.listarTodas();
        model.addAttribute("motos", motos);
        return "motos/list";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        MotoRequest motoRequest = new MotoRequest();
        motoRequest.setStatus(StatusMoto.DISPONIVEL);

        model.addAttribute("motoRequest", motoRequest);
        model.addAttribute("statusOptions", StatusMoto.values());
        model.addAttribute("locacoes", clienteLocacaoRepository.findAll());
        model.addAttribute("localizacoes", localizacaoRepository.findAll());
        model.addAttribute("isEditMode", false);
        return "motos/form";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Moto moto = motoService.buscarPorId(id);

        MotoRequest motoRequest = new MotoRequest();
        motoRequest.setId(moto.getId());
        motoRequest.setPlaca(moto.getPlaca());
        motoRequest.setModelo(moto.getModelo());
        motoRequest.setAnoFabricacao(moto.getAnoFabricacao());
        motoRequest.setStatus(moto.getStatusMoto());
        if (moto.getLocacao() != null) {
            motoRequest.setLocacaoId(moto.getLocacao().getId());
        }
        if (moto.getLocalizacao() != null) {
            motoRequest.setLocalizacaoId(moto.getLocalizacao().getId());
        }

        model.addAttribute("motoRequest", motoRequest);
        model.addAttribute("motoId", id);
        model.addAttribute("statusOptions", StatusMoto.values());
        model.addAttribute("locacoes", clienteLocacaoRepository.findAll());
        model.addAttribute("localizacoes", localizacaoRepository.findAll());
        model.addAttribute("isEditMode", true);
        return "motos/form";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("motoRequest") MotoRequest motoRequest,
                         BindingResult result,
                         RedirectAttributes redirectAttributes,
                         Model model) {

        if (result.hasErrors()) {
            model.addAttribute("statusOptions", StatusMoto.values());
            model.addAttribute("locacoes", clienteLocacaoRepository.findAll());
            model.addAttribute("localizacoes", localizacaoRepository.findAll());
            model.addAttribute("isEditMode", false);
            return "motos/form";
        }

        try {
            motoService.salvar(motoRequest);
            redirectAttributes.addFlashAttribute("mensagem", "Moto salva com sucesso.");
            return "redirect:/web/motos";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("mensagemErro", "Não foi possível salvar a moto. Verifique se a placa já está cadastrada.");
            model.addAttribute("statusOptions", StatusMoto.values());
            model.addAttribute("locacoes", clienteLocacaoRepository.findAll());
            model.addAttribute("localizacoes", localizacaoRepository.findAll());
            model.addAttribute("isEditMode", false);
            return "motos/form";
        }
    }

    @PostMapping("/atualizar/{id}")
    public String atualizar(@PathVariable Long id,
                            @Valid @ModelAttribute("motoRequest") MotoRequest motoRequest,
                            BindingResult result,
                            RedirectAttributes redirectAttributes,
                            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("motoId", id);
            model.addAttribute("statusOptions", StatusMoto.values());
            model.addAttribute("locacoes", clienteLocacaoRepository.findAll());
            model.addAttribute("localizacoes", localizacaoRepository.findAll());
            model.addAttribute("isEditMode", true);
            return "motos/form";
        }

        try {
            motoRequest.setId(id);
            motoService.salvar(motoRequest);
            redirectAttributes.addFlashAttribute("mensagem", "Moto atualizada com sucesso.");
            return "redirect:/web/motos";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("mensagemErro", "Não foi possível atualizar a moto. Verifique se a placa já está cadastrada.");
            model.addAttribute("motoId", id);
            model.addAttribute("statusOptions", StatusMoto.values());
            model.addAttribute("locacoes", clienteLocacaoRepository.findAll());
            model.addAttribute("localizacoes", localizacaoRepository.findAll());
            model.addAttribute("isEditMode", true);
            return "motos/form";
        }
    }

    @GetMapping("/deletar/{id}")
    public String showDeleteConfirmation(@PathVariable Long id, Model model) {
        Moto moto = motoService.buscarPorId(id);
        model.addAttribute("itemName", "Moto");
        model.addAttribute("itemDetails", "Placa " + moto.getPlaca() + " - " + moto.getModelo());
        model.addAttribute("deleteUrl", "/web/motos/deletar/" + id);
        model.addAttribute("cancelUrl", "/web/motos");
        return "delete-confirm";
    }

    @PostMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            motoService.excluir(id);
            redirectAttributes.addFlashAttribute("mensagem", "Moto excluída com sucesso.");
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute(
                    "mensagemErro",
                    "Não foi possível excluir a moto. Verifique se ela está associada a uma locação ou vaga."
            );
        }
        return "redirect:/web/motos";
    }
}
