package br.com.easymoto.converter;

import br.com.easymoto.enums.StatusLocacao;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class StatusLocacaoConverter implements AttributeConverter<StatusLocacao, Integer> {

    @Override
    public Integer convertToDatabaseColumn(StatusLocacao attribute) {
        return attribute != null ? attribute.getCode() : null;
    }

    @Override
    public StatusLocacao convertToEntityAttribute(Integer dbData) {
        if (dbData == null) return null;
        for (StatusLocacao value : StatusLocacao.values()) {
            if (value.getCode() == dbData) {
                return value;
            }
        }
        throw new IllegalArgumentException("Código inválido para StatusLocacao: " + dbData);
    }
}
