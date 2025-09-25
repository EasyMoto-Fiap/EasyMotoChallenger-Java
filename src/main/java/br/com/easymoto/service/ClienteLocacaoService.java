package br.com.easymoto.service;

import br.com.easymoto.dto.ClienteLocacaoRequest;
import br.com.easymoto.dto.ClienteLocacaoResponse;
import br.com.easymoto.enums.StatusLocacao;
import br.com.easymoto.model.Cliente;
import br.com.easymoto.model.ClienteLocacao;
import br.com.easymoto.repository.ClienteLocacaoRepository;
import br.com.easymoto.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ClienteLocacaoService {

    private final ClienteLocacaoRepository locacaoRepository;
    private final ClienteRepository clienteRepository;

    @Cacheable("locacoes")
    public Page<ClienteLocacaoResponse> listar(StatusLocacao status, LocalDate dataInicio, LocalDate dataFim, Pageable pageable) {
        if (status != null && dataInicio != null && dataFim != null) {
            return locacaoRepository.findByStatusLocacaoAndDataInicioBetween(status, dataInicio, dataFim, pageable).map(this::toResponse);
        } else if (status != null) {
            return locacaoRepository.findByStatusLocacao(status, pageable).map(this::toResponse);
        } else if (dataInicio != null && dataFim != null) {
            return locacaoRepository.findByDataInicioBetween(dataInicio, dataFim, pageable).map(this::toResponse);
        } else {
            return locacaoRepository.findAll(pageable).map(this::toResponse);
        }
    }

    public ClienteLocacaoResponse buscarPorId(Long id) {
        ClienteLocacao locacao = locacaoRepository.findById(id).orElseThrow();
        return toResponse(locacao);
    }

    @CacheEvict(value = "locacoes", allEntries = true)
    public ClienteLocacaoResponse salvar(ClienteLocacaoRequest dto) {
        Cliente cliente = clienteRepository.findById(dto.clienteId()).orElseThrow();
        ClienteLocacao locacao = new ClienteLocacao(
                null,
                dto.dataInicio(),
                dto.dataFim(),
                dto.statusLocacao(),
                cliente
        );
        return toResponse(locacaoRepository.save(locacao));
    }

    @CacheEvict(value = "locacoes", allEntries = true)
    public ClienteLocacaoResponse atualizar(Long id, ClienteLocacaoRequest dto) {
        ClienteLocacao locacao = locacaoRepository.findById(id).orElseThrow();
        Cliente cliente = clienteRepository.findById(dto.clienteId()).orElseThrow();
        locacao.setDataInicio(dto.dataInicio());
        locacao.setDataFim(dto.dataFim());
        locacao.setStatusLocacao(dto.statusLocacao());
        locacao.setCliente(cliente);
        return toResponse(locacaoRepository.save(locacao));
    }

    @CacheEvict(value = "locacoes", allEntries = true)
    public void deletar(Long id) {
        locacaoRepository.deleteById(id);
    }

    private ClienteLocacaoResponse toResponse(ClienteLocacao locacao) {
        return new ClienteLocacaoResponse(
                locacao.getId(),
                locacao.getDataInicio(),
                locacao.getDataFim(),
                locacao.getStatusLocacao(),
                locacao.getCliente().getId(),
                locacao.getCliente().getNomeCliente()
        );
    }
}
