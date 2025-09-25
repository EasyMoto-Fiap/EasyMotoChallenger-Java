package br.com.easymoto.repository;

import br.com.easymoto.enums.StatusMoto;
import br.com.easymoto.model.Moto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MotoRepository extends JpaRepository<Moto, Long> {
    Page<Moto> findByModeloContainingIgnoreCase(String modelo, Pageable pageable);
    Page<Moto> findByStatusMoto(StatusMoto status, Pageable pageable);
    boolean existsByPlaca(String placa);
}
