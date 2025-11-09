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
        return StatusMoto.fromCode(dbData);
    }
}
