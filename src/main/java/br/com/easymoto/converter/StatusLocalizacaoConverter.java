package br.com.easymoto.converter;

import br.com.easymoto.enums.StatusLocalizacao;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class StatusLocalizacaoConverter implements AttributeConverter<StatusLocalizacao, Integer> {

    @Override
    public Integer convertToDatabaseColumn(StatusLocalizacao attribute) {
        return attribute != null ? attribute.getCodigo() : null;
    }

    @Override
    public StatusLocalizacao convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }
        return StatusLocalizacao.fromCodigo(dbData);
    }
}
