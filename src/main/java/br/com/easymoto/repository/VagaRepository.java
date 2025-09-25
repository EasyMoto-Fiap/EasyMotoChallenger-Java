package br.com.easymoto.repository;

import br.com.easymoto.enums.StatusVaga;
import br.com.easymoto.model.Vaga;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VagaRepository extends JpaRepository<Vaga, Long> {
    Page<Vaga> findByStatusVagaContainingIgnoreCase(String statusVaga, Pageable pageable);
    Page<Vaga> findByStatusVaga(StatusVaga statusVaga, Pageable pageable);
}
