package br.com.easymoto.converter;

import br.com.easymoto.enums.StatusLocalizacao;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class StatusLocalizacaoConverter implements AttributeConverter<StatusLocalizacao, Integer> {

    @Override
    public Integer convertToDatabaseColumn(StatusLocalizacao attribute) {
        return attribute != null ? attribute.getCode() : null;
    }

    @Override
    public StatusLocalizacao convertToEntityAttribute(Integer dbData) {
        if (dbData == null) return null;
        for (StatusLocalizacao value : StatusLocalizacao.values()) {
            if (value.getCode() == dbData) {
                return value;
            }
        }
        throw new IllegalArgumentException("Código inválido para StatusLocalizacao: " + dbData);
    }
}
