package br.com.easymoto.controller.global;

import br.com.easymoto.dto.MotoRequest;
import br.com.easymoto.dto.MotoResponse;
import br.com.easymoto.service.MotoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/motos")
@RequiredArgsConstructor
@Tag(name = "Moto", description = "Endpoints para gerenciamento de motos")
public class MotoController {

    private final MotoService service;

    @GetMapping
    public Page<MotoResponse> listar(
            @RequestParam(required = false) String modelo,
            Pageable pageable) {
        return service.listar(modelo, pageable);
    }

    @GetMapping("/{id}")
    public MotoResponse buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public MotoResponse salvar(@RequestBody @Valid MotoRequest request) {
        return service.salvar(request);
    }

    @PutMapping("/{id}")
    public MotoResponse atualizar(@PathVariable Long id, @RequestBody @Valid MotoRequest request) {
        return service.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
