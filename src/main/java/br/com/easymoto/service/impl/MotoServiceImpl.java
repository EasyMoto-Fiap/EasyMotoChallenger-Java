package br.com.easymoto.service.impl;

import br.com.easymoto.dto.MotoRequest;
import br.com.easymoto.enums.StatusMoto;
import br.com.easymoto.model.ClienteLocacao;
import br.com.easymoto.model.Localizacao;
import br.com.easymoto.model.Moto;
import br.com.easymoto.repository.ClienteLocacaoRepository;
import br.com.easymoto.repository.LocalizacaoRepository;
import br.com.easymoto.repository.MotoRepository;
import br.com.easymoto.service.MotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MotoServiceImpl implements MotoService {

    private final MotoRepository motoRepository;
    private final ClienteLocacaoRepository clienteLocacaoRepository;
    private final LocalizacaoRepository localizacaoRepository;

    @Override
    public List<Moto> listarTodas() {
        return motoRepository.findAll();
    }

    @Override
    public Moto buscarPorId(Long id) {
        return motoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Moto não encontrada: " + id));
    }

    @Override
    @Transactional
    public void salvar(MotoRequest request) {
        String placaNormalizada = request.getPlaca() == null ? null : request.getPlaca().trim().toUpperCase();

        if (request.getId() == null && placaNormalizada != null && motoRepository.existsByPlaca(placaNormalizada)) {
            throw new DataIntegrityViolationException("Placa já cadastrada");
        }

        Moto moto = request.getId() == null ? new Moto() : buscarPorId(request.getId());
        moto.setPlaca(placaNormalizada);
        moto.setModelo(request.getModelo());
        moto.setAnoFabricacao(request.getAnoFabricacao());
        StatusMoto status = request.getStatus() == null ? StatusMoto.DISPONIVEL : request.getStatus();
        moto.setStatusMoto(status);

        if (request.getLocacaoId() != null) {
            ClienteLocacao locacao = clienteLocacaoRepository.findById(request.getLocacaoId())
                    .orElseThrow(() -> new IllegalArgumentException("Locação não encontrada: " + request.getLocacaoId()));
            moto.setLocacao(locacao);
        } else {
            moto.setLocacao(null);
        }

        if (request.getLocalizacaoId() != null) {
            Localizacao localizacao = localizacaoRepository.findById(request.getLocalizacaoId())
                    .orElseThrow(() -> new IllegalArgumentException("Localização não encontrada: " + request.getLocalizacaoId()));
            moto.setLocalizacao(localizacao);
        } else {
            moto.setLocalizacao(null);
        }

        motoRepository.save(moto);
    }

    @Override
    @Transactional
    public void excluir(Long id) {
        motoRepository.deleteById(id);
    }

    @Override
    public boolean existePlaca(String placa) {
        if (placa == null) return false;
        return motoRepository.existsByPlaca(placa.trim().toUpperCase());
    }
}
