package br.com.easymoto.controller.global;

import br.com.easymoto.dto.NoticiaRequest;
import br.com.easymoto.dto.NoticiaResponse;
import br.com.easymoto.service.NoticiaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/noticias")
@RequiredArgsConstructor
@Tag(name = "Noticia", description = "Endpoints para gerenciamento de notícias")
public class NoticiaController {

    private final NoticiaService noticiaService;

    @GetMapping
    public Page<NoticiaResponse> listar(Pageable pageable) {
        return noticiaService.listar(pageable);
    }

    @PostMapping
    public ResponseEntity<NoticiaResponse> salvar(@RequestBody @Valid NoticiaRequest request) {
        return new ResponseEntity<>(noticiaService.salvar(request), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        noticiaService.deletar(id);
    }
}