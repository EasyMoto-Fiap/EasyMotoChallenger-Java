package br.com.easymoto.service;

import br.com.easymoto.dto.MotoRequest;
import br.com.easymoto.model.Moto;

import java.util.List;

public interface MotoService {
    List<Moto> listarTodas();
    Moto buscarPorId(Long id);
    void salvar(MotoRequest request);
    void excluir(Long id);
    boolean existePlaca(String placa);
}
