package br.com.easymoto.converter;

import br.com.easymoto.enums.TypeCargo;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class TypeCargoConverter implements AttributeConverter<TypeCargo, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TypeCargo attribute) {
        return attribute != null ? attribute.getCode() : null;
    }

    @Override
    public TypeCargo convertToEntityAttribute(Integer dbData) {
        if (dbData == null) return null;
        for (TypeCargo value : TypeCargo.values()) {
            if (value.getCode() == dbData) {
                return value;
            }
        }
        throw new IllegalArgumentException("Código inválido para TypeCargo: " + dbData);
    }
}
