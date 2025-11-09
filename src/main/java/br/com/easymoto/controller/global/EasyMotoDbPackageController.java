package br.com.easymoto.controller.global;

import br.com.easymoto.service.EasyMotoDbPackageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/db")
@RequiredArgsConstructor
public class EasyMotoDbPackageController {

    private final EasyMotoDbPackageService easyMotoDbPackageService;

    @PostMapping("/motos-json")
    public String executarMotosJson(@RequestParam(required = false) Integer clienteId) {
        easyMotoDbPackageService.executarMotosJson(clienteId);
        return "Procedure PKG_EASYMOTO.MOTOS_JSON executada. JSON gerado no DBMS_OUTPUT do banco.";
    }

    @GetMapping("/resumo-locacoes")
    public List<Map<String, Object>> resumoLocacoes() {
        return easyMotoDbPackageService.resumoLocacoes();
    }

    @GetMapping("/motos/{id}/json")
    public String motoJson(@PathVariable Long id) {
        return easyMotoDbPackageService.getRowJson("MOTO", "ID_MOTO", id);
    }
}
