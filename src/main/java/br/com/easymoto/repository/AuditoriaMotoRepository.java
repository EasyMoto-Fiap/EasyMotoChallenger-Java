package br.com.easymoto.repository;

import br.com.easymoto.model.AuditoriaMoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditoriaMotoRepository extends JpaRepository<AuditoriaMoto, Long> {
}