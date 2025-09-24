package br.com.easymoto.controller.global;

import br.com.easymoto.dto.LocalizacaoRequest;
import br.com.easymoto.dto.LocalizacaoResponse;
import br.com.easymoto.service.LocalizacaoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/localizacoes")
@RequiredArgsConstructor
@Tag(name = "Localizacao", description = "Endpoints para gerenciamento de localizações")
public class LocalizacaoController {

    private final LocalizacaoService service;

    @GetMapping
    public Page<LocalizacaoResponse> listar(
            @RequestParam(required = false) String status,
            Pageable pageable) {
        return service.listar(status, pageable);
    }

    @GetMapping("/{id}")
    public LocalizacaoResponse buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public LocalizacaoResponse salvar(@RequestBody @Valid LocalizacaoRequest request) {
        return service.salvar(request);
    }

    @PutMapping("/{id}")
    public LocalizacaoResponse atualizar(@PathVariable Long id, @RequestBody @Valid LocalizacaoRequest request) {
        return service.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
