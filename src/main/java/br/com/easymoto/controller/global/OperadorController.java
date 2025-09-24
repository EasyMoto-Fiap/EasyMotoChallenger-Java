package br.com.easymoto.controller.global;

import br.com.easymoto.dto.OperadorRequest;
import br.com.easymoto.dto.OperadorResponse;
import br.com.easymoto.service.OperadorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/operadores")
@RequiredArgsConstructor
@Tag(name = "Operador", description = "Endpoints para gerenciamento de operadores")
public class OperadorController {

    private final OperadorService service;

    @GetMapping
    public Page<OperadorResponse> listar(
            @RequestParam(required = false) String nome,
            Pageable pageable) {
        return service.listar(nome, pageable);
    }

    @GetMapping("/{id}")
    public OperadorResponse buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public OperadorResponse salvar(@RequestBody @Valid OperadorRequest request) {
        return service.salvar(request);
    }

    @PutMapping("/{id}")
    public OperadorResponse atualizar(@PathVariable Long id, @RequestBody @Valid OperadorRequest request) {
        return service.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
