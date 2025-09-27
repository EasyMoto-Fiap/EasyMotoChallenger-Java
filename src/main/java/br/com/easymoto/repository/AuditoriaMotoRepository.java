package br.com.easymoto.repository;

import br.com.easymoto.model.AuditoriaMoto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface AuditoriaMotoRepository extends JpaRepository<AuditoriaMoto, Long> {
    @Query("SELECT a FROM AuditoriaMoto a WHERE " +
            "(:userName IS NULL OR a.userName LIKE %:userName%) AND " +
            "(:operacao IS NULL OR a.operacao = :operacao) AND " +
            "(:dataInicio IS NULL OR a.dataHora >= :dataInicio) AND " +
            "(:dataFim IS NULL OR a.dataHora <= :dataFim)")
    Page<AuditoriaMoto> findWithFilters(
            @Param("userName") String userName,
            @Param("operacao") String operacao,
            @Param("dataInicio") LocalDateTime dataInicio,
            @Param("dataFim") LocalDateTime dataFim,
            Pageable pageable);
}