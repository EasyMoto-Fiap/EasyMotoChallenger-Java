package br.com.easymoto.controller.global;

import br.com.easymoto.dto.PatioRequest;
import br.com.easymoto.dto.PatioResponse;
import br.com.easymoto.service.PatioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patios")
@RequiredArgsConstructor
@Tag(name = "Patio", description = "Endpoints para gerenciamento de pátios")
public class PatioController {

    private final PatioService service;

    @GetMapping
    public Page<PatioResponse> listar(
            @RequestParam(required = false) String nome,
            Pageable pageable) {
        return service.listar(nome, pageable);
    }

    @GetMapping("/{id}")
    public PatioResponse buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public PatioResponse salvar(@RequestBody @Valid PatioRequest request) {
        return service.salvar(request);
    }

    @PutMapping("/{id}")
    public PatioResponse atualizar(@PathVariable Long id, @RequestBody @Valid PatioRequest request) {
        return service.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
