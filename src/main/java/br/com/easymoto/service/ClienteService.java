package br.com.easymoto.service;

import br.com.easymoto.dto.ClienteRequest;
import br.com.easymoto.dto.ClienteResponse;
import br.com.easymoto.exception.ResourceNotFoundException;
import br.com.easymoto.model.Cliente;
import br.com.easymoto.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;

    @Cacheable("clientes")
    public Page<ClienteResponse> listar(String nome, Pageable pageable) {
        if (nome != null && !nome.isEmpty()) {
            return repository.findByNomeClienteContainingIgnoreCase(nome, pageable)
                    .map(this::toResponse);
        } else {
            return repository.findAll(pageable)
                    .map(this::toResponse);
        }
    }

    public ClienteResponse buscarPorId(Long id) {
        Cliente cliente = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com o ID: " + id));
        return toResponse(cliente);
    }

    @CacheEvict(value = "clientes", allEntries = true)
    public ClienteResponse salvar(ClienteRequest dto) {
        Cliente cliente = new Cliente(null,
                dto.nomeCliente(),
                dto.cpfCliente(),
                dto.telefoneCliente(),
                dto.emailCliente()
        );
        return toResponse(repository.save(cliente));
    }

    @CacheEvict(value = "clientes", allEntries = true)
    public ClienteResponse atualizar(Long id, ClienteRequest dto) {
        Cliente cliente = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Cliente não encontrado com o ID: " + id)
        );
        cliente.setNomeCliente(dto.nomeCliente());
        cliente.setCpfCliente(dto.cpfCliente());
        cliente.setTelefoneCliente(dto.telefoneCliente());
        cliente.setEmailCliente(dto.emailCliente());
        return toResponse(repository.save(cliente));
    }

    @CacheEvict(value = "clientes", allEntries = true)
    public void deletar(Long id) {
        repository.deleteById(id);
    }

    private ClienteResponse toResponse(Cliente cliente) {
        return new ClienteResponse(
                cliente.getId(),
                cliente.getNomeCliente(),
                cliente.getCpfCliente(),
                cliente.getTelefoneCliente(),
                cliente.getEmailCliente()
        );
    }
}
