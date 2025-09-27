package br.com.easymoto.repository;

import br.com.easymoto.enums.TypeCargo;
import br.com.easymoto.model.Funcionario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Page<Funcionario> findByNomeFuncContainingIgnoreCase(String nomeFunc, Pageable pageable);
    boolean existsByCpfFunc(String cpfFunc);
    Funcionario findByEmailFunc(String email);
    Optional<Funcionario> findByResetPasswordToken(String token);


    @Query("SELECT f FROM Funcionario f WHERE " +
            "(:nome IS NULL OR LOWER(f.nomeFunc) LIKE LOWER(CONCAT('%', :nome, '%'))) AND " +
            "(:cargo IS NULL OR f.cargo = :cargo)")
    Page<Funcionario> findWithFilters(@Param("nome") String nome, @Param("cargo") TypeCargo cargo, Pageable pageable);
}
