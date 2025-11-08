package br.com.easymoto.converter;

import br.com.easymoto.enums.StatusMoto;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class StatusMotoConverter implements AttributeConverter<StatusMoto, Integer> {

    @Override
    public Integer convertToDatabaseColumn(StatusMoto attribute) {
        return attribute != null ? attribute.getCode() : null;
    }

    @Override
    public StatusMoto convertToEntityAttribute(Integer dbData) {
        if (dbData == null) return null;
        for (StatusMoto value : StatusMoto.values()) {
            if (value.getCode() == dbData) {
                return value;
            }
        }
        throw new IllegalArgumentException("Código inválido para StatusMoto: " + dbData);
    }
}
