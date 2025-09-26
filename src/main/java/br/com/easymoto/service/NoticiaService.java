package br.com.easymoto.service;

import br.com.easymoto.dto.NoticiaRequest;
import br.com.easymoto.dto.NoticiaResponse;
import br.com.easymoto.exception.ResourceNotFoundException;
import br.com.easymoto.model.Noticia;
import br.com.easymoto.repository.NoticiaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class NoticiaService {

    private final NoticiaRepository noticiaRepository;

    public Page<NoticiaResponse> listar(Pageable pageable) {
        return noticiaRepository.findAll(pageable).map(this::toResponse);
    }

    public NoticiaResponse salvar(NoticiaRequest dto) {
        Noticia noticia = new Noticia();
        noticia.setTitulo(dto.titulo());
        noticia.setConteudo(dto.conteudo());
        noticia.setDataPublicacao(LocalDate.now());
        noticia.setAutor(dto.autor());
        noticia.setCategoria(dto.categoria());
        return toResponse(noticiaRepository.save(noticia));
    }

    public void deletar(Long id) {
        if (!noticiaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Notícia não encontrada com o ID: " + id);
        }
        noticiaRepository.deleteById(id);
    }

    private NoticiaResponse toResponse(Noticia noticia) {
        return new NoticiaResponse(
                noticia.getId(),
                noticia.getTitulo(),
                noticia.getConteudo(),
                noticia.getDataPublicacao(),
                noticia.getAutor(),
                noticia.getCategoria().getDisplayName()
        );
    }
}