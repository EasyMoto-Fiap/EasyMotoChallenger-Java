package br.com.easymoto.controller.global;

import br.com.easymoto.dto.AuditoriaMotoResponse;
import br.com.easymoto.service.AuditoriaMotoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/auditoria/motos")
@RequiredArgsConstructor
@Tag(name = "Auditoria", description = "Endpoints para consulta de logs de auditoria de motos")
public class AuditoriaController {

    private final AuditoriaMotoService service;

    @GetMapping
    public Page<AuditoriaMotoResponse> listar(
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) String operacao,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim,
            Pageable pageable) {
        return service.listar(userName, operacao, dataInicio, dataFim, pageable);
    }

    @GetMapping("/{id}")
    public AuditoriaMotoResponse buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }
}