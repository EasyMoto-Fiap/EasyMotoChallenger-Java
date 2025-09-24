package br.com.easymoto.controller.global;

import br.com.easymoto.dto.FilialRequest;
import br.com.easymoto.dto.FilialResponse;
import br.com.easymoto.service.FilialService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/filiais")
@RequiredArgsConstructor
@Tag(name = "Filial", description = "Endpoints para gerenciamento de filiais")
public class FilialController {

    private final FilialService service;

    @GetMapping
    public Page<FilialResponse> listar(
            @RequestParam(required = false) String nome,
            Pageable pageable) {
        return service.listar(nome, pageable);
    }

    @GetMapping("/{id}")
    public FilialResponse buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public FilialResponse salvar(@RequestBody @Valid FilialRequest request) {
        return service.salvar(request);
    }

    @PutMapping("/{id}")
    public FilialResponse atualizar(@PathVariable Long id, @RequestBody @Valid FilialRequest request) {
        return service.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
