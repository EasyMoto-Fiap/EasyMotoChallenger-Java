package br.com.easymoto.repository;

import br.com.easymoto.enums.StatusLocacao;
import br.com.easymoto.model.ClienteLocacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface ClienteLocacaoRepository extends JpaRepository<ClienteLocacao, Long> {
    Page<ClienteLocacao> findByStatusLocacao(StatusLocacao status, Pageable pageable);
    Page<ClienteLocacao> findByDataInicioBetween(LocalDate dataInicio, LocalDate dataFim, Pageable pageable);
    Page<ClienteLocacao> findByStatusLocacaoAndDataInicioBetween(StatusLocacao status, LocalDate dataInicio, LocalDate dataFim, Pageable pageable);}
