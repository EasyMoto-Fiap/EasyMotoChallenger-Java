package br.com.easymoto.controller.global;

import br.com.easymoto.dto.VagaRequest;
import br.com.easymoto.dto.VagaResponse;
import br.com.easymoto.service.VagaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vagas")
@RequiredArgsConstructor
@Tag(name = "Vaga", description = "Endpoints para gerenciamento de vagas")
public class VagaController {

    private final VagaService service;

    @GetMapping
    public Page<VagaResponse> listar(
            @RequestParam(required = false) String status,
            Pageable pageable) {
        return service.listar(status, pageable);
    }

    @GetMapping("/{id}")
    public VagaResponse buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public VagaResponse salvar(@RequestBody @Valid VagaRequest request) {
        return service.salvar(request);
    }

    @PutMapping("/{id}")
    public VagaResponse atualizar(@PathVariable Long id, @RequestBody @Valid VagaRequest request) {
        return service.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
