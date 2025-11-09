package br.com.easymoto.controller.global;

import br.com.easymoto.dto.MotoRequest;
import br.com.easymoto.model.Moto;
import br.com.easymoto.service.MotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/motos")
@RequiredArgsConstructor
public class MotoController {

    private final MotoService service;

    @GetMapping
    public List<Moto> listar() {
        return service.listarTodas();
    }

    @GetMapping("/{id}")
    public Moto buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody MotoRequest request) {
        service.salvar(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody MotoRequest request) {
        request.setId(id);
        service.salvar(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
