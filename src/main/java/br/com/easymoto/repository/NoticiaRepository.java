package br.com.easymoto.repository;

import br.com.easymoto.model.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticiaRepository extends JpaRepository<Noticia, Long> {
}