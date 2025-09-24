package br.com.easymoto.controller.global;

import br.com.easymoto.dto.ClienteRequest;
import br.com.easymoto.dto.ClienteResponse;
import br.com.easymoto.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
@Tag(name = "Cliente", description = "Endpoints para gerenciamento de clientes")
public class ClienteController {

    private final ClienteService service;

    @GetMapping
    public Page<ClienteResponse> listar(
            @RequestParam(required = false) String nome,
            Pageable pageable) {
        return service.listar(nome, pageable);
    }

    @GetMapping("/{id}")
    public ClienteResponse buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public ClienteResponse salvar(@RequestBody @Valid ClienteRequest request) {
        return service.salvar(request);
    }

    @PutMapping("/{id}")
    public ClienteResponse atualizar(@PathVariable Long id, @RequestBody @Valid ClienteRequest request) {
        return service.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
