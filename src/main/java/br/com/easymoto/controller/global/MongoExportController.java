package br.com.easymoto.controller.global;

import br.com.easymoto.service.MongoExportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/export")
@RequiredArgsConstructor
public class MongoExportController {

    private final MongoExportService mongoExportService;

    @PostMapping("/motos-mongo")
    public String exportarMotosParaMongo() {
        mongoExportService.exportarMotosParaMongo();
        return "Motos exportadas para MongoDB com sucesso (coleção 'motos_json').";
    }
}
