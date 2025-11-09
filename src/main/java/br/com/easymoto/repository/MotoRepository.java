package br.com.easymoto.repository;

import br.com.easymoto.model.Moto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MotoRepository extends JpaRepository<Moto, Long> {
    boolean existsByPlaca(String placa);
}
