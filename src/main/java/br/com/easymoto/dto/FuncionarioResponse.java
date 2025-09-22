package br.com.easymoto.dto;

import br.com.easymoto.enums.TypeCargo;

public record FuncionarioResponse(
        Long id,
        String nomeFunc,
        String cpfFunc,
        TypeCargo cargo,
        String telefoneFunc,
        String emailFunc,
        String password,
        Long filialId,
        String filialNome
) {}
