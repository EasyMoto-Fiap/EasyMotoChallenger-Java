package br.com.easymoto.controller;

import br.com.easymoto.model.Cliente;
import br.com.easymoto.repository.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/web/clientes") // Usamos "/web" para não conflitar com a API
public class ClienteWebController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public String listarClientes(Model model) {
        model.addAttribute("clientes", clienteRepository.findAll());
        return "clientes/list"; // Aponta para o arquivo templates/clientes/list.html
    }

    @GetMapping("/novo")
    public String mostrarFormularioNovo(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "clientes/form"; // Aponta para templates/clientes/form.html
    }

    @PostMapping("/salvar")
    public String salvarCliente(@Valid @ModelAttribute("cliente") Cliente cliente, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "clientes/form";
        }
        clienteRepository.save(cliente);
        redirectAttributes.addFlashAttribute("mensagem", "Cliente salvo com sucesso!");
        return "redirect:/web/clientes";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Long id, Model model) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isEmpty()) {
            return "redirect:/web/clientes";
        }
        model.addAttribute("cliente", cliente.get());
        return "clientes/form";
    }

    @GetMapping("/deletar/{id}")
    public String deletarCliente(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        clienteRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("mensagem", "Cliente deletado com sucesso!");
        return "redirect:/web/clientes";
    }
}