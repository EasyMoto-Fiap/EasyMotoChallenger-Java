package br.com.easymoto.controller.global;

import br.com.easymoto.dto.EmpresaRequest;
import br.com.easymoto.dto.EmpresaResponse;
import br.com.easymoto.service.EmpresaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/empresas")
@RequiredArgsConstructor
@Tag(name = "Empresa", description = "Endpoints para gerenciamento de empresas")
public class EmpresaController {

    private final EmpresaService service;

    @GetMapping
    public Page<EmpresaResponse> listar(
            @RequestParam(required = false) String nome,
            Pageable pageable) {
        return service.listar(nome, pageable);
    }

    @GetMapping("/{id}")
    public EmpresaResponse buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public EmpresaResponse salvar(@RequestBody @Valid EmpresaRequest request) {
        return service.salvar(request);
    }

    @PutMapping("/{id}")
    public EmpresaResponse atualizar(@PathVariable Long id, @RequestBody @Valid EmpresaRequest request) {
        return service.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
