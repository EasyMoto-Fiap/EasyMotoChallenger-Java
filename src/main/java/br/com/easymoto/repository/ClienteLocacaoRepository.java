package br.com.easymoto.repository;

import br.com.easymoto.enums.StatusLocacao;
import br.com.easymoto.model.ClienteLocacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface ClienteLocacaoRepository extends JpaRepository<ClienteLocacao, Long> {
    Page<ClienteLocacao> findByStatusLocacao(StatusLocacao status, Pageable pageable);
    Page<ClienteLocacao> findByDataInicioBetween(LocalDate dataInicio, LocalDate dataFim, Pageable pageable);
    Page<ClienteLocacao> findByStatusLocacaoAndDataInicioBetween(StatusLocacao status, LocalDate dataInicio, LocalDate dataFim, Pageable pageable);

    @Query("SELECT loc FROM ClienteLocacao loc JOIN loc.cliente c WHERE " +
            "(:clienteNome IS NULL OR c.nomeCliente LIKE %:clienteNome%) AND " +
            "(:status IS NULL OR loc.statusLocacao = :status) AND " +
            "(:dataInicio IS NULL OR loc.dataInicio >= :dataInicio) AND " +
            "(:dataFim IS NULL OR loc.dataFim <= :dataFim)")
    Page<ClienteLocacao> findWithFilters(
            @Param("clienteNome") String clienteNome,
            @Param("status") StatusLocacao status,
            @Param("dataInicio") LocalDate dataInicio,
            @Param("dataFim") LocalDate dataFim,
            Pageable pageable);
}
