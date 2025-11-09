package br.com.easymoto.service;

import br.com.easymoto.model.Moto;
import br.com.easymoto.repository.MotoRepository;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MongoExportService {

    private final MotoRepository motoRepository;
    private final EasyMotoDbPackageService easyMotoDbPackageService;
    private final MongoTemplate mongoTemplate;

    public void exportarMotosParaMongo() {
        mongoTemplate.dropCollection("motos_json");

        List<Moto> motos = motoRepository.findAll();

        for (Moto moto : motos) {
            String json = easyMotoDbPackageService.getRowJson("MOTO", "ID_MOTO", moto.getId());
            Document doc = Document.parse(json);
            mongoTemplate.insert(doc, "motos_json");
        }
    }
}
