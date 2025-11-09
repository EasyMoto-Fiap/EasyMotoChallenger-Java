package br.com.easymoto.controller.web;

import br.com.easymoto.service.EasyMotoDbPackageService;
import br.com.easymoto.service.MongoExportService;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/web/db")
@RequiredArgsConstructor
public class DbWebController {

    private final EasyMotoDbPackageService easyMotoDbPackageService;
    private final MongoExportService mongoExportService;
    private final MongoTemplate mongoTemplate; // <-- adiciona isso

    @GetMapping("/tools")
    public String telaFerramentas(Model model) {
        model.addAttribute("mensagem", null);
        model.addAttribute("resumo", null);
        return "db/tools";
    }

    @GetMapping("/resumo-locacoes")
    public String executarProcedureResumo(Model model) {
        List<Map<String, Object>> resumo = easyMotoDbPackageService.resumoLocacoes();
        model.addAttribute("mensagem", "Procedure RESUMO_LOCACAO executada com sucesso.");
        model.addAttribute("resumo", resumo);
        return "db/tools";
    }

    @GetMapping("/exportar-motos-mongo")
    public String exportarMotosMongo(Model model) {
        mongoExportService.exportarMotosParaMongo();
        model.addAttribute("mensagem", "Motos exportadas para MongoDB com sucesso (coleção 'motos_json').");
        model.addAttribute("resumo", null);
        return "db/tools";
    }

    @GetMapping("/motos-json")
    public String listarMotosJson(Model model) {
        List<Document> docs = mongoTemplate.findAll(Document.class, "motos_json");
        model.addAttribute("docs", docs);
        return "db/motos-json";
    }
}
