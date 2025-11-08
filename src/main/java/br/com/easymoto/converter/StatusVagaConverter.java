package br.com.easymoto.converter;

import br.com.easymoto.enums.StatusVaga;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class StatusVagaConverter implements AttributeConverter<StatusVaga, Integer> {

    @Override
    public Integer convertToDatabaseColumn(StatusVaga attribute) {
        return attribute != null ? attribute.getCode() : null;
    }

    @Override
    public StatusVaga convertToEntityAttribute(Integer dbData) {
        if (dbData == null) return null;
        for (StatusVaga value : StatusVaga.values()) {
            if (value.getCode() == dbData) {
                return value;
            }
        }
        throw new IllegalArgumentException("Código inválido para StatusVaga: " + dbData);
    }
}
