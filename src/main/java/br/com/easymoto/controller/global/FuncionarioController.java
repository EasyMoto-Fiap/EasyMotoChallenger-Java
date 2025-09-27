package br.com.easymoto.controller.global;

import br.com.easymoto.dto.FuncionarioRequest;
import br.com.easymoto.dto.FuncionarioResponse;
import br.com.easymoto.service.FuncionarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/funcionarios")
@RequiredArgsConstructor
@Tag(name = "Funcionario", description = "Endpoints para gerenciamento de funcionários")
public class FuncionarioController {

    private final FuncionarioService service;

    //@GetMapping
    //public Page<FuncionarioResponse> listar(@RequestParam(required = false) String nome, Pageable pageable) {return service.listar(nome, pageable);}

    @GetMapping("/{id}")
    public FuncionarioResponse buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public FuncionarioResponse salvar(@RequestBody @Valid FuncionarioRequest request) {
        return service.salvar(request);
    }

    @PutMapping("/{id}")
    public FuncionarioResponse atualizar(@PathVariable Long id, @RequestBody @Valid FuncionarioRequest request) {
        return service.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
