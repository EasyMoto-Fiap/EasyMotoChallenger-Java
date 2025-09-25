package br.com.easymoto.service;

import br.com.easymoto.dto.MotoRequest;
import br.com.easymoto.dto.MotoResponse;
import br.com.easymoto.enums.StatusMoto;
import br.com.easymoto.model.ClienteLocacao;
import br.com.easymoto.model.Localizacao;
import br.com.easymoto.model.Moto;
import br.com.easymoto.repository.ClienteLocacaoRepository;
import br.com.easymoto.repository.LocalizacaoRepository;
import br.com.easymoto.repository.MotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MotoService {

    private final MotoRepository motoRepository;
    private final ClienteLocacaoRepository locacaoRepository;
    private final LocalizacaoRepository localizacaoRepository;

    @Cacheable("motos")
    public Page<MotoResponse> listar(String modelo, StatusMoto status, Pageable pageable) {
        if (status != null) {
            return motoRepository.findByStatusMoto(status, pageable).map(this::toResponse);
        }
        if (modelo != null && !modelo.isEmpty()) {
            return motoRepository.findByModeloContainingIgnoreCase(modelo, pageable)
                    .map(this::toResponse);
        }
        return motoRepository.findAll(pageable)
                .map(this::toResponse);
    }

    public MotoResponse buscarPorId(Long id) {
        Moto moto = motoRepository.findById(id).orElseThrow();
        return toResponse(moto);
    }

    @CacheEvict(value = "motos", allEntries = true)
    public MotoResponse salvar(MotoRequest dto) {
        ClienteLocacao locacao = locacaoRepository.findById(dto.locacaoId()).orElseThrow();
        Localizacao localizacao = localizacaoRepository.findById(dto.localizacaoId()).orElseThrow();
        Moto moto = new Moto(
                null,
                dto.placa(),
                dto.modelo(),
                dto.anoFabricacao(),
                dto.statusMoto(),
                locacao,
                localizacao
        );
        return toResponse(motoRepository.save(moto));
    }

    @CacheEvict(value = "motos", allEntries = true)
    public MotoResponse atualizar(Long id, MotoRequest dto) {
        Moto moto = motoRepository.findById(id).orElseThrow();
        ClienteLocacao locacao = locacaoRepository.findById(dto.locacaoId()).orElseThrow();
        Localizacao localizacao = localizacaoRepository.findById(dto.localizacaoId()).orElseThrow();
        moto.setPlaca(dto.placa());
        moto.setModelo(dto.modelo());
        moto.setAnoFabricacao(dto.anoFabricacao());
        moto.setStatusMoto(dto.statusMoto());
        moto.setLocacao(locacao);
        moto.setLocalizacao(localizacao);
        return toResponse(motoRepository.save(moto));
    }

    @CacheEvict(value = "motos", allEntries = true)
    public void deletar(Long id) {
        motoRepository.deleteById(id);
    }

    private MotoResponse toResponse(Moto moto) {
        return new MotoResponse(
                moto.getId(),
                moto.getPlaca(),
                moto.getModelo(),
                moto.getAnoFabricacao(),
                moto.getStatusMoto(),
                moto.getLocacao().getId(),
                moto.getLocalizacao().getId()
        );
    }
}
