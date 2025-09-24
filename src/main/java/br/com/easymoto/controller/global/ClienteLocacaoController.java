package br.com.easymoto.controller.global;

import br.com.easymoto.dto.ClienteLocacaoRequest;
import br.com.easymoto.dto.ClienteLocacaoResponse;
import br.com.easymoto.service.ClienteLocacaoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/locacoes")
@RequiredArgsConstructor
@Tag(name = "Locacao", description = "Endpoints para gerenciamento de locações de clientes")
public class ClienteLocacaoController {

    private final ClienteLocacaoService service;

    @GetMapping
    public Page<ClienteLocacaoResponse> listar(
            @RequestParam(required = false) String status,
            Pageable pageable) {
        return service.listar(status, pageable);
    }

    @GetMapping("/{id}")
    public ClienteLocacaoResponse buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public ClienteLocacaoResponse salvar(@RequestBody @Valid ClienteLocacaoRequest request) {
        return service.salvar(request);
    }

    @PutMapping("/{id}")
    public ClienteLocacaoResponse atualizar(@PathVariable Long id, @RequestBody @Valid ClienteLocacaoRequest request) {
        return service.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
