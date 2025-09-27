package br.com.easymoto.service;

import br.com.easymoto.dto.AuditoriaMotoRequest;
import br.com.easymoto.dto.AuditoriaMotoResponse;
import br.com.easymoto.exception.ResourceNotFoundException;
import br.com.easymoto.model.AuditoriaMoto;
import br.com.easymoto.repository.AuditoriaMotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuditoriaMotoService {

    private final AuditoriaMotoRepository auditoriaMotoRepository;

    public Page<AuditoriaMotoResponse> listar(String userName, String operacao, LocalDateTime dataInicio, LocalDateTime dataFim, Pageable pageable) {
        return auditoriaMotoRepository.findWithFilters(userName, operacao, dataInicio, dataFim, pageable)
                .map(this::toResponse);
    }

    public AuditoriaMotoResponse buscarPorId(Long id) {
        AuditoriaMoto auditoria = auditoriaMotoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Registro de Auditoria não encontrado com o ID: " + id));
        return toResponse(auditoria);
    }


    public AuditoriaMotoResponse salvar(AuditoriaMotoRequest dto) {
        AuditoriaMoto auditoria = new AuditoriaMoto();
        if (dto.id() != null) {
            auditoria = auditoriaMotoRepository.findById(dto.id()).orElseThrow(
                    () -> new ResourceNotFoundException("Registro de auditoria não encontrado para edição.")
            );
        } else {
            auditoria.setDataHora(LocalDateTime.now());
        }

        auditoria.setUserName(dto.userName());
        auditoria.setOperacao(dto.operacao());
        auditoria.setOldValues(dto.oldValues());
        auditoria.setNewValues(dto.newValues());

        return toResponse(auditoriaMotoRepository.save(auditoria));
    }


    private AuditoriaMotoResponse toResponse(AuditoriaMoto auditoria) {
        return new AuditoriaMotoResponse(
                auditoria.getId(),
                auditoria.getUserName(),
                auditoria.getOperacao(),
                auditoria.getDataHora(),
                auditoria.getOldValues(),
                auditoria.getNewValues()
        );
    }
}