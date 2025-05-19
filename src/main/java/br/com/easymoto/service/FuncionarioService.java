package br.com.easymoto.service;

import br.com.easymoto.dto.FuncionarioRequest;
import br.com.easymoto.dto.FuncionarioResponse;
import br.com.easymoto.model.Filial;
import br.com.easymoto.model.Funcionario;
import br.com.easymoto.repository.FilialRepository;
import br.com.easymoto.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final FilialRepository filialRepository;

    @Cacheable("funcionarios")
    public Page<FuncionarioResponse> listar(String nome, Pageable pageable) {
        if (nome != null && !nome.isEmpty()) {
            return funcionarioRepository.findByNomeFuncContainingIgnoreCase(nome, pageable)
                    .map(this::toResponse);
        } else {
            return funcionarioRepository.findAll(pageable)
                    .map(this::toResponse);
        }
    }

    public FuncionarioResponse buscarPorId(Long id) {
        Funcionario funcionario = funcionarioRepository.findById(id).orElseThrow();
        return toResponse(funcionario);
    }

    @CacheEvict(value = "funcionarios", allEntries = true)
    public FuncionarioResponse salvar(FuncionarioRequest dto) {
        Filial filial = filialRepository.findById(dto.filialId()).orElseThrow();
        Funcionario funcionario = new Funcionario(
                null,
                dto.nomeFunc(),
                dto.cpfFunc(),
                dto.cargo(),
                dto.telefoneFunc(),
                dto.emailFunc(),
                filial
        );
        return toResponse(funcionarioRepository.save(funcionario));
    }

    @CacheEvict(value = "funcionarios", allEntries = true)
    public FuncionarioResponse atualizar(Long id, FuncionarioRequest dto) {
        Funcionario funcionario = funcionarioRepository.findById(id).orElseThrow();
        Filial filial = filialRepository.findById(dto.filialId()).orElseThrow();
        funcionario.setNomeFunc(dto.nomeFunc());
        funcionario.setCpfFunc(dto.cpfFunc());
        funcionario.setCargo(dto.cargo());
        funcionario.setTelefoneFunc(dto.telefoneFunc());
        funcionario.setEmailFunc(dto.emailFunc());
        funcionario.setFilial(filial);
        return toResponse(funcionarioRepository.save(funcionario));
    }

    @CacheEvict(value = "funcionarios", allEntries = true)
    public void deletar(Long id) {
        funcionarioRepository.deleteById(id);
    }

    private FuncionarioResponse toResponse(Funcionario funcionario) {
        return new FuncionarioResponse(
                funcionario.getId(),
                funcionario.getNomeFunc(),
                funcionario.getCpfFunc(),
                funcionario.getCargo(),
                funcionario.getTelefoneFunc(),
                funcionario.getEmailFunc(),
                funcionario.getFilial().getId(),
                funcionario.getFilial().getNomeFilial()
        );
    }
}
