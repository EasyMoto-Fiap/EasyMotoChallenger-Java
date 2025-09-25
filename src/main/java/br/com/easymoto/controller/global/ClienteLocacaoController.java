package br.com.easymoto.controller.global;

import br.com.easymoto.dto.ClienteLocacaoRequest;
import br.com.easymoto.dto.ClienteLocacaoResponse;
import br.com.easymoto.enums.StatusLocacao;
import br.com.easymoto.service.ClienteLocacaoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/locacoes")
@RequiredArgsConstructor
@Tag(name = "Locacao", description = "Endpoints para gerenciamento de locações de clientes")
public class ClienteLocacaoController {

    private final ClienteLocacaoService service;

    @GetMapping
    public Page<ClienteLocacaoResponse> listar(
            @RequestParam(required = false) StatusLocacao status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim,
            Pageable pageable) {
        return service.listar(status, dataInicio, dataFim, pageable);
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
