package br.com.easymoto.controller.global;

import br.com.easymoto.service.ProcedureEasyMotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/locacoes")
@RequiredArgsConstructor
public class LocacaoProcedureController {

    private final ProcedureEasyMotoService procedureEasyMotoService;

    @PostMapping("/finalizar-vencidas")
    public String finalizarLocacoesVencidas() {
        procedureEasyMotoService.finalizarLocacoesVencidasHoje();
        return "Locações vencidas finalizadas com sucesso.";
    }
}
