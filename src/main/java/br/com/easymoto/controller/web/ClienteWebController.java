package br.com.easymoto.controller.web;

import br.com.easymoto.model.Cliente;
import br.com.easymoto.repository.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/web/clientes")
public class ClienteWebController {

    @Autowired private ClienteRepository clienteRepository;

    @GetMapping
    public String listarClientes(Model model) {
        model.addAttribute("clientes", clienteRepository.findAll());
        return "clientes/list";
    }

    @GetMapping("/novo")
    public String mostrarFormularioNovo(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "clientes/form";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Long id, Model model) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID de Cliente inválido:" + id));
        model.addAttribute("cliente", cliente);
        return "clientes/form";
    }

    @PostMapping("/salvar")
    public String salvarCliente(@Valid @ModelAttribute("cliente") Cliente cliente, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            return "clientes/form";
        }
        try {
            clienteRepository.save(cliente);
            redirectAttributes.addFlashAttribute("mensagem", "Cliente cadastrado com sucesso!");
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("mensagemErro", "Não foi possível salvar. O CPF informado já está cadastrado no sistema.");
            return "clientes/form";
        }
        return "redirect:/web/clientes";
    }

    @PostMapping("/atualizar/{id}")
    public String atualizarCliente(@PathVariable("id") Long id, @Valid @ModelAttribute("cliente") Cliente cliente, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            return "clientes/form";
        }
        try {
            cliente.setId(id); // Garante que o ID correto está sendo atualizado
            clienteRepository.save(cliente);
            redirectAttributes.addFlashAttribute("mensagem", "Cliente atualizado com sucesso!");
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("mensagemErro", "Não foi possível atualizar. Ocorreu um erro de integridade de dados.");
            return "clientes/form";
        }
        return "redirect:/web/clientes";
    }

    @GetMapping("/deletar/{id}")
    public String showDeleteConfirmation(@PathVariable("id") Long id, Model model) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID inválido"));
        model.addAttribute("itemName", "Cliente");
        model.addAttribute("itemDetails", cliente.getNomeCliente());
        model.addAttribute("deleteUrl", "/web/clientes/deletar/" + id);
        model.addAttribute("cancelUrl", "/web/clientes");
        return "delete-confirm";
    }

    @PostMapping("/deletar/{id}")
    public String deletarCliente(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            clienteRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("mensagem", "Cliente deletado com sucesso!");
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Não é possível excluir o cliente, pois ele está associado a uma ou mais locações.");
        }
        return "redirect:/web/clientes";
    }
}