package br.com.easymoto.service;

import br.com.easymoto.dto.FuncionarioRequest;
import br.com.easymoto.dto.FuncionarioResponse;
import br.com.easymoto.exception.InvalidPasswordException;
import br.com.easymoto.exception.ResourceNotFoundException;
import br.com.easymoto.model.Filial;
import br.com.easymoto.model.Funcionario;
import br.com.easymoto.repository.FilialRepository;
import br.com.easymoto.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final FilialRepository filialRepository;
    private final PasswordEncoder passwordEncoder; // Injete o encoder

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
        String encryptedPassword = passwordEncoder.encode(dto.password());
        Funcionario funcionario = new Funcionario();
        funcionario.setNomeFunc(dto.nomeFunc());
        funcionario.setCpfFunc(dto.cpfFunc());
        funcionario.setCargo(dto.cargo());
        funcionario.setTelefoneFunc(dto.telefoneFunc());
        funcionario.setEmailFunc(dto.emailFunc());
        funcionario.setPassword(encryptedPassword);
        funcionario.setFilial(filial);

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

        if (dto.password() != null && !dto.password().isEmpty()) {
            funcionario.setPassword(passwordEncoder.encode(dto.password()));
        }

        return toResponse(funcionarioRepository.save(funcionario));
    }

    public void changePassword(String oldPassword, String newPassword) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();

        Funcionario funcionario = (Funcionario) funcionarioRepository.findByEmailFunc(username);
        if (funcionario == null) {
            throw new ResourceNotFoundException("Usuário não encontrado.");
        }

        if (!passwordEncoder.matches(oldPassword, funcionario.getPassword())) {
            throw new InvalidPasswordException("A senha antiga está incorreta.");
        }

        funcionario.setPassword(passwordEncoder.encode(newPassword));
        funcionarioRepository.save(funcionario);
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
                funcionario.getPassword(),
                funcionario.getFilial().getId(),
                funcionario.getFilial().getNomeFilial()
        );
    }
}
