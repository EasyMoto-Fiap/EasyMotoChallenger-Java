package br.com.easymoto;

import br.com.easymoto.model.Empresa;
import br.com.easymoto.model.Filial;
import br.com.easymoto.repository.EmpresaRepository;
import br.com.easymoto.repository.FilialRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// INSERTS PARA CLOUD
@Component
public class DataSeeder implements CommandLineRunner {

    private final EmpresaRepository empresaRepository;
    private final FilialRepository filialRepository;

    public DataSeeder(EmpresaRepository empresaRepository, FilialRepository filialRepository) {
        this.empresaRepository = empresaRepository;
        this.filialRepository = filialRepository;
    }

    @Override
    public void run(String... args) {
        //Empresa emp1 = empresaRepository.save(new Empresa(null, "Nova Vanguarda Motos", "98765432000112"));
        //Empresa emp2 = empresaRepository.save(new Empresa(null, "Speed Motor", "12345678000190"));
        //Empresa emp3 = empresaRepository.save(new Empresa(null, "Fênix Motocenter", "23456789000199"));
        //Empresa emp4 = empresaRepository.save(new Empresa(null, "Moto Power", "34567890000188"));
        //Empresa emp5 = empresaRepository.save(new Empresa(null, "Extreme Bikes", "45678900000177"));

        //filialRepository.save(new Filial(null, "Filial Leste", "São Paulo", "SP", "Brasil", "Avenida dos Bandeirantes, 1000", emp1));
        //filialRepository.save(new Filial(null, "Filial Sul", "Curitiba", "PR", "Brasil", "Rua das Flores, 150", emp2));
        //filialRepository.save(new Filial(null, "Filial Norte", "Manaus", "AM", "Brasil", "Avenida do Turismo, 500", emp3));
        //filialRepository.save(new Filial(null, "Filial Centro", "Goiânia", "GO", "Brasil", "Rua Central, 10", emp4));
        //filialRepository.save(new Filial(null, "Filial Oeste", "Campo Grande", "MS", "Brasil", "Avenida Oeste, 99", emp5));
    }
}
