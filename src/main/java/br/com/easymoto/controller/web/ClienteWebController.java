package br.com.easymoto.controller.web;

import br.com.easymoto.model.Cliente;
import br.com.easymoto.repository.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/web/clientes")
public class ClienteWebController {

    @Autowired
    private ClienteRepository clienteRepository;

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
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de Cliente inválido:" + id));
        model.addAttribute("cliente", cliente);
        return "clientes/form";
    }

    @PostMapping("/salvar")
    public String salvarCliente(@Valid @ModelAttribute("cliente") Cliente cliente, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "clientes/form";
        }

        String mensagem = (cliente.getId() == null) ? "Cliente cadastrado com sucesso!" : "Cliente atualizado com sucesso!";
        clienteRepository.save(cliente);
        redirectAttributes.addFlashAttribute("mensagem", mensagem);
        return "redirect:/web/clientes";
    }

    @GetMapping("/deletar/{id}")
    public String deletarCliente(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        clienteRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("mensagem", "Cliente deletado com sucesso!");
        return "redirect:/web/clientes";
    }
}