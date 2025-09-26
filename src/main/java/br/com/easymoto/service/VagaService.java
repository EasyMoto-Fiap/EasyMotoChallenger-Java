package br.com.easymoto.service;

import br.com.easymoto.dto.VagaRequest;
import br.com.easymoto.dto.VagaResponse;
import br.com.easymoto.enums.StatusVaga;
import br.com.easymoto.model.Moto;
import br.com.easymoto.model.Patio;
import br.com.easymoto.model.Vaga;
import br.com.easymoto.repository.MotoRepository;
import br.com.easymoto.repository.PatioRepository;
import br.com.easymoto.repository.VagaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VagaService {

    private final VagaRepository vagaRepository;
    private final MotoRepository motoRepository;
    private final PatioRepository patioRepository;

    @Cacheable("vagas")
    public Page<VagaResponse> listar(StatusVaga status, Pageable pageable) {
        if (status != null) {
            return vagaRepository.findByStatusVaga(status, pageable)
                    .map(this::toResponse);
        } else {
            return vagaRepository.findAll(pageable)
                    .map(this::toResponse);
        }
    }

    public VagaResponse buscarPorId(Long id) {
        Vaga vaga = vagaRepository.findById(id).orElseThrow();
        return toResponse(vaga);
    }

    @CacheEvict(value = "vagas", allEntries = true)
    public VagaResponse salvar(VagaRequest dto) {
        Patio patio = patioRepository.findById(dto.patioId()).orElseThrow();
        Moto moto = motoRepository.findById(dto.motoId()).orElseThrow();
        Vaga vaga = new Vaga(
                null,
                dto.statusVaga(),
                patio,
                moto,
                dto.fileira(),
                dto.coluna()
        );
        return toResponse(vagaRepository.save(vaga));
    }

    @CacheEvict(value = "vagas", allEntries = true)
    public VagaResponse atualizar(Long id, VagaRequest dto) {
        Vaga vaga = vagaRepository.findById(id).orElseThrow();
        Patio patio = patioRepository.findById(dto.patioId()).orElseThrow();
        Moto moto = motoRepository.findById(dto.motoId()).orElseThrow();
        vaga.setStatusVaga(dto.statusVaga());
        vaga.setPatio(patio);
        vaga.setMoto(moto);
        vaga.setFileira(dto.fileira());
        vaga.setColuna(dto.coluna());
        return toResponse(vagaRepository.save(vaga));
    }

    @CacheEvict(value = "vagas", allEntries = true)
    public void deletar(Long id) {
        vagaRepository.deleteById(id);
    }

    private VagaResponse toResponse(Vaga vaga) {
        return new VagaResponse(
                vaga.getId(),
                vaga.getStatusVaga(),
                vaga.getPatio().getId(),
                vaga.getPatio().getNomePatio(),
                vaga.getMoto().getId(),
                vaga.getMoto().getPlaca(),
                vaga.getFileira(),
                vaga.getColuna()
        );
    }
}